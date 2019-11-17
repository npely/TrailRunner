package model

import maps._

package object AllLevels {
  val implementedLevels : List[Level] = List(Level1, Level2, Level3)
  def showLevel(level: Level) : String = level.name
}
