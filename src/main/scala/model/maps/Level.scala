package model.maps

import model.{Field, Player, Item, RightDash, LeftDash}

trait Level {

  var name: String
  var playerName: String
  var rows: Int
  var columns: Int
  var startX: Int
  var startY: Int
  var winX: Int
  var winY: Int

  val dungeon: Array[Array[Field]] = Array.ofDim[Field](rows, columns)
  var player = Player(playerName)

  def sum() : Int = {

    var sum = 0

    for (i <- 0 until rows; j <- 0 until columns) {
      sum += dungeon(i)(j).value
    }
    sum
  }

  def lose(): Boolean = {

    if (dungeon(player.yPos)(player.xPos).value < 0) {
      return true
    }
    false
  }

  def win(): Boolean = {
    if (player.xPos == winX && player.yPos == winY && this.sum() == 0) {
      return true
    }
    false
  }

  override def toString: String = dungeon.map(_.mkString).mkString("\n")

}


class Level1() extends Level {

  override var name: String = "Level1"
  override var playerName: String = "Pete"
  override var rows: Int = 2
  override var columns: Int = 2
  override var startX: Int = 0
  override var startY: Int = 1
  override var winX: Int = 1
  override var winY: Int = 0

  override val dungeon: Array[Array[Field]] = Array.ofDim[Field](rows, columns)

  player.xPos = startX
  player.yPos = startY

  // Zeile 1 der Matrix
  var field11 = Field(1)
  var field12 = Field(1)

  // Zeile 2 der Matrix
  var field21 = Field(2)
  var field22 = Field(1)


  dungeon(0)(0) = field11
  dungeon(winY)(winX) = field12

  dungeon(startY)(startX) = field21
  dungeon(1)(1) = field22

}

class Level2 extends Level {

  override var name: String = "Level2"
  override var playerName: String = "Niklas"
  override var rows: Int = 5
  override var columns: Int = 5
  override var startX: Int = 0
  override var startY: Int = 4
  override var winX: Int = 4
  override var winY: Int = 0

  override val dungeon: Array[Array[Field]] = Array.ofDim[Field](rows, columns)

  player.xPos = startX
  player.yPos = startY

  // Zeile 1 der Matrix
  var field11 = Field(1)
  var field12 = Field(1)
  var field13 = Field(2)
  var field14 = Field(2)
  var field15 = Field(1)

  // Zeile 2 der Matrix
  var field21 = Field(2)
  var field22 = Field(1)
  var field23 = Field(1)
  var field24 = Field(2)
  var field25 = Field(1)

  // Zeile 3 der Matrix
  var field31 = Field(1)
  var field32 = Field(1)
  var field33 = Field(1)
  var field34 = Field(1)
  var field35 = Field(1)

  // Zeile 4 der Matrix
  var field41 = Field(1)
  var field42 = Field(1)
  var field43 = Field(3)
  var field44 = Field(2)
  var field45 = Field(1)

  // Zeile 5 der Matrix
  var field51 = Field(1)
  var field52 = Field(1)
  var field53 = Field(2)
  var field54 = Field(1)
  var field55 = Field(1)

  dungeon(0)(0) = field11
  dungeon(0)(1) = field12
  dungeon(0)(2) = field13
  dungeon(0)(3) = field14
  dungeon(winY)(winX) = field15

  dungeon(1)(0) = field21
  dungeon(1)(1) = field22
  dungeon(1)(2) = field23
  dungeon(1)(3) = field24
  dungeon(1)(4) = field25

  dungeon(2)(0) = field31
  dungeon(2)(1) = field32
  dungeon(2)(2) = field33
  dungeon(2)(3) = field34
  dungeon(2)(4) = field35

  dungeon(3)(0) = field41
  dungeon(3)(1) = field42
  dungeon(3)(2) = field43
  dungeon(3)(3) = field44
  dungeon(3)(4) = field45

  dungeon(startY)(startX) = field51
  dungeon(4)(1) = field52
  dungeon(4)(2) = field53
  dungeon(4)(3) = field54
  dungeon(4)(4) = field55
}

class Level3 extends Level {

  override var name: String = "Level3"
  override var playerName: String = "Roland"
  override var rows: Int = 2
  override var columns: Int = 5
  override var startX: Int = 0
  override var startY: Int = 1
  override var winX: Int = 1
  override var winY: Int = 1

  override val dungeon: Array[Array[Field]] = Array.ofDim[Field](rows, columns)

  player.xPos = startX
  player.yPos = startY

  // Zeile 1 der Matrix
  var field11 = Field(1)
  var field12 = Field(2)
  var field13 = Field(1)
  var field14 = Field(3)
  var field15 = Field(2)

  // Zeile 2 der Matrix
  var field21 = Field(1)
  var field22 = Field(3)
  var field23 = Field(2)
  var field24 = Field(3)
  var field25 = Field(2)

  dungeon(0)(0) = field11
  dungeon(0)(1) = field12
  dungeon(0)(2) = field13
  dungeon(0)(3) = field14
  dungeon(0)(4) = field15

  dungeon(startY)(startX) = field21
  dungeon(winY)(winX) = field22
  dungeon(1)(2) = field23
  dungeon(1)(3) = field24
  dungeon(1)(4) = field25
}


