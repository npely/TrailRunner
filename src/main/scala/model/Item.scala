package model

import model.player.{Player, PlayerFactory}

abstract class Item(var player: Player) {
  def whenUsed()
}

object RightDash extends Item(player = PlayerFactory.createPlayer1()) {

  override def whenUsed(): Unit = {
    player.xPos += 2
    player.inventory -= RightDash
  }
}

object LeftDash extends Item(player = PlayerFactory.createPlayer1()) {

  override def whenUsed(): Unit = {
    player.xPos -= 2
    player.inventory -= LeftDash
  }
}

object UpDash extends Item(player = PlayerFactory.createPlayer1()) {

  override def whenUsed(): Unit = {
    player.yPos -= 2
    player.inventory -= UpDash
  }
}

object DownDash extends Item(player = PlayerFactory.createPlayer1()) {

  override def whenUsed(): Unit = {
    player.yPos += 2
    player.inventory -= DownDash
  }
}


