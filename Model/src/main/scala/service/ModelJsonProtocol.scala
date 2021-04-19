package service

import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldBaseImpl.Field
import model.levelComponent.levelBaseImpl.Level
import model.playerComponent.PlayerInterface
import spray.json.{DefaultJsonProtocol, DeserializationException, JsArray, JsBoolean, JsNumber, JsObject, JsString, JsValue, RootJsonFormat}

object ModelJsonProtocol extends DefaultJsonProtocol {

  implicit object FieldJsonFormat extends RootJsonFormat[Field] {
    def write(field: Field): JsObject = JsObject(
      "value" -> JsNumber(field.value),
      "fieldType" -> JsString(field.fieldType),
      "fog" -> JsBoolean(field.fog),
      "isPlayerOnField" -> JsBoolean(field.isPlayerOnField)
    )

    def read(value: JsValue): Field = {
      value.asJsObject.getFields("value") match {
        case Seq(JsNumber(value),JsString(fieldType),JsBoolean(fog),JsBoolean(isPlayerOnField)) =>
          Field(value.toInt, fieldType, fog, isPlayerOnField)
        case _ => throw DeserializationException("Field expected")
      }
    }
  }

  implicit object LevelJsonFormat extends RootJsonFormat[Level] {

    def write(level: Level): JsObject = level.toJson

    def read(value: JsValue): Level = {
      value.asJsObject.getFields("name", "player", "winX", "winY", "doorX", "doorY", "isDoorOpen", "size") match {
        case Seq(JsString(name), JsObject(player), JsNumber(winX), JsNumber(winY), JsNumber(doorX), JsNumber(doorY), JsBoolean(isDoorOpen), JsNumber(size)) =>
          Level(name, player, winX.toInt, winY.toInt, doorX.toInt, doorY.toInt, isDoorOpen, size.toInt)
        case _ => throw DeserializationException("Field expected")
      }
    }
  }
}
