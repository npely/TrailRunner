
import model.fieldComponent.fieldBaseImpl.Field
import model.levelComponent.levelBaseImpl.Level
import model.playerComponent.playerBaseImpl.PlayerFactory
import org.scalatest.{Matchers, WordSpec}

class PlayerSpec extends WordSpec with Matchers {
  "Player1" when {
    "new" should {
      val player = PlayerFactory.createPlayer1()
      "have a name" in {
        player.id should be(1)
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
      val player2 = PlayerFactory.createPlayer2()
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
      val player3 = PlayerFactory.createPlayer3()
      "have a name" in {
        player3.id should be(3)
      }
      "have a nice String representation" in {
        player3.toString should be("Roland")
      }
    }
  }
}
