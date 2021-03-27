package model.levelComponent.levelBaseImpl

import com.google.inject.Inject
import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldBaseImpl.Field
import model.playerComponent.PlayerInterface

class Level5 @Inject() extends Level {

  override var name: String = "Level3"
  override var playerName: String = "Roland"
  override var startX: Int = 2
  override var startY: Int = 7
  override var winX: Int = 6
  override var winY: Int = 2
  override var doorX: Int = 6
  override var doorY: Int = 1

  override val player: PlayerInterface = PlayerFactory.createPlayer2()

  override val dungeon: Array[Array[FieldInterface]] = Array.ofDim[FieldInterface](rows, columns)

  player.xPos = startX
  player.yPos = startY

  // Zeile 2 der Matrix
  var field21 = Field(0, "Ice")
  var field22 = Field(0, "Ice")
  var field23 = Field(0, "Ice")
  var field24 = Field(0, "Ice")
  var field25 = Field(0, "Ice")
  var field26 = Field(1, "Snow")
  var field27 = Field(0, "Ice")

  // Zeile 3 der Matrix
  var field31 = Field(0, "Ice")
  var field32 = Field(1, "Snow")
  var field33 = Field(1, "Ice")
  var field34 = Field(1, "Ice")
  var field35 = Field(1, "Snow")
  var field36 = Field(1, "Ice")
  var field37 = Field(0, "Ice")

  // Zeile 4 der Matrix
  var field41 = Field(0, "Ice")
  var field42 = Field(1, "Ice")
  var field43 = Field(1, "Snow")
  var field44 = Field(1, "Snow")
  var field45 = Field(1, "Ice")
  var field46 = Field(1, "Ice")
  var field47 = Field(0, "Ice")

  // Zeile 5 der Matrix
  var field51 = Field(0, "Ice")
  var field52 = Field(1, "Ice")
  var field53 = Field(1, "Ice")
  var field54 = Field(1, "Ice")
  var field55 = Field(1, "Ice")
  var field56 = Field(1, "Ice")
  var field57 = Field(0, "Ice")

  // Zeile 6 der Matrix
  var field61 = Field(0, "Ice")
  var field62 = Field(1, "Ice")
  var field63 = Field(1, "Ice")
  var field64 = Field(2, "Ice")
  var field65 = Field(2, "Ice")
  var field66 = Field(1, "Snow")
  var field67 = Field(0, "Ice")

  // Zeile 7 der Matrix
  var field72 = Field(1, "Snow")
  var field74 = Field(2, "Ice")
  var field75 = Field(2, "Ice")

  dungeon(doorY)(doorX) = fieldDoor

  dungeon(2)(1) = field21
  dungeon(2)(2) = field22
  dungeon(2)(3) = field23
  dungeon(2)(4) = field24
  dungeon(2)(5) = field25
  dungeon(winY)(winX) = field26
  dungeon(2)(7) = field27

  dungeon(3)(1) = field31
  dungeon(3)(2) = field32
  dungeon(3)(3) = field33
  dungeon(3)(4) = field34
  dungeon(3)(5) = field35
  dungeon(3)(6) = field36
  dungeon(3)(7) = field37

  dungeon(4)(1) = field41
  dungeon(4)(2) = field42
  dungeon(4)(3) = field43
  dungeon(4)(4) = field44
  dungeon(4)(5) = field45
  dungeon(4)(6) = field46
  dungeon(4)(7) = field47

  dungeon(5)(1) = field51
  dungeon(5)(2) = field52
  dungeon(5)(3) = field53
  dungeon(5)(4) = field54
  dungeon(5)(5) = field55
  dungeon(5)(6) = field56
  dungeon(5)(7) = field57

  dungeon(6)(1) = field61
  dungeon(6)(2) = field62
  dungeon(6)(3) = field63
  dungeon(6)(4) = field64
  dungeon(6)(5) = field65
  dungeon(6)(6) = field66
  dungeon(6)(7) = field67

  dungeon(startY)(startX) = field72
  dungeon(7)(4) = field74
  dungeon(7)(5) = field75

  fillNullValues()
}
