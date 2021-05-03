package persistence.service


import model.levelComponent.levelBaseImpl.Level
import persistence.{FileIO, Slick}

object PersistenceController {

  def loadLastScore(): Level = {
    Slick.load()
  }

  def saveLastScore(level: Level): Boolean = {
    Slick.save(level)
  }

}
