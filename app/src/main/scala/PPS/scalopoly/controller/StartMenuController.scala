package PPS.scalopoly.controller

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

class StartMenuController extends Initializable:

  @FXML
  private var startBtn: Button = _
  @FXML
  private var exitBtn: Button = _

  @FXML
  private var startMenuPane: AnchorPane  = _


  override def initialize(url: URL, rb: util.ResourceBundle): Unit =
    startMenuPane.getStylesheets.add(getClass.getResource(CssResources.START_MENU_STYLE.path).toExternalForm)
    val screenResolution = Screen.getPrimary.getBounds
    val width = screenResolution.getWidth * 0.9
    val height = screenResolution.getHeight * 0.9

    startMenuPane.setPrefWidth(width)
    startMenuPane.setPrefHeight(height)


  def playGame() : Unit =
    FxmlUtils.changeScene(FxmlResources.GAME_VIEW.path)

  def exitGame() : Unit = sys.exit(0)
