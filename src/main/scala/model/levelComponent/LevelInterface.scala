package model.levelComponent

trait LevelInterface {

  def sum() : Int
  def lose(): Boolean
  def win(): Boolean
  def fillNullValues() : Unit
  def getName: String
  def toString: String

}
