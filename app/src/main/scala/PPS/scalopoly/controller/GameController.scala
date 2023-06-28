package PPS.scalopoly.controller

import PPS.scalopoly.engine.{GameEngine, EndgameLogicEngine}
import PPS.scalopoly.utils.FxmlUtils
import PPS.scalopoly.view.GameView
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.ButtonType
import PPS.scalopoly.utils.resources.FxmlResources

/** Controller for the game view.
  */
object GameController:

  /** Remove current player from the game.
    */
  def currentPlayerQuit(): Unit =
    GameEngine.currentPlayerQuit()
    if EndgameLogicEngine.checkVictory() then showVictory()

  /** Throw the dice and update the view.
    * @return
    *   the result of the dice throw.
    */
  def throwDice(): (Int, Int) =
    val (dice1, dice2) = GameEngine.moveCurrentPlayer()
    (dice1, dice2)

  /** End the turn of the current player.
    */
  def endTurn(): Unit =
    GameEngine.endTurn()

  private def showVictory(): Unit =
    val result = FxmlUtils.showAlert(
      AlertType.INFORMATION,
      "VITTORIA",
      "Vittoria",
      "Complimenti " + GameEngine.winner.get.nickname + " hai vinto!"
    )
    result.get match
      case ButtonType.OK =>
        GameEngine.newGame()
        FxmlUtils.changeScene(FxmlResources.START_MENU.path)
