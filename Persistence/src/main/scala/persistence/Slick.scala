package persistence
import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldBaseImpl.Field
import model.levelComponent.levelBaseImpl.Level
import model.playerComponent.playerBaseImpl.Player
import persistence.sqlTables.{FieldTable, LevelTable, PlayerTable}
import slick.jdbc.JdbcBackend.Database
import slick.driver.PostgresDriver.api._
import slick.lifted.TableQuery

import scala.concurrent.Await
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}

object Slick extends PersistenceInterface {

  val db = Database.forURL("jdbc:postgresql://db:5432/postgres", "postgres", "postgres", driver = "org.postgresql.Driver")

  val playerTable = TableQuery[PlayerTable]
  val fieldTable = TableQuery[FieldTable]
  val levelTable = TableQuery[LevelTable]
  val tables = List(playerTable, fieldTable, levelTable)

  tables.foreach(e => Await.result(db.run(e.schema.createIfNotExists), Duration.Inf))

  override def load(): Level = {
    val player = Await.result(db.run(playerTable.result.map(_.headOption.map(v => Player(v._2, v._3)))), Duration.Inf)
    val level = Await.result(db.run(levelTable.result.map(_.headOption.map(v => Level(v._2, player.get, v._3, v._4, v._5, v._6, v._7, v._8)))), Duration.Inf).get
    Await.result(db.run(fieldTable.result.map(_.foreach(f => level.dungeon(f._2)(f._3) = Field(f._4, f._5, f._6, f._7)))), Duration.Inf)
    level
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
