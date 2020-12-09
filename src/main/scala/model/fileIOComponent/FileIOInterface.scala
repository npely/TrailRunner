package model.fileIOComponent

import model.levelComponent.LevelInterface
import play.api.libs.json.JsObject

trait FileIOInterface {

  def load: LevelInterface
  def save(level: LevelInterface): Unit
  def levelToJson(level: LevelInterface): JsObject
}
