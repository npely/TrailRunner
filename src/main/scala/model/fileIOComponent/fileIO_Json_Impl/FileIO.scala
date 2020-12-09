package model.fileIOComponent.fileIO_Json_Impl

import com.google.inject.Guice
import com.google.inject.name.Names
import main.TrailRunnerModule
import model.fileIOComponent.FileIOInterface
import model.levelComponent.LevelInterface
import play.api.libs.json.{JsArray, JsNumber, JsValue, Json}
import net.codingwell.scalaguice.InjectorExtensions._

import scala.io.Source


class FileIO extends FileIOInterface {

  override def load(source: String): LevelInterface = {
    var jsonString: String = ""
    if ("".equals(source)) {
      jsonString = Source.fromFile("level.json").getLines().mkString
    } else {
      jsonString = source
    }
    val json: JsValue = Json.parse(jsonString)
    val name = (json \ "level" \ "name").as[String]
    val size = (json \ "level" \ "size").as[Int]
    val injector = Guice.createInjector(new TrailRunnerModule)
    val level: LevelInterface = injector.instance[LevelInterface](Names.named(name))
    val fields: JsArray = (json \ "fields").as[JsArray]

    var row = 0
    var col = 0

    for (field <- fields.value) {
      level.dungeon(row)(col).value = (field \ "fieldvalue").as[Int]
      level.dungeon(row)(col).fieldType = (field \ "fieldtype").as[String]
      col += 1
      if (col % size == 0) {
        row += 1
        col = 0
      }
    }
    level.player.xPos = (json \ "level" \ "PxPos").as[Int]
    level.player.yPos = (json \ "level" \ "PyPos").as[Int]
    level.doorX = (json \ "level" \ "DxPos").as[Int]
    level.doorY = (json \ "level" \ "DyPos").as[Int]
    level.winX = (json \ "level" \ "WxPos").as[Int]
    level.winY = (json \ "level" \ "WyPos").as[Int]
    level
  }

  override def save(level: LevelInterface): String = {
    import java.io._
    val levelAsJson = Json.prettyPrint(levelToJson(level))
    val pw = new PrintWriter(new File("level.json"))
    pw.write(levelAsJson)
    pw.close()
    levelAsJson
  }

  override def levelToJson(level: LevelInterface) = {
    val levelObj = Json.obj(
      "name" -> level.getName,
      "size" -> JsNumber(level.dungeon.length),
      "PxPos" -> JsNumber(level.player.xPos),
      "PyPos" -> JsNumber(level.player.yPos),
      "DxPos" -> JsNumber(level.doorX),
      "DyPos" -> JsNumber(level.doorY),
      "WxPos" -> JsNumber(level.winX),
      "WyPos" -> JsNumber(level.winY),
    )

    var fields = new JsArray()
    for (i <- 0 to level.dungeon.length - 1) {
      for (j <- 0 to level.dungeon.length - 1) {
        fields = fields.append(Json.obj(
          "fieldvalue" -> level.dungeon(i)(j).value,
          "fieldtype" -> level.dungeon(i)(j).fieldType
        ))
      }
    }

    Json.obj(
      "level" -> levelObj,
      "fields" -> fields
    )
  }
}
