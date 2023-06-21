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
  private var startMenuPane: AnchorPane = _

  override def initialize(url: URL, rb: util.ResourceBundle): Unit =
    startMenuPane.getStylesheets.add(
      getClass.getResource(CssResources.START_MENU_STYLE.path).toExternalForm
    )
    FxmlUtils.setResolution(startMenuPane, 0.9, 0.9)

  def playGame(): Unit =
    FxmlUtils.changeScene(FxmlResources.GAME_VIEW.path)

  def exitGame(): Unit = sys.exit(0)
