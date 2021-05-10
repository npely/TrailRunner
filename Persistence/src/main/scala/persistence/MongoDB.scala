package persistence
import model.levelComponent.levelBaseImpl.Level
import org.mongodb.scala._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}
import org.mongodb.scala.model.Filters._

object MongoDB extends PersistenceInterface {

  val uri: String = "mongodb://mongo:27017/"
  val client: MongoClient = MongoClient(uri)
  val database: MongoDatabase = client.getDatabase("TrailRunner")
  val levelCollection: MongoCollection[Document] = database.getCollection("levels")
  val fieldCollection: MongoCollection[Document] = database.getCollection("fields")


  override def load(): Level = {
    /*val levelDocument = collection.find({ _id: "levelDocument"}).head().onComplete({
      case Success(value) => Some(value.toJson())
      case Failure(exception) => print(exception)
    })*/
    ???
  }

  override def save(level: Level): Boolean = {
    levelCollection.deleteMany(equal("_id", "levelDocument"))
    fieldCollection.deleteMany(equal("filter", "fieldDocument"))
    val playerDocument: Document = Document("_id" -> "playerDocument", "playerX" -> level.player.xPos, "playerY" -> level.player.yPos)
    for (x <- 0 to level.dungeon.length - 1) {
      for (y <- 0 to level.dungeon.length - 1) {
        if (level.player.xPos == x && level.player.yPos == y) {
          fieldCollection.insertOne(Document("_id" -> x.toString.concat(y.toString), "name" -> "fieldDocument", "value" -> level.dungeon(x)(y).value,
            "fieldType" -> level.dungeon(x)(y).fieldType, "fog" -> level.dungeon(x)(y).fog, "isPlayerOnField" -> true))
        } else {
          fieldCollection.insertOne(Document("_id" -> x.toString.concat(y.toString), "name" -> "fieldDocument", "value" -> level.dungeon(x)(y).value,
            "fieldType" -> level.dungeon(x)(y).fieldType, "fog" -> level.dungeon(x)(y).fog, "isPlayerOnField" -> false))
        }
      }
    }
    val levelDocument: Document = Document("_id" -> "levelDocument", "name" -> level.name, "winX" -> level.winX,
      "winY" -> level.winY, "doorX" -> level.doorX, "doorY" -> level.doorY,
      "isDoorOpen" -> level.isDoorOpen, "size" -> level.size, "playerDocument" -> playerDocument)
    Try({
      levelCollection.insertOne(levelDocument)
    }) match {
      case Success(value) => true
      case Failure(exception) => println(exception); false
    }
  }
}
