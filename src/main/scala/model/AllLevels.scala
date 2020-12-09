package model

import levelComponent._
import model.levelComponent.levelBaseImpl.{Level, Level1, Level2, Level3, Level4, Level5}

package object AllLevels {
  private val implementedLevels : List[LevelInterface] = List(new Level1, new Level2, new Level3, new Level4, new Level5)
  def showLevel(level: LevelInterface) : String = level.getName
  def getImplementedList(): List[LevelInterface] = implementedLevels

}
