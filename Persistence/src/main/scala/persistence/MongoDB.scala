package persistence
import model.levelComponent.levelBaseImpl.Level
import model.playerComponent.playerBaseImpl.Player
import model.fieldComponent.fieldBaseImpl.Field
import org.mongodb.scala._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.result.{DeleteResult, InsertOneResult}

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration

object MongoDB extends PersistenceInterface {

  val uri: String = "mongodb://root:mongo@mongo:27017/?authSource=admin"
  val client: MongoClient = MongoClient(uri)
  val database: MongoDatabase = client.getDatabase("TrailRunner")
  val levelCollection: MongoCollection[Document] = database.getCollection("levels")
  val fieldCollection: MongoCollection[Document] = database.getCollection("fields")


  override def load(): Level = {
    val levelDocument: Document = Await.result(levelCollection.find(equal("_id", "levelDocument")).first().head(), Duration.Inf)
    val playerDocument: Document = Await.result(levelCollection.find(equal("_id", "playerDocument")).first().head(), Duration.Inf)
    val level: Level = Level(
      levelDocument("name").asString().getValue,
      Player(playerDocument("playerX").asInt32().getValue, playerDocument("playerY").asInt32().getValue),
      levelDocument("winX").asInt32().getValue,
      levelDocument("winY").asInt32().getValue,
      levelDocument("doorX").asInt32().getValue,
      levelDocument("doorY").asInt32().getValue,
      levelDocument("isDoorOpen").asBoolean().getValue,
      levelDocument("size").asInt32().getValue)
    for (x <- 0 to levelDocument("size").asInt32().getValue - 1) {
      for (y <- 0 to levelDocument("size").asInt32().getValue - 1) {
        val fieldDocument: Document = Await.result(fieldCollection.find(equal("_id", x.toString.concat(y.toString))).first().head(), Duration.Inf)
        level.dungeon(x)(y) = Field(
          fieldDocument("value").asInt32().getValue,
          fieldDocument("fieldType").asString().getValue,
          fieldDocument("fog").asBoolean().getValue,
          fieldDocument("isPlayerOnField").asBoolean().getValue)
      }
    }
    level
  }

  override def save(level: Level): Boolean = {
    Await.result(delete(), Duration.Inf)
    val playerDocument: Document = Document("_id" -> "playerDocument", "playerX" -> level.player.xPos, "playerY" -> level.player.yPos)
    for (x <- 0 to level.dungeon.length - 1) {
      for (y <- 0 to level.dungeon.length - 1) {
        if (level.player.xPos == x && level.player.yPos == y) {
          observerInsertion(fieldCollection.insertOne(Document("_id" -> x.toString.concat(y.toString), "name" -> "fieldDocument", "value" -> level.dungeon(x)(y).value,
            "fieldType" -> level.dungeon(x)(y).fieldType, "fog" -> level.dungeon(x)(y).fog, "isPlayerOnField" -> true)))
        } else {
          observerInsertion(fieldCollection.insertOne(Document("_id" -> x.toString.concat(y.toString), "name" -> "fieldDocument", "value" -> level.dungeon(x)(y).value,
            "fieldType" -> level.dungeon(x)(y).fieldType, "fog" -> level.dungeon(x)(y).fog, "isPlayerOnField" -> false)))
        }
      }
    }
    val levelDocument: Document = Document("_id" -> "levelDocument", "name" -> level.name, "winX" -> level.winX,
      "winY" -> level.winY, "doorX" -> level.doorX, "doorY" -> level.doorY,
      "isDoorOpen" -> level.isDoorOpen, "size" -> level.size)
    Try({
      observerInsertion(levelCollection.insertOne(levelDocument))
      observerInsertion(levelCollection.insertOne(playerDocument))
    }) match {
      case Success(value) => true
      case Failure(exception) => println(exception); false
    }
  }

  private def delete(): Future[String] = {
    levelCollection.deleteMany(equal("_id", "levelDocument")).subscribe(
      (dr: DeleteResult) => println(s"Deleted levelDocument"),
      (e: Throwable) => println(s"Error while trying to delete levelDocument: $e")
    )
    levelCollection.deleteMany(equal("_id", "playerDocument")).subscribe(
      (dr: DeleteResult) => println(s"Deleted playerDocument"),
      (e: Throwable) => println(s"Error while trying to delete playerDocument: $e")
    )
    fieldCollection.deleteMany(equal("name", "fieldDocument")).subscribe(
      (dr: DeleteResult) => println(s"Deleted fieldDocument"),
      (e: Throwable) => println(s"Error while trying to delete fieldDocument: $e")
    )
    Future { "Finished deleting" }
  }

  private def observerInsertion(insertObservable: SingleObservable[InsertOneResult]): Unit = {
    insertObservable.subscribe(new Observer[InsertOneResult] {
      override def onNext(result: InsertOneResult): Unit = println(s"inserted: $result")

      override def onError(e: Throwable): Unit = println(s"onError: $e")

      override def onComplete(): Unit = println("completed")
    })
  }
}
