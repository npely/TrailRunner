package model.levelComponent.levelBaseImpl

import com.google.inject.Inject
import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldBaseImpl.Field
import model.playerComponent.PlayerInterface

class Level4 @Inject() extends Level {

  override var name: String = "Level4"
  override var playerName: String = "Niklas"
  override var startX: Int = 1
  override var startY: Int = 8
  override var winX: Int = 5
  override var winY: Int = 2
  override var doorX: Int = 5
  override var doorY: Int = 1

  override val player: PlayerInterface = PlayerFactory.createPlayer2()

  override val dungeon: Array[Array[FieldInterface]] = Array.ofDim[FieldInterface](rows, columns)

  player.xPos = startX
  player.yPos = startY

  // Zeile 1 der Matrix
  var field11 = Field(3, "Ground")
  var field12 = Field(4, "Ground")
  var field18 = Field(1, "Ground")

  // Zeile 2 der Matrix
  var field21 = Field(1, "Ground")
  var field22 = Field(2, "Ground")
  var field23 = Field(1, "Ground")
  var field25 = Field(1, "Ground")
  var field27 = Field(2, "Ground")
  var field28 = Field(2, "Ground")

  // Zeile 3 der Matrix
  var field31 = Field(1, "Ground")
  var field32 = Field(1, "Ground")
  var field33 = Field(2, "Ground")
  var field34 = Field(1, "Ground")
  var field35 = Field(1, "Ground")
  var field36 = Field(1, "Ground")
  var field37 = Field(2, "Ground")
  var field38 = Field(2, "Ground")

  // Zeile 4 der Matrix
  var field43 = Field(1, "Ground")
  var field44 = Field(2, "Ground")
  var field45 = Field(2, "Ground")
  var field46 = Field(2, "Ground")
  var field47 = Field(2, "Ground")
  var field48 = Field(3, "Ground")

  // Zeile 5 der Matrix
  var field51 = Field(1, "Ground")
  var field52 = Field(1, "Ground")
  var field54 = Field(1, "Ground")
  var field55 = Field(1, "Ground")
  var field56 = Field(1, "Ground")
  var field58 = Field(2, "Ground")

  // Zeile 6 der Matrix
  var field61 = Field(1, "Ground")
  var field62 = Field(2, "Ground")
  var field63 = Field(3, "Ground")
  var field64 = Field(5, "Ground")
  var field65 = Field(4, "Ground")
  var field66 = Field(2, "Ground")
  var field67 = Field(2, "Ground")
  var field68 = Field(2, "Ground")

  // Zeile 7 der Matrix
  var field73 = Field(2, "Ground")
  var field74 = Field(3, "Ground")
  var field75 = Field(3, "Ground")
  var field76 = Field(2, "Ground")
  var field77 = Field(2, "Ground")
  var field78 = Field(1, "Ground")

  // Zeile 8 der Matrix
  var field81 = Field(1, "Ground")
  var field82 = Field(1, "Ground")
  var field83 = Field(1, "Ground")
  var field84 = Field(1, "Ground")
  var field85 = Field(2, "Ground")
  var field86 = Field(2, "Ground")
  var field87 = Field(1, "Ground")
  var field88 = Field(1, "Ground")

  dungeon(1)(1) = field11
  dungeon(1)(2) = field12
  dungeon(doorY)(doorX) = fieldDoor
  dungeon(1)(8) = field18

  dungeon(2)(1) = field21
  dungeon(2)(2) = field22
  dungeon(2)(3) = field23
  dungeon(winY)(winX) = field25
  dungeon(2)(7) = field27
  dungeon(2)(8) = field28

  dungeon(3)(1) = field31
  dungeon(3)(2) = field32
  dungeon(3)(3) = field33
  dungeon(3)(4) = field34
  dungeon(3)(5) = field35
  dungeon(3)(6) = field36
  dungeon(3)(7) = field37
  dungeon(3)(8) = field38

  dungeon(4)(3) = field43
  dungeon(4)(4) = field44
  dungeon(4)(5) = field45
  dungeon(4)(6) = field46
  dungeon(4)(7) = field47
  dungeon(4)(8) = field48

  dungeon(5)(1) = field51
  dungeon(5)(2) = field52
  dungeon(5)(4) = field54
  dungeon(5)(5) = field55
  dungeon(5)(6) = field56
  dungeon(5)(8) = field58

  dungeon(6)(1) = field61
  dungeon(6)(2) = field62
  dungeon(6)(3) = field63
  dungeon(6)(4) = field64
  dungeon(6)(5) = field65
  dungeon(6)(6) = field66
  dungeon(6)(7) = field67
  dungeon(6)(8) = field68

  dungeon(7)(3) = field73
  dungeon(7)(4) = field74
  dungeon(7)(5) = field75
  dungeon(7)(6) = field76
  dungeon(7)(7) = field77
  dungeon(7)(8) = field78

  dungeon(startY)(startX) = field81
  dungeon(8)(2) = field82
  dungeon(8)(3) = field83
  dungeon(8)(4) = field84
  dungeon(8)(5) = field85
  dungeon(8)(6) = field86
  dungeon(8)(7) = field87
  dungeon(8)(8) = field88

  fillNullValues()
}
