import main.scala.aview.TUI
import model._

import scala.io.StdIn.readLine

object TrailRunner {
    val tui = new TUI

    def main(args: Array[String]): Unit = {
        var input: String = ""

        System.out.println("Welcome to TrailRunner! To choose a level type 'level'")
        do {
            input = readLine()
            tui.processInputLine(input)
        } while (input != "q")
    }
}
