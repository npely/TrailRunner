package main.scala.config

import model.fieldComponent.fieldBaseImpl.Field
import model.levelComponent.levelBaseImpl.Level
import model.playerComponent.playerBaseImpl.Player
import spray.json.{DefaultJsonProtocol, DeserializationException, JsArray, JsBoolean, JsNumber, JsObject, JsString, JsValue, RootJsonFormat}

object ModelJsonProtocol extends DefaultJsonProtocol {

  implicit object FieldJsonFormat extends RootJsonFormat[Field] {
    def write(field: Field): JsObject = field.toJson

    def read(value: JsValue): Field = {
      fieldFromJson(value)
    }
  }

  implicit object PlayerJsonFormat extends RootJsonFormat[Player] {
    def write(player: Player): JsObject = player.toJson

    def read(value: JsValue): Player = {
      playerFromJson(value)
    }
  }

  implicit object LevelJsonFormat extends RootJsonFormat[Level] {

    def write(level: Level): JsObject = level.toJson

    def read(value: JsValue): Level = {
      value.asJsObject.getFields("name", "player", "winX", "winY", "doorX", "doorY", "isDoorOpen", "size", "dungeon") match {
        case Seq(JsString(name), player, JsNumber(winX), JsNumber(winY), JsNumber(doorX), JsNumber(doorY), JsBoolean(isDoorOpen), JsNumber(size), JsArray(dungeon)) =>
          val level = Level(name, playerFromJson(player), winX.toInt, winY.toInt, doorX.toInt, doorY.toInt, isDoorOpen, size.toInt)
          loadDungeonFromJson(level, dungeon)
          level
        case _ => throw DeserializationException("Level expected")
      }
    }
  }

  def loadDungeonFromJson(level: Level, json: Vector[JsValue]): Unit = {
    var row = 0
    var col = 0
    for (value <- json) {
      val field = fieldFromJson(value)
      level.dungeon(row)(col) = fieldFromJson(value)
      col += 1
      if (col % level.size == 0) {
        row += 1
        col = 0
      }
    }
  }

  def playerFromJson(json: JsValue): Player = {
    json.asJsObject.getFields("xPos", "yPos") match {
      case Seq(JsNumber(xPos), JsNumber(yPos)) =>
        Player(xPos.toInt, yPos.toInt)
      case _ => throw DeserializationException("Player expected")
    }
  }

  def fieldFromJson(json: JsValue): Field = {
    json.asJsObject.getFields("value", "fieldType", "fog", "isPlayerOnField") match {
      case Seq(JsNumber(value), JsString(fieldType), JsBoolean(fog), JsBoolean(isPlayerOnField)) =>
        Field(value.toInt, fieldType, fog, isPlayerOnField)
      case _ => throw DeserializationException("Field expected")
    }
  }
}
