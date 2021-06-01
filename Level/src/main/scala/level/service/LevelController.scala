package level.service

import model.fieldComponent.fieldBaseImpl.Field
import model.levelComponent.levelBaseImpl.Level
import model.playerComponent.playerBaseImpl.Player
import play.api.libs.json.{JsArray, Json}

import scala.io.Source

object LevelController {

  var levelBuffer = scala.collection.mutable.Map[String, Level]()

  def initializeBuffer(): Boolean = {
    println("initializing Buffer")
    for (i <- 1 to 7) {
      levelBuffer += ("Level" + i -> getLevelFromFile(i))
    }
    true
  }

  def createLevel(number: Long): Level = {
    levelBuffer.get("Level" + number).getOrElse(getLevelFromFile(number))
  }

  private def getLevelFromFile(number: Long): Level = {
    println("Fetching level from file " + number)
    val json = Json.parse(Source.fromFile("/Level/src/main/resources/levels/Level%s.json".format(number)).getLines().mkString)

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

}
