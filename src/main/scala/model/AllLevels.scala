package model

import maps._

package object AllLevels {
  private val implementedLevels : List[Level] = List(Level1, Level2, Level3)
  def showLevel(level: Level) : String = level.name
  def getImplementedList(): List[Level] = implementedLevels

}
