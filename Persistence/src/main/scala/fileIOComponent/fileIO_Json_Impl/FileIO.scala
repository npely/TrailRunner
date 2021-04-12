package fileIOComponent.fileIO_Json_Impl

import fileIOComponent.FileIOInterface
import model.fieldComponent.fieldBaseImpl.Field
import model.levelComponent.LevelInterface
import model.levelComponent.levelBaseImpl.Level
import model.playerComponent.playerBaseImpl.Player
import play.api.libs.json.{JsArray, JsBoolean, JsNumber, JsValue, Json}

import scala.io.Source

class FileIO extends FileIOInterface {

  override def start(name: String): LevelInterface = {
    val json: JsValue = Json.parse(Source.fromFile("src/main/resources/levels/%s.json".format(name)).getLines().mkString)
    load(json)
  }

  override def load(source: JsValue): LevelInterface = {
    val sourceOption: Option[JsValue] = Some(source)
    val json = Some(source).getOrElse(Json.parse(Source.fromFile("src/main/resources/scores/last-score.json").getLines().mkString))

    val name = (json \ "level" \ "name").as[String]
    val size = (json \ "level" \ "size").as[Int]
    val xPos = (json \ "level" \ "PxPos").as[Int]
    val yPos = (json \ "level" \ "PyPos").as[Int]
    val doorX = (json \ "level" \ "DxPos").as[Int]
    val doorY = (json \ "level" \ "DyPos").as[Int]
    val winX = (json \ "level" \ "WxPos").as[Int]
    val winY = (json \ "level" \ "WyPos").as[Int]
    val isDoorOpen = (json \ "level" \ "Open").as[Boolean]
    val level = Level(name, Player (xPos, yPos), winX, winY, doorX, doorY, isDoorOpen, 10)
    val fields: JsArray = (json \ "fields").as[JsArray]

    var row = 0
    var col = 0

    for (field <- fields.value) {
      if (col == xPos && row == yPos) {
        level.dungeon(row)(col) = Field((field \ "fieldvalue").as[Int], (field \ "fieldtype").as[String], (field \ "fog").as[Boolean], true)
      } else {
        level.dungeon(row)(col) = Field((field \ "fieldvalue").as[Int], (field \ "fieldtype").as[String], (field \ "fog").as[Boolean], false)
      }
      col += 1
      if (col % size == 0) {
        row += 1
        col = 0
      }
    }
    level
  }

  override def save(level: LevelInterface): String = {
    import java.io._
    val levelAsJson = Json.prettyPrint(levelToJson(level))
    val pw = new PrintWriter(new File("src/main/resources/scores/last-score.json"))
    pw.write(levelAsJson)
    pw.close()
    levelAsJson
  }

  override def levelToJson(level: LevelInterface) = {
    val levelObj = Json.obj(
      "name" -> level.name,
      "size" -> JsNumber(level.dungeon.length),
      "PxPos" -> JsNumber(level.player.xPos),
      "PyPos" -> JsNumber(level.player.yPos),
      "DxPos" -> JsNumber(level.doorX),
      "DyPos" -> JsNumber(level.doorY),
      "WxPos" -> JsNumber(level.winX),
      "WyPos" -> JsNumber(level.winY),
      "Open" -> JsBoolean(level.isDoorOpen)
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
