package persistence.sqlTables

import slick.jdbc.PostgresProfile.api._

class PlayerTable(tag: Tag) extends Table[(Int, Int, Int)](tag, "PLAYER") {

  def id = column[Int]("ID", O.PrimaryKey)
  def xValue = column[Int]("X")
  def yValue = column[Int]("Y")

  override def * = (id, xValue, yValue)
}
