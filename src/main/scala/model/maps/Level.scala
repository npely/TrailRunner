package model.maps

import model.{Field, Player}

case class Level(name: String, playername: String, rows: Int, columns: Int, startValue: Int, endValue: Int) {

  var matrix: Array[Array[Field]] = Array.ofDim[Field](rows, columns)
  var startField = Field(startValue)
  var endField = Field(endValue)
  var player = new Player(playername)
}

object Level1 extends Level("Level1", "Niklas",5, 5, 1, 1){

  var level1 = Level1.matrix

  player.yPos = 4
  player.xPos = 0

  // Zeile 1 der Matrix
  var field11 = Field(1)
  var field12 = Field(1)
  var field13 = Field(2)
  var field14 = Field(2)

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
  var field52 = Field(1)
  var field53 = Field(2)
  var field54 = Field(1)
  var field55 = Field(1)

  level1(0)(0) = field11
  level1(0)(1) = field12
  level1(0)(2) = field13
  level1(0)(3) = field14
  level1(0)(4) = Level1.endField

  level1(1)(0) = field21
  level1(1)(1) = field22
  level1(1)(2) = field23
  level1(1)(3) = field24
  level1(1)(4) = field25

  level1(2)(0) = field31
  level1(2)(1) = field32
  level1(2)(2) = field33
  level1(2)(3) = field34
  level1(2)(4) = field35

  level1(3)(0) = field41
  level1(3)(1) = field42
  level1(3)(2) = field43
  level1(3)(3) = field44
  level1(3)(4) = field45

  level1(4)(0) = Level1.startField
  level1(4)(1) = field52
  level1(4)(2) = field53
  level1(4)(3) = field54
  level1(4)(4) = field55

  //level.toList.transpose.map(_.sum)

}
/*object Level2 extends Level("Level2") {

  var level: Array[Array[Field]] = Array.ofDim[Field](2, 2)

  var Dungeon2 = Dungeon(2, 1)

  var player = new Player("Peter")

  player.xPos = 0
  player.yPos = 1

  // Startfeld
  var startField: Field = Dungeon2.startField
  var endField: Field = Dungeon2.endField

  // Zeile 1 der Matrix
  var field11 = endField
  var field12 = Field(1)

  // Zeile 2 der Matrix
  var field21 = startField
  var field22 = Field(1)


  level(0)(0) = field11
  level(0)(1) = field12

  level(1)(0) = field21
  level(1)(1) = field22

}*/
