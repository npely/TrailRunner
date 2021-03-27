package model.levelComponent.levelBaseImpl

import javax.inject.Inject
import model.fieldComponent.FieldInterface
import model.fieldComponent.fieldBaseImpl.Field
import model.playerComponent.PlayerInterface
import model.playerComponent.playerBaseImpl.Player

case class Level6 @Inject() (isDoorOpen: Boolean) extends Level {

  val name: String = "Level6"
  val winX: Int = 5
  val winY: Int = 4
  val doorX: Int = 5
  val doorY: Int = 3

  override val player: PlayerInterface = Player(4, 5)

  // Zeile 5 der Matrix
  dungeon(4)(4) = Field(1, "Ground", true, false)
  dungeon(winY)(winX) = Field(1, "Ground", true, false)


  // Zeile 6 der Matrix
  dungeon(5)(4) = Field(2, "Ground", true, false) // Start
  dungeon(5)(5) = Field(1, "Ground", true, false)

  dungeon(doorY)(doorX) = fieldDoor

  def standsPlayerInFrontOfOpenDoor(): Level6 = {
    if (player.xPos == winX && player.yPos == winY && this.sum() == 0) {
      Level6(true)
    }
    this
  }

  fillNullValues()
}

