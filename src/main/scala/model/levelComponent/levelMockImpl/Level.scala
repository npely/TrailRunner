package model.levelComponent.levelMockImpl

import model.levelComponent.LevelInterface

class Level extends LevelInterface{
  override def sum(): Int = 1

  override def lose(): Boolean = false

  override def win(): Boolean = false

  override def fillNullValues(): Unit = {}

  override def getName: String = "Mock"
}
