package model.fileIOComponent

import model.levelComponent.LevelInterface

trait FileIOInterface {

  def load: Unit
  def save: Unit
}
