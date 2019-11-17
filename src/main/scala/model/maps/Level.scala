package model.maps

import model.{Field, Player}

case class Level(name: String, playername: String, rows: Int, columns: Int, startX: Int, startY: Int, winX: Int, winY: Int) {

  var level: Array[Array[Field]] = Array.ofDim[Field](rows, columns)
  var player = Player(playername)

  def sum() : Int = {

    var sum = 0

    for (i <- 0 until rows; j <- 0 until columns) {
      sum += level(i)(j).value
    }
    sum
  }

  def lose(): Boolean = {

    if (level(player.yPos)(player.xPos).value == -1) {
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

}

object Level1 extends Level("Level1", "Peter", 2, 2, 0, 1, 1, 0) {

  var player1 = Player(playername)

  player.xPos = startX
  player.yPos = startY

  // Zeile 1 der Matrix
  var field11 = Field(1)
  var field12 = Field(1)

  // Zeile 2 der Matrix
  var field21 = Field(2)
  var field22 = Field(1)


  level(0)(0) = field11
  level(winY)(winX) = field12

  level(startY)(startX) = field21
  level(1)(1) = field22

}

object Level2 extends Level("Level2", "Niklas",5, 5, 0, 4, 4, 0){

  var player2 = Player(playername)

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

  level(0)(0) = field11
  level(0)(1) = field12
  level(0)(2) = field13
  level(0)(3) = field14
  level(winY)(winX) = field15

  level(1)(0) = field21
  level(1)(1) = field22
  level(1)(2) = field23
  level(1)(3) = field24
  level(1)(4) = field25

  level(2)(0) = field31
  level(2)(1) = field32
  level(2)(2) = field33
  level(2)(3) = field34
  level(2)(4) = field35

  level(3)(0) = field41
  level(3)(1) = field42
  level(3)(2) = field43
  level(3)(3) = field44
  level(3)(4) = field45

  level(startY)(startX) = field51
  level(4)(1) = field52
  level(4)(2) = field53
  level(4)(3) = field54
  level(4)(4) = field55

}

object Level3 extends Level("Level3", "Roland", 2, 5, 0, 1, 1, 1) {

  var player3 = Player(playername)

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

  level(0)(0) = field11
  level(0)(1) = field12
  level(0)(2) = field13
  level(0)(3) = field14
  level(0)(4) = field15

  level(startY)(startX) = field21
  level(winY)(winX) = field22
  level(1)(2) = field23
  level(1)(3) = field24
  level(1)(4) = field25

}


