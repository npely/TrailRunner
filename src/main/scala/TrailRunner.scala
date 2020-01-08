import aview.TUI
import aview.gui.GUI
import com.google.inject.Guice
import controller.controllerComponent.{ControllerInterface, DungeonChanged}
import controller.controllerComponent.controllerBaseImpl.Controller
import model.fieldComponent.fieldBaseImpl.Field
import model.levelComponent.levelBaseImpl.Level1
import model.playerComponent.playerBaseImpl.PlayerFactory

import scala.io.StdIn.readLine

object TrailRunner {
    //val injector = Guice.createInjector(new TrailRunnerModule)
    //val controller = injector.getInstance(classOf[ControllerInterface])
    val controller = new Controller(PlayerFactory.createPlayer1(), Field(0), new Level1)
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
