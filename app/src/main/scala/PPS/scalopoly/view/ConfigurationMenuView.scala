package PPS.scalopoly.view

import PPS.scalopoly.controller.ConfigurationMenuController
import PPS.scalopoly.utils.FxmlUtils
import PPS.scalopoly.utils.resources.CssResources
import javafx.scene.control.Button
import javafx.stage.{Screen, Stage, Window}
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.{Parent, Scene}
import javafx.scene.layout.AnchorPane

import java.net.URL
import java.util

class ConfigurationMenuView extends Initializable:

  @FXML
  private var startBtn: Button = _
  @FXML
  private var exitBtn: Button = _
  @FXML
  private var startMenuPane: AnchorPane = _

  override def initialize(url: URL, rb: util.ResourceBundle): Unit =
    ConfigurationMenuController.setView(this)
    startMenuPane.getStylesheets.add(getClass.getResource(CssResources.START_MENU_STYLE.path).toExternalForm)
    FxmlUtils.setResolution(startMenuPane, 0.9, 0.9)

  def playGameBtnClick(): Unit =
    ConfigurationMenuController.playGame()

  def exitGameBtnClick(): Unit =
    ConfigurationMenuController.exitGame()