package model.fileIOComponent.fileIO_Json_Impl

import com.google.inject.Guice
import com.google.inject.name.Names
import model.fileIOComponent.FileIOInterface
import model.levelComponent.LevelInterface
import model.playerComponent.PlayerInterface
import play.api.libs.json.{JsNumber, JsValue, Json}
import src.main.TrailRunnerModule.TrailRunnerModule
import net.codingwell.scalaguice.InjectorExtensions._

import scala.io.Source

class FileIO extends FileIOInterface {

  override def load: LevelInterface = {
    var level: LevelInterface = null
    val source: String = Source.fromFile("level.json").getLines().mkString
    val json: JsValue = Json.parse(source)
    val name = (json \ "level" \ "name").as[String]
    val injector = Guice.createInjector(new TrailRunnerModule)
    name match {
      case "Level1" => level = injector.instance[LevelInterface](Names.named("Level1"))
      case "Level2" => level = injector.instance[LevelInterface](Names.named("Level2"))
      case "Level3" => level = injector.instance[LevelInterface](Names.named("Level3"))
    }
    for {
      row <- 0 until level.dungeon.length;
      col <- 0 until level.dungeon.length
    } yield {
      val fieldvalue = (json \ "level" \ "fields" \ "fieldvalue").as[Int]
      level.dungeon(row)(col).setValue(fieldvalue)
    }
    val playerX = (json \ "level" \ "xPos").as[Int]
    val playerY = (json \ "level" \ "yPos").as[Int]
    level.player.xPos = playerX
    level.player.yPos = playerY
    level
  }

  override def save(level: LevelInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("level.json"))
    //pw.write(Json.prettyPrint(playerToJson(level.player)))
    pw.write(Json.prettyPrint(levelToJson(level)))
    pw.close()
  }

  def levelToJson(level: LevelInterface) = {
    Json.obj(
      "level" -> Json.obj(
        "name" -> level.getName,
        "size" -> JsNumber(level.dungeon.length),
        "xPos" -> JsNumber(level.player.xPos),
        "yPos" -> JsNumber(level.player.yPos),
        "fields" -> Json.toJson(
          for {
            row <- 0 until level.dungeon.length;
            col <- 0 until level.dungeon.length
          } yield {
            Json.obj(
              "fieldvalue" -> Json.toJson(level.dungeon(row)(col).value)
            )
          }
        )
      )
    )
  }
}
