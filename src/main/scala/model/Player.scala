package model

case class Player(name: String) {

   override def toString:String = name

   var xPos: Int = 0
   var yPos: Int = 0
}
