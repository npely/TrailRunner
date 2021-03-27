package model.levelComponent.levelBaseImpl

import javax.inject.Inject
import model.fieldComponent.FieldInterface
import model.playerComponent.PlayerInterface

case class CustomLevel @Inject() (name: String, player: PlayerInterface, winX: Int, winY: Int, doorX: Int, doorY: Int, isDoorOpen: Boolean) extends Level {

  override val dungeon: Array[Array[FieldInterface]] = Array.ofDim[FieldInterface](rows, columns)

  def standsPlayerInFrontOfOpenDoor(): CustomLevel = {
    if (player.xPos == winX && player.yPos == winY && this.sum() == 0) {
      CustomLevel(this.name, this.player, this.winX, this.winY, this.doorX, doorY, true)
    }
    this
  }

  fillNullValues()
}
