package model.fileIOComponent

import model.levelComponent.LevelInterface
import play.api.libs.json.JsObject

trait FileIOInterface {

  def load(json: String): LevelInterface
  def save(level: LevelInterface): String
  def levelToJson(level: LevelInterface): JsObject
}
