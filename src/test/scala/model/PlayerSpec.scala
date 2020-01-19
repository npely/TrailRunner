
import model.fieldComponent.fieldBaseImpl.Field
import model.levelComponent.levelBaseImpl.Level
import model.playerComponent.playerBaseImpl.PlayerFactory
import model.playerComponent.playerBaseImpl.PlayerFactory.{Player1, Player2, Player3}
import org.scalatest.{Matchers, WordSpec}

class PlayerSpec extends WordSpec with Matchers {
  "Player1" when {
    "new" should {
      val player = PlayerFactory.createPlayer1()
      val player1 = new Player1("Niklas")
      "have a name" in {
        player1.id should be(1)
        player1.toString should be(player1.name)
      }
      "have a nice String representation" in {
        player.toString should be("Niklas")
      }
      "have xPos Value 0" in {
        player.xPos == 0 should be(true)
      }
      "have yPos Value 0" in {
        player.yPos == 0 should be(true)
      }
      "have xPos Value 1" in {
        player.moveRight
        player.xPos == 1 should be(true)
      }
      "have yPos Value 1" in {
        player.moveDown
        player.yPos == 1 should be(true)
      }
      "not have xPos Value -1" in {
        player.moveLeft
        player.xPos == -1 should be(false)
      }
      "not have yPos Value -1" in {
        player.moveUp
        player.yPos == -1 should be(false)
      }
      "be displayed in TUI as 'P'" in {
        player.toGameString should be("P")
      }
    }
  }
  "Player2" when {
    "new" should {
      val player2 = new Player2("Pete")
      "have a name" in {
        player2.id should be(2)
      }
      "have a nice String representation" in {
        player2.toString should be("Pete")
      }
    }
  }
  "Player3" when {
    "new" should {
      val player3 = new Player3("Roland")
      "have a name" in {
        player3.id should be(3)
      }
      "have a nice String representation" in {
        player3.toString should be("Roland")
      }
    }
  }
  "MockFactory" when {
    "new" should {
      val mplayer1 = model.playerComponent.playerMockImpl.PlayerFactory.createPlayer1()
      val mplayer2 = model.playerComponent.playerMockImpl.PlayerFactory.createPlayer2()
      val mplayer3 = model.playerComponent.playerMockImpl.PlayerFactory.createPlayer3()

      val moplayer1 = new model.playerComponent.playerMockImpl.PlayerFactory.Player1("1")
      val moplayer2 = new model.playerComponent.playerMockImpl.PlayerFactory.Player2("2")
      val moplayer3 = new model.playerComponent.playerMockImpl.PlayerFactory.Player3("3")
      "have a name" in {
        moplayer1.toString should be(moplayer1.name)
        moplayer2.toString should be(moplayer2.name)
        moplayer3.toString should be(moplayer3.name)
      }
    }
  }
}
