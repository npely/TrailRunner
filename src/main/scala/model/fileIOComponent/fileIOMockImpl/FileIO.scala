package model.fileIOComponent.fileIOMockImpl

import model.fileIOComponent.FileIOInterface
import model.levelComponent.LevelInterface
import model.levelComponent.levelMockImpl.Level
import play.api.libs.json.JsObject

class FileIO extends FileIOInterface {

  override def load: LevelInterface = new Level

  override def save(level: LevelInterface): Unit = {}

  override def levelToJson(level: LevelInterface): JsObject = {null}
}
