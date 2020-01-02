package model.levelComponent.levelBaseImpl

import model.Field
import model.player.PlayerFactory

class Level3 extends Level {

  override var name: String = "Level3"
  override var playerName: String = "Roland"
  override var startX: Int = 1
  override var startY: Int = 2
  override var winX: Int = 2
  override var winY: Int = 2

  override val player = PlayerFactory.createPlayer3()

  override val dungeon: Array[Array[Field]] = Array.ofDim[Field](rows, columns)

  player.xPos = startX
  player.yPos = startY

  // Zeile 1 der Matrix
  var field11 = Field(1)
  var field12 = Field(2)
  var field13 = Field(1)
  var field14 = Field(3)
  var field15 = Field(2)

  // Zeile 2 der Matrix
  var field21 = Field(1)
  var field22 = Field(3)
  var field23 = Field(2)
  var field24 = Field(3)
  var field25 = Field(2)

  dungeon(1)(1) = field11
  dungeon(1)(2) = field12
  dungeon(1)(3) = field13
  dungeon(1)(4) = field14
  dungeon(1)(5) = field15

  dungeon(startY)(startX) = field21
  dungeon(winY)(winX) = field22
  dungeon(2)(3) = field23
  dungeon(2)(4) = field24
  dungeon(2)(5) = field25

  fillNullValues()
}
