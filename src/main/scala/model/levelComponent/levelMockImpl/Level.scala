package model.levelComponent.levelMockImpl

import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldBaseImpl.Field
import model.levelComponent.LevelInterface
import model.playerComponent.PlayerInterface
import model.playerComponent.playerBaseImpl.{Player, PlayerFactory}

class Level extends LevelInterface{
  override def sum(): Int = 1

  override def lose(): Boolean = false

  override def win(): Boolean = false

  override def fillNullValues(): Unit = {}

  override def getName: String = "Mock"

  override val dungeon: Array[Array[FieldInterface]] = Array.ofDim[FieldInterface](rows, columns)

  override val player: PlayerInterface = PlayerFactory.createPlayer1()

  override var rows: Int = 1

  override var columns: Int = 1

  override var winX: Int = 1

  override var winY: Int = 1
}
