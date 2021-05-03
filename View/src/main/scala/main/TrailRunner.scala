package main

import java.awt.GraphicsEnvironment
import java.io.File

import aview.gui.GUI
import aview.rest.ViewApi
import aview.tui.TUI
import com.google.inject.Guice
import controller.{ControllerInterface, DungeonChanged}
import javax.sound.sampled.AudioSystem

import scala.io.StdIn.readLine
import scala.util.{Failure, Success, Try}

object TrailRunner {
  val injector = Guice.createInjector(new TrailRunnerModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
  val gui = new GUI(controller)
  //val tui = new TUI(controller)
  controller.publish(new DungeonChanged)

  def main(args: Array[String]): Unit = {
    ViewApi.start()
    var input: String = ""
    //playSound("View/src/main/resources/audio/8-bit_Dungeon.wav");
    /*do {
      input = readLine()
    } while (tui.evaluateInput(input) != -1)

    println("Bis zum nächsten Mal!")*/
  }

  def playSound(url: String): Unit = {
    new Thread(new Runnable() {
      override def run(): Unit = {
        while (true) {
          Try{
            val clip = AudioSystem.getClip
            val inputStream = AudioSystem.getAudioInputStream(new File(url))
            clip.open(inputStream)
            clip.start()
        } match {
            case Success(s) =>
            case Failure(f) => System.err.println("failed to play audio: " + f.getMessage)
          }
          Thread.sleep(196000)
        }
      }
    }).start()
  }
}
