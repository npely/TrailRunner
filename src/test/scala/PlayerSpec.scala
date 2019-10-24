
import org.scalatest.{Matchers, WordSpec}
import org.scalatestplus.junit.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
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
