package model.fileIOComponent.fileIO_Json_Impl

import com.google.inject.{ConfigurationException, Guice}
import com.google.inject.name.Names
import main.TrailRunnerModule
import model.fieldComponent.fieldBaseImpl.Field
import model.fileIOComponent.FileIOInterface
import model.levelComponent.LevelInterface
import play.api.libs.json.{JsArray, JsNumber, JsValue, Json}
import net.codingwell.scalaguice.InjectorExtensions._

import scala.io.Source


class FileIO extends FileIOInterface {

  override def load(source: JsValue): LevelInterface = {
    var json: JsValue = null
    if (source == null) {
      json = Json.parse(Source.fromFile("level.json").getLines().mkString)
    } else {
      json = source
    }
    val name = (json \ "level" \ "name").as[String]
    val size = (json \ "level" \ "size").as[Int]
    val injector = Guice.createInjector(new TrailRunnerModule)
    var level: LevelInterface = null
    try {
      level = injector.instance[LevelInterface](Names.named(name))
    } catch {
      case e: ConfigurationException => level = injector.instance[LevelInterface](Names.named("CustomLevel"))
    }
    val fields: JsArray = (json \ "fields").as[JsArray]

    var row = 0
    var col = 0

    for (field <- fields.value) {
      level.dungeon(row)(col) = Field((field \ "fieldvalue").as[Int], (field \ "fieldtype").as[String], (field \ "fog").as[Boolean], false)
      col += 1
      if (col % size == 0) {
        row += 1
        col = 0
      }
    }
    level.name = name
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
          "fieldtype" -> level.dungeon(i)(j).fieldType,
          "fog" -> level.dungeon(i)(j).fog
        ))
      }
    }

    Json.obj(
      "level" -> levelObj,
      "fields" -> fields
    )
  }
}
