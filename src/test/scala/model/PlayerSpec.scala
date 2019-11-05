
import model.Player
import org.scalatest.{Matchers, WordSpec}

class PlayerSpec extends WordSpec with Matchers {
  "A Player" when {
    "new" should {
      val player = Player("Niklas")
      "have a name"  in {
        player.name should be("Niklas")
    }
    "have a nice String representation" in {
      player.toString should be("Niklas")
    }
  }}
}
