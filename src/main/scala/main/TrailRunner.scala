package main

import java.io.File

import aview.TUI
import aview.gui.GUI
import com.google.inject.Guice
import controller.controllerComponent.{ControllerInterface, DungeonChanged}
import javax.sound.sampled.AudioSystem

import scala.io.StdIn.readLine

object TrailRunner {
  val injector = Guice.createInjector(new TrailRunnerModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
  val gui = new GUI(controller)
  val tui = new TUI(controller)
  controller.publish(new DungeonChanged)

  def main(args: Array[String]): Unit = {
    var input: String = ""

    playSound("src/main/scala/aview/gui/audio/8-bit_Dungeon.wav")

    do {
      input = readLine()
    } while (tui.evaluateInput(input) != -1)

    println("Bis zum nächsten Mal!")
  }

  def playSound(url: String): Unit = {
    new Thread(new Runnable() {
      override def run(): Unit = {
        while (true) {
          try {
            val clip = AudioSystem.getClip
            val inputStream = AudioSystem.getAudioInputStream(new File(url))
            clip.open(inputStream)
            clip.start()
          } catch {
            case e: Exception =>
              System.err.println("failed to play audio: " + e.getMessage)
          }
          Thread.sleep(196000)
        }
      }
    }).start()
  }
}
