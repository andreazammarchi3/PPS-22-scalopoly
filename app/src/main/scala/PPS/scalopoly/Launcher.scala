package PPS.scalopoly

import PPS.scalopoly.utils.FxmlUtils.createPrimaryStage
import PPS.scalopoly.utils.resources.FxmlResources
import javafx.stage.Stage
import scalafx.Includes._
import scalafx.application.JFXApp3
import scalafx.application.JFXApp3.PrimaryStage
import scalafx.scene.Scene

/** Main class of the application.
  */
object Launcher extends JFXApp3:

  /** Starts the application.
    */
  override def start(): Unit =
    createPrimaryStage()
