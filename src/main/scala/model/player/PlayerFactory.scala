package model.player

object PlayerFactory {
  val PLAYER1: Int = 1
  val PLAYER2: Int = 2
  val PLAYER3: Int = 3

  def createPlayer1(): Player = {
    new Player1("Niklas")
  }

  def createPlayer2(): Player = {
    new Player2("Pete")
  }

  def createPlayer3(): Player = {
    new Player3("Roland")
  }

  case class Player1(name: String) extends Player {
    override val id: Int = PLAYER1
    override def toString: String = {
      name
    }

    def validateName(): Option[String] = try {
      case Some(name) => name
      case None => "Name can not be empty"
    }
  }

  case class Player2(name: String) extends Player {
    override val id: Int = PLAYER2

    override def toString: String = {
      name
    }

    def validateName(): Option[String] = try {
      case Some(name) => name
      case None => "Name can not be empty"
    }
  }

  case class Player3(name: String) extends Player {
    override val id: Int = PLAYER3

    override def toString: String = {
      name
    }

    def validateName(): Option[String] = try {
      case Some(name) => name
      case None => "Name can not be empty"
    }
  }
}
