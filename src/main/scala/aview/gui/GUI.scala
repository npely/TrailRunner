package aview.gui

import controller.Controller
import scala.swing._


class GUI(controller: Controller) extends Frame{

  listenTo(controller)

  title = "TrailRunner"
}
