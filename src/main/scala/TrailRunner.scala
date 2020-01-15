import aview.TUI
import aview.gui.GUI
import com.google.inject.Guice
import controller.controllerComponent.{ControllerInterface, DungeonChanged}
import src.main.TrailRunnerModule.TrailRunnerModule

import scala.io.StdIn.readLine

object TrailRunner {
    val injector = Guice.createInjector(new TrailRunnerModule)
    val controller = injector.getInstance(classOf[ControllerInterface])
    val gui = new GUI(controller)
    val tui = new TUI(controller)
    controller.publish(new DungeonChanged)

    def main(args: Array[String]): Unit = {
        var input: String = ""

        do {
            input = readLine()
        } while (tui.evaluateInput(input) != -1)

        println("Bis zum nächsten Mal!")
    }
}
