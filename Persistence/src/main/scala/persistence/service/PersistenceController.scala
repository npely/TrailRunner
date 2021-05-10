package persistence.service


import model.levelComponent.levelBaseImpl.Level
import persistence.{FileIO, Slick, MongoDB}

object PersistenceController {

  def loadLastScore(): Level = {
    sys.env("DB_TYPE") match {
      case "mongodb" => println("Loading from MongoDB"); MongoDB.load();
      case "postgresql" => println("Loading from Slick"); Slick.load();
      case _ => println("Loading from FileIO"); FileIO.load();
    }
  }

  def saveLastScore(level: Level): Boolean = {
    sys.env("DB_TYPE") match {
      case "mongodb" => println("Saving to MongoDB"); MongoDB.save(level);
      case "postgresql" => println("Saving to Slick"); Slick.save(level);
      case _ => println("Saving to FileIO"); FileIO.save(level);
    }
  }

}
