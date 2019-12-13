import aview.TUI
import aview.gui.GUI
import controller.Controller
import de.htwg.se.sudoku.controller.DungeonChanged
import model.Field
import model.maps._
import model.player.PlayerFactory

import scala.io.StdIn.readLine

object TrailRunner {
    val controller = new Controller(PlayerFactory.createPlayer1(), Field(0), new Level1)
    val tui = new TUI(controller)
    val gui = new GUI(controller)
    controller.publish(new DungeonChanged)

    def main(args: Array[String]): Unit = {
        var input: String = ""

        do {
            input = readLine()
        } while (tui.evaluateInput(input) != -1)

        println("Bis zum nächsten Mal!")
    }
}
