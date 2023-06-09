package PPS.scalopoly.controller

import PPS.scalopoly.utils.resources.{CssResources, FxmlResources}
import javafx.scene.{control as jfxsc, image as jfxsi, layout as jfxsl}
import javafx.stage.{Stage, Window}
import javafx.{event as jfxe, fxml as jfxf, scene as jfxs}
import jdk.internal.org.jline.utils.InfoCmp.Capability
import scalafx.Includes.*
import scalafx.scene.control.Alert.*
import scalafx.scene.layout.{BorderPane, GridPane, HBox}
import scalafx.scene.Scene

import java.io.IOException
import java.net.URL
import java.util

class StartMenuController extends jfxf.Initializable:

  @jfxf.FXML
  private var startBtn: jfxsc.Button = _
  @jfxf.FXML
  private var exitBtn: jfxsc.Button = _

  @jfxf.FXML
  private var startMenuPane: jfxsl.AnchorPane  = _


  override def initialize(url: URL, rb: util.ResourceBundle): Unit =

    val css = getClass.getResource(CssResources.START_MENU_STYLE).toExternalForm
    startMenuPane.getStylesheets.add(css)
    val screenResolution = javafx.stage.Screen.getPrimary.getBounds
    val width = screenResolution.getWidth * 0.9
    val height = screenResolution.getHeight * 0.9

    startMenuPane.setPrefWidth(width)
    startMenuPane.setPrefHeight(height)


  def playGame() : Unit =
    val root: jfxs.Parent = jfxf.FXMLLoader.load(getClass.getResource(FxmlResources.GAME_VIEW))
    val scene = new Scene(root)
    val newStage = startBtn.getScene.getWindow.asInstanceOf[Stage]
    newStage.setScene(scene)

  def exitGame() : Unit = sys.exit(0)
