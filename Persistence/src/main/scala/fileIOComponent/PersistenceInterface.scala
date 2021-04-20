package fileIOComponent

import model.levelComponent.levelBaseImpl.Level

trait PersistenceInterface {

  def load(): Level
  def save(level: Level): Boolean
}
