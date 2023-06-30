package PPS.scalopoly.controller

import PPS.scalopoly.engine.{EndgameLogicEngine, GameEngine}
import PPS.scalopoly.model.{DiceManager, Player, SpaceStatus}
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

  /** Throw the dice and move the current player.
    *
    * @return
    *   the result of the dice roll.
    */
  def throwDice(): (Int, Int) =
    val dicePair = DiceManager().roll()
    GameEngine.moveCurrentPlayer(dicePair._1 + dicePair._2)
    dicePair

  /** End the turn of the current player.
    */
  def endTurn(): Unit =
    GameEngine.endTurn()

  def checkPlayerActions(): Unit =
    GameEngine.checkSpaceStatus match
      case SpaceStatus.OWNED_BY_ANOTHER_PLAYER =>
        GameEngine.playerPaysRent()
      case SpaceStatus.PURCHASABLE =>
        GameEngine.askPlayerToBuySpace()
      case _ =>
    if EndgameLogicEngine.checkVictory() then showVictory()

  private def showVictory(): Unit = GameEngine.winner match
    case Some(winner) =>
      AlertUtils.showVictory(winner)
      GameEngine.newGame()
      FxmlUtils.changeScene(FxmlResources.START_MENU.path)
