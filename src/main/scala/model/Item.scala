package model
import model.Player

abstract class Item(var player: Player) {
  def whenUsed()
}

class RightDash extends Item(player = new Player("")) {

  var rightdash = new RightDash

  override def whenUsed(): Unit = {
    player.xPos += 2
    player.inventory -= rightdash
  }
}

class LeftDash extends Item(player = new Player("")) {

  var leftdash = new LeftDash;

  override def whenUsed(): Unit = {
    player.xPos -= 2
    player.inventory -= leftdash
  }
}

class UpDash extends Item(player = new Player("")) {

  var updash = new UpDash

  override def whenUsed(): Unit = {
    player.yPos -= 2
    player.inventory -= updash
  }
}

class DownDash extends Item(player = new Player("")) {

  var downdash = new DownDash

  override def whenUsed(): Unit = {
    player.yPos += 2
    player.inventory -= downdash
  }
}


