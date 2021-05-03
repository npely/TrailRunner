package persistence.sqlTables

import slick.jdbc.PostgresProfile.api._

class FieldTable(tag: Tag) extends Table[(Int, Int, Int, Int, String, Boolean, Boolean)](tag,"FIELD") {

  //column definition
  def id = column[Int]("ID")
  def xValue = column[Int]("X")
  def yValue = column[Int]("Y")
  def value = column[Int]("VALUE")
  def fieldType = column[String]("TYPE")
  def fog = column[Boolean]("FOG")
  def isPlayerOnField = column[Boolean]("PLAYERONFIELD")

  override def * = (id, xValue, yValue, value, fieldType, fog, isPlayerOnField)

  def pk = primaryKey("primaryKey_field", (xValue, yValue))
}
