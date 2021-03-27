package model.levelComponent.levelBaseImpl

import com.google.inject.Inject
import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldBaseImpl.Field
import model.playerComponent.PlayerInterface
import model.playerComponent.playerBaseImpl.Player

case class Level4 @Inject() (isDoorOpen: Boolean) extends Level {

  val name: String = "Level4"
  val winX: Int = 5
  val winY: Int = 2
  val doorX: Int = 5
  val doorY: Int = 1

  override val player: PlayerInterface = Player(1,8)

  // Zeile 1 der Matrix
  dungeon(1)(1) = Field(3, "Ground", false, false)
  dungeon(1)(2) = Field(4, "Ground", false, false)
  dungeon(1)(8) = Field(1, "Ground", false, false)

  // Zeile 2 der Matrix
  dungeon(2)(1) = Field(1, "Ground", false, false)
  dungeon(2)(2) = Field(2, "Ground", false, false)
  dungeon(2)(3) = Field(1, "Ground", false, false)
  dungeon(winY)(winX) = Field(1, "Ground", false, false)
  dungeon(2)(7) = Field(2, "Ground", false, false)
  dungeon(2)(8) = Field(2, "Ground", false, false)

  // Zeile 3 der Matrix
  dungeon(3)(1) = Field(1, "Ground", false, false)
  dungeon(3)(2) = Field(1, "Ground", false, false)
  dungeon(3)(3) = Field(2, "Ground", false, false)
  dungeon(3)(4) = Field(1, "Ground", false, false)
  dungeon(3)(5) = Field(1, "Ground", false, false)
  dungeon(3)(6) = Field(1, "Ground", false, false)
  dungeon(3)(7) = Field(2, "Ground", false, false)
  dungeon(3)(8) = Field(2, "Ground", false, false)

  // Zeile 4 der Matrix
  dungeon(4)(3) = Field(1, "Ground", false, false)
  dungeon(4)(4) = Field(2, "Ground", false, false)
  dungeon(4)(5) = Field(2, "Ground", false, false)
  dungeon(4)(6) = Field(2, "Ground", false, false)
  dungeon(4)(7) = Field(2, "Ground", false, false)
  dungeon(4)(8) = Field(3, "Ground", false, false)

  // Zeile 5 der Matrix
  dungeon(5)(1) = Field(1, "Ground", false, false)
  dungeon(5)(2) = Field(1, "Ground", false, false)
  dungeon(5)(4) = Field(1, "Ground", false, false)
  dungeon(5)(5) = Field(1, "Ground", false, false)
  dungeon(5)(6) = Field(1, "Ground", false, false)
  dungeon(5)(8) = Field(2, "Ground", false, false)

  // Zeile 6 der Matrix
  dungeon(6)(1) = Field(1, "Ground", false, false)
  dungeon(6)(2) = Field(2, "Ground", false, false)
  dungeon(6)(3) = Field(3, "Ground", false, false)
  dungeon(6)(4) = Field(5, "Ground", false, false)
  dungeon(6)(5) = Field(4, "Ground", false, false)
  dungeon(6)(6) = Field(2, "Ground", false, false)
  dungeon(6)(7) = Field(2, "Ground", false, false)
  dungeon(6)(8) = Field(2, "Ground", false, false)

  // Zeile 7 der Matrix
  dungeon(7)(3) = Field(2, "Ground", false, false)
  dungeon(7)(4) = Field(3, "Ground", false, false)
  dungeon(7)(5) = Field(3, "Ground", false, false)
  dungeon(7)(6) = Field(2, "Ground", false, false)
  dungeon(7)(7) = Field(2, "Ground", false, false)
  dungeon(7)(8) = Field(1, "Ground", false, false)

  // Zeile 8 der Matrix
  dungeon(8)(1) = Field(1, "Ground", false, false) // Start
  dungeon(8)(2) = Field(1, "Ground", false, false)
  dungeon(8)(3) = Field(1, "Ground", false, false)
  dungeon(8)(4) = Field(1, "Ground", false, false)
  dungeon(8)(5) = Field(2, "Ground", false, false)
  dungeon(8)(6) = Field(2, "Ground", false, false)
  dungeon(8)(7) = Field(1, "Ground", false, false)
  dungeon(8)(8) = Field(1, "Ground", false, false)

  dungeon(doorY)(doorX) = fieldDoor

  def standsPlayerInFrontOfOpenDoor(): Level4 = {
    if (player.xPos == winX && player.yPos == winY && this.sum() == 0) {
      Level4(true)
    }
    this
  }

  fillNullValues()
}
