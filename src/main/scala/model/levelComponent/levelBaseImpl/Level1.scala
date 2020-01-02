package model.levelComponent.levelBaseImpl

import model.Field
import model.player.PlayerFactory

class Level1 extends Level {

  override var name: String = "Level1"
  override var playerName: String = "Pete"
  override var startX: Int = 4
  override var startY: Int = 5
  override var winX: Int = 5
  override var winY: Int = 4

  override val player = PlayerFactory.createPlayer1()

  override val dungeon: Array[Array[Field]] = Array.ofDim[Field](rows, columns)

  player.xPos = startX
  player.yPos = startY

  // Zeile 1 der Matrix
  var field11 = Field(1)
  var field12 = Field(1)


  // Zeile 2 der Matrix
  var field21 = Field(2)
  var field22 = Field(1)


  dungeon(4)(4) = field11
  dungeon(winY)(winX) = field12

  dungeon(startY)(startX) = field21
  dungeon(5)(5) = field22

  fillNullValues()

}
