import aview.TUI

import scala.io.StdIn.readLine
import controller.Controller
import model.{Player, Field}
import model.maps._

object TrailRunner {
    val controller = new Controller(Player(""), Field(0), Level1)
    val tui = new TUI(controller)
    controller.notifyObservers()

    def main(args: Array[String]): Unit = {
        var input: String = ""

        do {
            input = readLine()
        } while (tui.evaluateInput(input) != -1)

        println("Bis zum nächsten Mal!")
    }
}
