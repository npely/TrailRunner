import aview.TUI
import aview.gui.GUI

import scala.io.StdIn.readLine
import controller.Controller
import model.Field
import model.maps._
import model.player.{Player, PlayerFactory}

object TrailRunner {
    val controller = new Controller(PlayerFactory.createPlayer1(), Field(0), new Level1)
    val tui = new TUI(controller)
    //val gui = new GUI(controller)
    controller.notifyObservers()

    def main(args: Array[String]): Unit = {
        var input: String = ""

        do {
            input = readLine()
        } while (tui.evaluateInput(input) != -1)

        println("Bis zum nächsten Mal!")
    }
}
