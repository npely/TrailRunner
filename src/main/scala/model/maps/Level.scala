package model.maps

import model.{Field, Player}

case class Level(name: String, playerName: String, rows: Int, columns: Int, startX: Int, startY: Int, winX: Int, winY: Int) {

  var dungeon: Array[Array[Field]] = Array.ofDim[Field](rows, columns)
  var player = Player(playerName)

  def sum() : Int = {

    var sum = 0

    for (i <- 0 until rows; j <- 0 until columns) {
      sum += dungeon(i)(j).value
    }
    sum
  }

  def lose(): Boolean = {

    if (dungeon(player.yPos)(player.xPos).value == -1) {
      System.out.println("You died!")
      return true
    }
    false
  }

  def win(): Boolean = {
    if (player.xPos == winX && player.yPos == winY && this.sum() == 1) {
      return true
    }
    false
  }

  override def toString: String = dungeon.map(_.mkString).mkString("\n")

}

object Level1 extends Level("Level1", "Pete", 2, 2, 0, 1, 1, 0) {

  //var player1 = Player(playerName)

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

object Level2 extends Level("Level2", "Niklas",5, 5, 0, 4, 4, 0){

  //var player2 = Player(playerName)

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

object Level3 extends Level("Level3", "Roland", 2, 5, 0, 1, 1, 1) {

  //var player3 = Player(playerName)

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


