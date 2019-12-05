package model

import maps._

package object AllLevels {
  private val implementedLevels : List[Level] = List(new Level1, new Level2, new Level3)
  def showLevel(level: Level) : String = level.name
  def getImplementedList(): List[Level] = implementedLevels

}
