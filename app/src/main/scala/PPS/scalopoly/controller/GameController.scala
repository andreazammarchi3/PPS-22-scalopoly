package PPS.scalopoly.controller

import PPS.scalopoly.engine.{EndgameLogicEngine, GameEngine, SpaceStatus}
import PPS.scalopoly.model.Player
import PPS.scalopoly.utils.{AlertUtils, FxmlUtils}
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

  def checkPlayerActions(): Unit =
    GameEngine.checkPlayerActions()
    if EndgameLogicEngine.checkVictory() then showVictory()

  private def showVictory(): Unit = GameEngine.winner match
    case Some(winner) =>
      AlertUtils.showVictory(winner)
      GameEngine.newGame()
      FxmlUtils.changeScene(FxmlResources.START_MENU.path)
