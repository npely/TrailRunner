package model.levelComponent.levelBaseImpl

import javax.inject.Inject
import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldBaseImpl.Field
import model.playerComponent.PlayerInterface
import model.playerComponent.playerBaseImpl.PlayerFactory

class Level1 @Inject() extends Level {

  var name: String = "Level1"
  var playerName: String = "Pete"
  var startX: Int = 4
  var startY: Int = 5
  var winX: Int = 5
  var winY: Int = 4

  override val player: PlayerInterface = PlayerFactory.createPlayer1()

  override val dungeon: Array[Array[FieldInterface]] = Array.ofDim[FieldInterface](rows, columns)

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

  dungeon(winY - 1)(winX) = fieldDoor

  dungeon(startY)(startX) = field21
  dungeon(5)(5) = field22

  fillNullValues()
}