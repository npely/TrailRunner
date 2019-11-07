package model

import model.maps._

package object AllLevels {
  val implementedLevels = List(Level1)
  def showLevel(level: Level) : Unit = {
    println(level.name)
  }
}
