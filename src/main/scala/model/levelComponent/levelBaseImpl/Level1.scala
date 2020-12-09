package model.levelComponent.levelBaseImpl

import javax.inject.Inject
import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldBaseImpl.Field
import model.playerComponent.PlayerInterface
import model.playerComponent.playerBaseImpl.PlayerFactory

class Level1 @Inject() extends Level {

  override var name: String = "Level1"
  override var playerName: String = "Pete"
  override var startX: Int = 4
  override var startY: Int = 5
  override var winX: Int = 5
  override var winY: Int = 4
  override var doorX: Int = 5
  override var doorY: Int = 3

  override val player: PlayerInterface = PlayerFactory.createPlayer1()

  override val dungeon: Array[Array[FieldInterface]] = Array.ofDim[FieldInterface](rows, columns)

  player.xPos = startX
  player.yPos = startY

  // Zeile 1 der Matrix
  var field11 = Field(1, "Ground")
  var field12 = Field(1, "Ground")


  // Zeile 2 der Matrix
  var field21 = Field(2, "Ground")
  var field22 = Field(1, "Ground")


  dungeon(4)(4) = field11
  dungeon(winY)(winX) = field12

  dungeon(doorY)(doorX) = fieldDoor

  dungeon(startY)(startX) = field21
  dungeon(5)(5) = field22

  fillNullValues()
}
