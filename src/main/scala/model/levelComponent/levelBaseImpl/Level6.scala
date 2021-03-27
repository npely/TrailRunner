package model.levelComponent.levelBaseImpl

import javax.inject.Inject
import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldBaseImpl.Field
import model.playerComponent.PlayerInterface

class Level6 @Inject() extends Level {

  override var name: String = "Level6"
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
  field11.fog = true
  var field12 = Field(1, "Ground")
  field12.fog = true


  // Zeile 2 der Matrix
  var field21 = Field(2, "Ground")
  field21.fog = true
  var field22 = Field(1, "Ground")
  field22.fog = true


  dungeon(4)(4) = field11
  dungeon(winY)(winX) = field12

  dungeon(doorY)(doorX) = fieldDoor

  dungeon(startY)(startX) = field21
  dungeon(5)(5) = field22

  fillNullValues()
}

