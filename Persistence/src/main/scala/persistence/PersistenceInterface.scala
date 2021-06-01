package persistence

import model.levelComponent.levelBaseImpl.Level

import scala.concurrent.Future

trait PersistenceInterface {

  def load(): Level
  def save(level: Level): Boolean
  def delete(): Future[String]
}
