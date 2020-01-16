package model.levelComponent.levelBaseImpl

import com.google.inject.Inject
import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldBaseImpl.Field
import model.playerComponent.PlayerInterface
import model.playerComponent.playerBaseImpl.PlayerFactory

class Level2 @Inject() extends Level {

  override var name: String = "Level2"
  override var playerName: String = "Niklas"
  override var startX: Int = 1
  override var startY: Int = 5
  override var winX: Int = 5
  override var winY: Int = 1

  override val player: PlayerInterface = PlayerFactory.createPlayer2()

  override val dungeon: Array[Array[FieldInterface]] = Array.ofDim[FieldInterface](rows, columns)

  player.xPos = startX
  player.yPos = startY

  // Zeile 1 der Matrix
  var field11 = Field(1)
  var field12 = Field(1)
  var field13 = Field(2)
  var field14 = Field(2)
  var field15 = Field(1)

  // Zeile 2 der Matrix
  var field21 = Field(2)
  var field22 = Field(1)
  var field23 = Field(1)
  var field24 = Field(2)
  var field25 = Field(1)

  // Zeile 3 der Matrix
  var field31 = Field(1)
  var field32 = Field(1)
  var field33 = Field(1)
  var field34 = Field(1)
  var field35 = Field(1)

  // Zeile 4 der Matrix
  var field41 = Field(1)
  var field42 = Field(1)
  var field43 = Field(3)
  var field44 = Field(2)
  var field45 = Field(1)

  // Zeile 5 der Matrix
  var field51 = Field(1)
  var field52 = Field(1)
  var field53 = Field(2)
  var field54 = Field(1)
  var field55 = Field(1)

  dungeon(1)(1) = field11
  dungeon(1)(2) = field12
  dungeon(1)(3) = field13
  dungeon(1)(4) = field14
  dungeon(winY)(winX) = field15
  dungeon(winY - 1)(winX) = fieldDoor

  dungeon(2)(1) = field21
  dungeon(2)(2) = field22
  dungeon(2)(3) = field23
  dungeon(2)(4) = field24
  dungeon(2)(5) = field25

  dungeon(3)(1) = field31
  dungeon(3)(2) = field32
  dungeon(3)(3) = field33
  dungeon(3)(4) = field34
  dungeon(3)(5) = field35

  dungeon(4)(1) = field41
  dungeon(4)(2) = field42
  dungeon(4)(3) = field43
  dungeon(4)(4) = field44
  dungeon(4)(5) = field45

  dungeon(startY)(startX) = field51
  dungeon(5)(2) = field52
  dungeon(5)(3) = field53
  dungeon(5)(4) = field54
  dungeon(5)(5) = field55

  fillNullValues()
}