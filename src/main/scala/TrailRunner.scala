import main.scala.aview.TUI
import model._
import scala.io.StdIn.readLine

object TrailRunner {
    val tui = new TUI

    def main(args: Array[String]): Unit = {
        var input: String = ""

        System.out.println("Welcome to TrailRunner!\nTo see the available commands press 'c'")
        do {
            input = readLine()
            tui.processInputLine(input)
        } while (input != "q")
    }
}
