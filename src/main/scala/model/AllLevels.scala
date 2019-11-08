package model

import model.maps._

package object AllLevels {
  val implementedLevels : List[Level] = List(Level1)
  def showLevel(level: Level) : String = level.name
}