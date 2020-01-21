package model.fileIOComponent

import model.levelComponent.LevelInterface

trait FileIOInterface {

  def load: LevelInterface
  def save(level: LevelInterface): Unit
}
