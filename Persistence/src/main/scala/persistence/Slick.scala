package persistence
import model.levelComponent.levelBaseImpl.Level
import persistence.sqlTables.{FieldTable, LevelTable, PlayerTable}
import slick.jdbc.JdbcBackend.Database
import slick.driver.PostgresDriver.api._
import slick.lifted.TableQuery

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}

object Slick extends PersistenceInterface {

  val db = Database.forURL("jdbc:postgresql://localhost/5432/TrailRunner", "postgres", "impactDB", driver = "org.postgresql.Driver")

  val playerTable = TableQuery[PlayerTable]
  val fieldTable = TableQuery[FieldTable]
  val levelTable = TableQuery[LevelTable]
  val tables = List(playerTable, fieldTable, levelTable)

  tables.foreach(e => db.run(e.schema.createIfNotExists))

  override def load(): Level = {
   ???
  }

  override def save(level: Level): Boolean = {
    tables.foreach(e => db.run(e.delete))
    Try({
      db.run(levelTable += (1, level.name, level.winX, level.winY, level.doorX, level.doorY, level.isDoorOpen, level.size))
      db.run(playerTable += (1, level.player.xPos, level.player.yPos))
      for (x <- 0 to level.dungeon.length - 1) {
        for (y <- 0 to level.dungeon.length - 1) {
          if (level.player.xPos == x && level.player.yPos == y) {
            db.run(fieldTable += (1, x, y, level.dungeon(x)(y).value, level.dungeon(x)(y).fieldType, level.dungeon(x)(y).fog, true))
          } else {
            db.run(fieldTable += (1, x, y, level.dungeon(x)(y).value, level.dungeon(x)(y).fieldType, level.dungeon(x)(y).fog, false))
          }
        }
      }
    }) match {
      case Success(value) => true
      case Failure(exception) => println(exception); false
    }
  }
}
