package PPS.scalopoly.controller

import PPS.scalopoly.view.StartMenuView
import PPS.scalopoly.utils.resources.FxmlResources
import PPS.scalopoly.utils.FxmlUtils
import PPS.scalopoly.utils.resources.{CssResources, FxmlResources}
import javafx.scene.control.Button
import javafx.stage.{Screen, Stage, Window}
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.{Parent, Scene}
import javafx.scene.layout.AnchorPane

import java.io.IOException
import java.net.URL
import java.util

/** Controller for the start menu.
  */
object StartMenuController:

  /** Enter the configuration menu.
    */
  def playGame(): Unit =
    FxmlUtils.changeScene(FxmlResources.CONFIGURATION_MENU.path)

  /** Exit the game.
    */
  def exitGame(): Unit = sys.exit(0)
