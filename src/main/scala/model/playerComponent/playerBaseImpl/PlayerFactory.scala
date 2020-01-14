package model.playerComponent.playerBaseImpl

import model.playerComponent.{PlayerFactoryInterface, PlayerInterface}

object PlayerFactory extends PlayerFactoryInterface {

  val PLAYER1: Int = 1
  val PLAYER2: Int = 2
  val PLAYER3: Int = 3

  def createPlayer1(): PlayerInterface = {
    new Player1("Niklas")
  }

  def createPlayer2(): PlayerInterface = {
    new Player2("Pete")
  }

  def createPlayer3(): PlayerInterface = {
    new Player3("Roland")
  }

  case class Player1(name: String) extends Player {
    override val id: Int = PLAYER1
    override def toString: String = {
      name
    }
  }

  case class Player2(name: String) extends Player {
    override val id: Int = PLAYER2
    override def toString: String = {
      name
    }
  }

  case class Player3(name: String) extends Player {
    override val id: Int = PLAYER3
    override def toString: String = {
      name
    }
  }
}
