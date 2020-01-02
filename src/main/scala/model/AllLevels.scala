package model

import levelComponent._
import model.levelComponent.levelBaseImpl.{Level, Level1, Level2, Level3}

package object AllLevels {
  private val implementedLevels : List[Level] = List(new Level1, new Level2, new Level3)
  def showLevel(level: Level) : String = level.name
  def getImplementedList(): List[Level] = implementedLevels

}
