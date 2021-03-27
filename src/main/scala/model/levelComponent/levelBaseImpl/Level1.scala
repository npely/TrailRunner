package model.levelComponent.levelBaseImpl

import javax.inject.Inject
import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldBaseImpl.Field
import model.playerComponent.PlayerInterface
import model.playerComponent.playerBaseImpl.Player

case class Level1 @Inject() (isDoorOpen: Boolean) extends Level {

  val name: String = "Level1"
  val winX: Int = 5
  val winY: Int = 4
  val doorX: Int = 5
  val doorY: Int = 3
  val player: PlayerInterface = Player(4, 5)

  // Zeile 5 der Matrix
  dungeon(4)(4) = Field(1, "Ground", false, false)
  dungeon(winY)(winX) = Field(1, "Ground", false, false)

  // Zeile 6 der Matrix
  dungeon(4)(5) = Field(2, "Ground", false, false) //Start
  dungeon(5)(5) = Field(1, "Ground", false, false)

  dungeon(doorY)(doorX) = fieldDoor

  def standsPlayerInFrontOfOpenDoor(): Level1 = {
    if (player.xPos == winX && player.yPos == winY && this.sum() == 0) {
      Level1(true)
    }
    this
  }

  fillNullValues()
}
