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
import java.util.ResourceBundle

/** View of the start menu.
  */
class StartMenuView extends BaseView:

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var startBtn: Button = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var exitBtn: Button = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var gameBoard: ImageView = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var pane: BorderPane = _

  override def initialize(url: URL, rb: util.ResourceBundle): Unit =
    StartMenuController.setView(this)
    FxmlUtils.setGameBoardImage(gameBoard)
    pane.getStylesheets.add(
      getClass.getResource(CssResources.GAME_STYLE.path).toExternalForm
    )
    super.initialize(url, rb)

  override protected def initUIElements(): Unit =
    FxmlUtils.setPaneResolution(pane, 0.9, 0.9)
    FxmlUtils.setGameBoardSize(pane, gameBoard)

  /** Changes the scene of the stage to the configuration menu scene.
    */
  def playGameBtnClick(): Unit =
    StartMenuController.playGame()

  /** Exits the game.
    */
  def exitGameBtnClick(): Unit =
    StartMenuController.exitGame()