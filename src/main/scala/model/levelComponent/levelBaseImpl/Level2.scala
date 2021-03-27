package model.levelComponent.levelBaseImpl

import com.google.inject.Inject
import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldBaseImpl.Field
import model.playerComponent.PlayerInterface
import model.playerComponent.playerBaseImpl.Player

case class Level2 @Inject() (isDoorOpen: Boolean) extends Level {

  val name: String = "Level2"
  val winX: Int = 5
  val winY: Int = 1
  val doorX: Int = 5
  val doorY: Int = 0

  override val player: PlayerInterface = Player(1, 5)

  // Zeile 2 der Matrix
  dungeon(1)(1) = Field(1, "Ground", false, false)
  dungeon(1)(2) = Field(1, "Ground", false, false)
  dungeon(1)(3) = Field(2, "Ground", false, false)
  dungeon(1)(4) = Field(2, "Ground", false, false)
  dungeon(winY)(winX) = Field(1, "Ground", false, false)
  dungeon(doorY)(doorX) = fieldDoor

  // Zeile 3 der Matrix
  dungeon(2)(1) = Field(2, "Ground", false, false)
  dungeon(2)(2) = Field(1, "Ground", false, false)
  dungeon(2)(3) = Field(1, "Ground", false, false)
  dungeon(2)(4) = Field(2, "Ground", false, false)
  dungeon(2)(5) = Field(1, "Ground", false, false)

  // Zeile 4 der Matrix
  dungeon(3)(1) = Field(1, "Ground", false, false)
  dungeon(3)(2) = Field(1, "Ground", false, false)
  dungeon(3)(3) = Field(1, "Ground", false, false)
  dungeon(3)(4) = Field(1, "Ground", false, false)
  dungeon(3)(5) = Field(1, "Ground", false, false)

  // Zeile 5 der Matrix
  dungeon(4)(1) = Field(1, "Ground", false, false)
  dungeon(4)(2) = Field(1, "Ground", false, false)
  dungeon(4)(3) = Field(3, "Ground", false, false)
  dungeon(4)(4) = Field(2, "Ground", false, false)
  dungeon(4)(5) = Field(1, "Ground", false, false)

  // Zeile 6 der Matrix
  dungeon(5)(1) = Field(1, "Ground", false, false) // Start
  dungeon(5)(2) = Field(1, "Ground", false, false)
  dungeon(5)(3) = Field(2, "Ground", false, false)
  dungeon(5)(4) = Field(1, "Ground", false, false)
  dungeon(5)(5) = Field(1, "Ground", false, false)

  def standsPlayerInFrontOfOpenDoor(): Level2 = {
    if (player.xPos == winX && player.yPos == winY && this.sum() == 0) {
      Level2(true)
    }
    this
  }

  fillNullValues()
}
