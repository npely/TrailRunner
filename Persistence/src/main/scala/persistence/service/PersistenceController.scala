package persistence.service


import model.levelComponent.levelBaseImpl.Level
import persistence.{FileIO, Slick}

object PersistenceController {

  def loadLastScore(): Level = {
    FileIO.load()
  }

  def saveLastScore(level: Level): Boolean = {
    FileIO.save(level)
  }

}
