package PPS.scalopoly.view

import PPS.scalopoly.controller.StartMenuController
import PPS.scalopoly.utils.resources.CssResources
import PPS.scalopoly.utils.FxmlUtils
import javafx.scene.control.Button
import javafx.stage.Stage
import javafx.fxml.{FXML, Initializable}
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane

import java.net.URL
import java.util.ResourceBundle

/** View of the start menu.
  */
class StartMenuView extends Initializable:

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var startBtn: Button = _

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var exitBtn: Button = _

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var gameBoard: ImageView = _

  @FXML
  @SuppressWarnings(Array("org.wartremover.warts.Null"))
  private var pane: BorderPane = _

  override def initialize(location: URL, resources: ResourceBundle): Unit =
    FxmlUtils.initUIElements(
      pane,
      gameBoard,
      CssResources.GAME_STYLE,
      FxmlUtils.DEFAULT_WIDTH_PERC,
      FxmlUtils.DEFAULT_HEIGHT_PERC
    )

  /** Changes the scene of the stage to the configuration menu scene.
    */
  def playGameBtnClick(): Unit =
    StartMenuController.playGame()

  /** Exits the game.
    */
  def exitGameBtnClick(): Unit =
    StartMenuController.exitGame()
