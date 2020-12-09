package model.levelComponent.levelBaseImpl

import com.google.inject.Inject
import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldBaseImpl.Field
import model.playerComponent.PlayerInterface
import model.playerComponent.playerBaseImpl.PlayerFactory

class Level3 @Inject() extends Level {

  override var name: String = "Level3"
  override var playerName: String = "Roland"
  override var startX: Int = 1
  override var startY: Int = 2
  override var winX: Int = 2
  override var winY: Int = 2
  override var doorX: Int = 2
  override var doorY: Int = 3

  override val player: PlayerInterface = PlayerFactory.createPlayer3()

  override val dungeon: Array[Array[FieldInterface]] = Array.ofDim[FieldInterface](rows, columns)

  player.xPos = startX
  player.yPos = startY

  // Zeile 1 der Matrix
  var field11 = Field(1, "Ground")
  var field12 = Field(2, "Ground")
  var field13 = Field(1, "Ground")
  var field14 = Field(3, "Ground")
  var field15 = Field(2, "Ground")

  // Zeile 2 der Matrix
  var field21 = Field(1, "Ground")
  var field22 = Field(3, "Ground")
  var field23 = Field(2, "Ground")
  var field24 = Field(3, "Ground")
  var field25 = Field(2, "Ground")

  dungeon(1)(1) = field11
  dungeon(1)(2) = field12
  dungeon(1)(3) = field13
  dungeon(1)(4) = field14
  dungeon(1)(5) = field15

  dungeon(startY)(startX) = field21
  dungeon(winY)(winX) = field22
  dungeon(doorY)(doorX) = fieldDoorReversed
  dungeon(2)(3) = field23
  dungeon(2)(4) = field24
  dungeon(2)(5) = field25

  fillNullValues()
}
