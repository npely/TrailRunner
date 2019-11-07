import main.scala.aview.TUI
import model._
import scala.io.StdIn.readLine

object TrailRunner {
    val tui = new TUI

    def main(args: Array[String]): Unit = {
        var input: String = ""

        do {
            printf(tui.toString)
            input = readLine()
        } while (tui.evaluateInput(input) != -1)
    }
}
