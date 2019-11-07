package model.maps

import model.{Dungeon, Field, Player}

case class Level(name: String) {

}

object Level1 extends Level("Level1") {


  var level: Array[Array[Field]] = Array.ofDim[Field](5, 5)

  var Dungeon1 = Dungeon(1)

  var player = new Player("Niklas")

  player.yPos = 4
  player.xPos = 0

  // Startfeld
  var startField: Field = Dungeon1.startField

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
  var field52 = Field(1)
  var field53 = Field(2)
  var field54 = Field(1)
  var field55 = Field(1)

  level(0)(0) = field11
  level(0)(1) = field12
  level(0)(2) = field13
  level(0)(3) = field14
  level(0)(4) = field15

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

  level(4)(0) = startField
  level(4)(1) = field52
  level(4)(2) = field53
  level(4)(3) = field54
  level(4)(4) = field55

  override def toString: String = {
    level.map(_.mkString).mkString("\n")
  }

  var Levelmap: String = level.map(_.mkString).mkString("\n")

  def main(args: Array[String]): Unit = {
    print(Levelmap)
  }

}
object Level2 extends Level("Level2") {

  var level: Array[Array[Field]] = Array.ofDim[Field](5, 5)

  var Dungeon2 = Dungeon(2)

  // Startfeld
  var startField: Field = Dungeon2.startField

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
  var field52 = Field(1)
  var field53 = Field(2)
  var field54 = Field(1)
  var field55 = Field(1)

  level(0)(0) = field11
  level(0)(1) = field12
  level(0)(2) = field13
  level(0)(3) = field14
  level(0)(4) = field15

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

  level(4)(0) = startField
  level(4)(1) = field52
  level(4)(2) = field53
  level(4)(3) = field54
  level(4)(4) = field55

}
