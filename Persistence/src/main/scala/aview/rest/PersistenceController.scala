package aview.rest

import fileIOComponent.fileIO_Json_Impl.FileIO
import model.levelComponent.levelBaseImpl.Level

object PersistenceController {

  def loadLastScore(): Level = {
    FileIO.load()
  }

  def saveLastScore(level: Level): Boolean = {
    FileIO.save(level)
  }

}
