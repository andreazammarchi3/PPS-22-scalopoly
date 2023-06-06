package PPS.scalopoly.controller

import javafx.scene.{control as jfxsc, layout as jfxsl}
import javafx.stage.{Stage, Window}
import javafx.{event as jfxe, fxml as jfxf, scene as jfxs}
import scalafx.Includes.*
import scalafx.scene.control.Alert.*
import scalafx.scene.layout.{BorderPane, GridPane}
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
  private var pane: jfxsl.BorderPane = _
  private var borderPane: BorderPane = _

  override def initialize(url: URL, rb: util.ResourceBundle): Unit =
    borderPane = new BorderPane(pane)

  def playGame() : Unit =
    val root: jfxs.Parent = jfxf.FXMLLoader.load(getClass.getResource("/fxml/GameboardFXML.fxml"))
    val scene = new Scene(root)
    val newStage = startBtn.getScene.getWindow.asInstanceOf[Stage]
    newStage.setScene(scene)

  def exitGame() : Unit = sys.exit(0)
