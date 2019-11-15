
import model.{Field, Player}
import model.maps.Level
import org.scalatest.{Matchers, WordSpec}

class PlayerSpec extends WordSpec with Matchers {
  "A Player" when {
    "new" should {
      val player = Player("Niklas")
      "have a name" in {
        player.name should be("Niklas")
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
    }
  }
}
