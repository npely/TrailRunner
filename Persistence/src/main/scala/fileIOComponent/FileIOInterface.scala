package fileIOComponent

import model.levelComponent.LevelInterface
import play.api.libs.json.{JsObject, JsValue}

trait FileIOInterface {

  def load(json: JsValue): LevelInterface
  def save(level: LevelInterface): String
  def levelToJson(level: LevelInterface): JsObject
  def start(name: String): LevelInterface
}
