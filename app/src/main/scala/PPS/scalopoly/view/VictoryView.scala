package PPS.scalopoly.view

import PPS.scalopoly.controller.VictoryController
import PPS.scalopoly.engine.GameEngine
import javafx.fxml.{FXML, Initializable}
import javafx.scene.control.{Button, Label}
import javafx.scene.layout.BorderPane

import java.net.URL
import java.util.ResourceBundle

/** View for the victory screen.
 */
class VictoryView extends Initializable:

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var pane: BorderPane = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var victoryLbl: Label = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var backToMenuBtn: Button = _

  @FXML
  @SuppressWarnings(
    Array("org.wartremover.warts.Null", "org.wartremover.warts.Var")
  )
  private var exitBtn: Button = _

  override def initialize(location: URL, resources: ResourceBundle): Unit =
    victoryLbl.setText("")

  /** Changes the scene of the stage to the start menu scene.
   */
  def backToMenuClick(): Unit = VictoryController.backToMenu()

  /** Exits the game.
   */
  def exitGameBtnClick(): Unit = VictoryController.exitGame()
