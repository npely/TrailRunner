package model.levelComponent.levelBaseImpl

import com.google.inject.Inject
import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldBaseImpl.Field
import model.playerComponent.PlayerInterface
import model.playerComponent.playerBaseImpl.Player

case class Level3 @Inject() (isDoorOpen: Boolean) extends Level {

  val name: String = "Level3"
  val winX: Int = 2
  val winY: Int = 2
  val doorX: Int = 2
  val doorY: Int = 3

  override val player: PlayerInterface = Player(1,2)

  // Zeile 1 der Matrix
  dungeon(1)(1) = Field(1, "Ground", false, false)
  dungeon(1)(2) = Field(2, "Ground", false, false)
  dungeon(1)(3) = Field(1, "Ground", false, false)
  dungeon(1)(4) = Field(3, "Ground", false, false)
  dungeon(1)(5) = Field(2, "Ground", false, false)

  // Zeile 2 der Matrix
  dungeon(2)(1) = Field(1, "Ground", false, false) // Start
  dungeon(winY)(winX) = Field(3, "Ground", false, false)
  dungeon(2)(3) = Field(2, "Ground", false, false)
  dungeon(2)(4) = Field(3, "Ground", false, false)
  dungeon(2)(5) = Field(2, "Ground", false, false)

  dungeon(doorY)(doorX) = fieldDoorReversed

  def standsPlayerInFrontOfOpenDoor(): Level3 = {
    if (player.xPos == winX && player.yPos == winY && this.sum() == 0) {
      Level3(true)
    }
    this
  }

  fillNullValues()
}
