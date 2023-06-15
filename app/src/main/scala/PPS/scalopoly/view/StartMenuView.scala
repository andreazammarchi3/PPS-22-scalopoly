package PPS.scalopoly.view

import PPS.scalopoly.controller.StartMenuController
import PPS.scalopoly.utils.resources.{CssResources, ImgResources}
import PPS.scalopoly.utils.FxmlUtils
import javafx.scene.control.Button
import javafx.stage.{Screen, Stage, Window}
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.image.{Image, ImageView}
import javafx.scene.{Parent, Scene}
import javafx.scene.layout.BorderPane

import java.net.URL
import java.util

class StartMenuView extends Initializable:

  @FXML
  private var startBtn: Button = _
  @FXML
  private var exitBtn: Button = _
  @FXML
  private var gameBoard: ImageView = _

  @FXML
  private var pane: BorderPane = _

  override def initialize(url: URL, rb: util.ResourceBundle): Unit =
    StartMenuController.setView(this)
    gameBoard.setImage(new Image(getClass.getResource(ImgResources.GAMEBOARD_SQUARED.path).toString))
    gameBoard.setPreserveRatio(false)
    pane.getStylesheets.add(getClass.getResource(CssResources.GAME_STYLE.path).toExternalForm)
    initUIElements()

  private def initUIElements(): Unit =
    FxmlUtils.setPaneResolution(pane, 0.9, 0.9)
    FxmlUtils.setGameBoardSize(pane, gameBoard)

  def playGameBtnClick(): Unit =
    StartMenuController.playGame()

  def exitGameBtnClick(): Unit =
    StartMenuController.exitGame()

