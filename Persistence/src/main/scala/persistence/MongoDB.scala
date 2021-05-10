package persistence
import model.levelComponent.levelBaseImpl.Level
import org.mongodb.scala._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}

object MongoDB extends PersistenceInterface {

  val uri: String = "mongodb://mongo:27017/"
  val client: MongoClient = MongoClient(uri)
  val database: MongoDatabase = client.getDatabase("TrailRunner")
  val collection: MongoCollection[Document] = database.getCollection("mongo")


  override def load(): Level = {
    val test = collection.find().first().head().onComplete({
      case Success(value) => Some(value.toJson())
      case Failure(exception) => print(exception)
    })
  }

  override def save(level: Level): Boolean = {
    val levelDocument: Document = Document("_id" -> level.name, "name" -> level.name, "playerX" -> level.player.xPos,
      "playerY" -> level.player.yPos, "winX" -> level.winX, "winY" -> level.winY, "doorX" -> level.doorX, "doorY" -> level.doorY,
      "isDoorOpen" -> level.isDoorOpen, "size" -> level.size)
    Try({
      collection.insertOne(levelDocument)
    }) match {
      case Success(value) => true
      case Failure(exception) => println(exception); false
    }
  }
}
