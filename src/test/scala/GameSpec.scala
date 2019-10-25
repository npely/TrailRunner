import org.scalatest.WordSpec
import org.scalatest.Matchers

class GameSpec extends WordSpec with Matchers {
  "A Game" should {
      val newGame = TrailRunner
      "not be null"  in {
        newGame != null should be(true)
      }
     "have Game name as value" in {
       newGame.game should equal("TrailRunner")
     }
     "be version" in {
       newGame.version should be(1.0)
     }
     "return name" in {
       newGame.setPlayerName("Niklas") should be(Player("Niklas"))
     }
    }
}
