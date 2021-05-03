package persistence.sqlTables

import slick.jdbc.PostgresProfile.api._

class LevelTable(tag: Tag) extends Table[(Int, String, Int, Int, Int, Int, Boolean, Int)](tag, "LEVEL") {

  def id = column[Int]("ID", O.PrimaryKey)
  def name = column[String]("NAME")
  def winX = column[Int]("WIN_X")
  def winY = column[Int]("WIN_Y")
  def doorX = column[Int]("DOOR_X")
  def doorY = column[Int]("DOOR_Y")
  def isDoorOpen = column[Boolean]("DOOR_OPEN")
  def size = column[Int]("SIZE")

  override def * = (id, name, winX, winY, doorX, doorY, isDoorOpen, size)
}
