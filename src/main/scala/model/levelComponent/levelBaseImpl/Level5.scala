package model.levelComponent.levelBaseImpl

import com.google.inject.Inject
import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldBaseImpl.Field
import model.playerComponent.PlayerInterface
import model.playerComponent.playerBaseImpl.Player

case class Level5 @Inject() (isDoorOpen: Boolean) extends Level {

  val name: String = "Level3"
  val winX: Int = 6
  val winY: Int = 2
  val doorX: Int = 6
  val doorY: Int = 1

  override val player: PlayerInterface = Player(2, 7)

  // Zeile 2 der Matrix
  dungeon(2)(1) = Field(0, "Ice", false, false)
  dungeon(2)(2) = Field(0, "Ice", false, false)
  dungeon(2)(3) = Field(0, "Ice", false, false)
  dungeon(2)(4) = Field(0, "Ice", false, false)
  dungeon(2)(5) = Field(0, "Ice", false, false)
  dungeon(winY)(winX) = Field(1, "Snow", false, false)
  dungeon(2)(7) = Field(0, "Ice", false, false)

  // Zeile 3 der Matrix
  dungeon(3)(1) = Field(0, "Ice", false, false)
  dungeon(3)(2) = Field(1, "Snow", false, false)
  dungeon(3)(3) = Field(1, "Ice", false, false)
  dungeon(3)(4) = Field(1, "Ice", false, false)
  dungeon(3)(5) = Field(1, "Snow", false, false)
  dungeon(3)(6) = Field(1, "Ice", false, false)
  dungeon(3)(7) = Field(0, "Ice", false, false)

  // Zeile 4 der Matrix
  dungeon(4)(1) = Field(0, "Ice", false, false)
  dungeon(4)(2) = Field(1, "Ice", false, false)
  dungeon(4)(3) = Field(1, "Snow", false, false)
  dungeon(4)(4) = Field(1, "Snow", false, false)
  dungeon(4)(5) = Field(1, "Ice", false, false)
  dungeon(4)(6) = Field(1, "Ice", false, false)
  dungeon(4)(7) = Field(0, "Ice", false, false)

  // Zeile 5 der Matrix
  dungeon(5)(1) = Field(0, "Ice", false, false)
  dungeon(5)(2) = Field(1, "Ice", false, false)
  dungeon(5)(3) = Field(1, "Ice", false, false)
  dungeon(5)(4) = Field(1, "Ice", false, false)
  dungeon(5)(5) = Field(1, "Ice", false, false)
  dungeon(5)(6) = Field(1, "Ice", false, false)
  dungeon(5)(7) = Field(0, "Ice", false, false)

  // Zeile 6 der Matrix
  dungeon(6)(1) = Field(0, "Ice", false, false)
  dungeon(6)(2) = Field(1, "Ice", false, false)
  dungeon(6)(3) = Field(1, "Ice", false, false)
  dungeon(6)(4) = Field(2, "Ice", false, false)
  dungeon(6)(5) = Field(2, "Ice", false, false)
  dungeon(6)(6) = Field(1, "Snow", false, false)
  dungeon(6)(7) = Field(0, "Ice", false, false)

  // Zeile 7 der Matrix
  dungeon(7)(2) = Field(1, "Snow", false, false) // Start
  dungeon(7)(4) = Field(2, "Ice", false, false)
  dungeon(7)(5) = Field(2, "Ice", false, false)

  dungeon(doorY)(doorX) = fieldDoor

  def standsPlayerInFrontOfOpenDoor(): Level5 = {
    if (player.xPos == winX && player.yPos == winY && this.sum() == 0) {
      Level5(true)
    }
    this
  }

  fillNullValues()
}
