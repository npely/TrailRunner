package model.playerComponent.playerMockImpl

import model.playerComponent.playerMockImpl.Player
import model.playerComponent.{PlayerFactoryInterface, PlayerInterface}

object PlayerFactory extends PlayerFactoryInterface {
  override def createPlayer1(): PlayerInterface = Player1("1")

  override def createPlayer2(): PlayerInterface = Player2("2")

  override def createPlayer3(): PlayerInterface = Player3("3")

  case class Player1(name: String) extends Player {
    override val id: Int = 1
    override def toString: String = {
      name
    }
  }

  case class Player2(name: String) extends Player {
    override val id: Int = 2
    override def toString: String = {
      name
    }
  }

  case class Player3(name: String) extends Player {
    override val id: Int = 3
    override def toString: String = {
      name
    }
  }
}
