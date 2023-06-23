package PPS.scalopoly.controller

import PPS.scalopoly.engine.GameEngine
import PPS.scalopoly.view.GameView

/** Controller for the game view.
  */
object GameController:
  private var gameView: GameView = _

  /** Set the view of the controller.
    * @param view
    *   the view to set.
    */
  def setView(view: GameView): Unit =
    gameView = view

  /** Remove current player from the game.
    */
  def currentPlayerQuit(): Unit =
    GameEngine.currentPlayerQuit()
    gameView.updateStyleForCurrentPLayer()

  /** Throw the dice and update the view.
    * @return
    *   the result of the dice throw.
    */
  def throwDice(): (Int, Int) =
    val (dice1, dice2) = GameEngine.moveCurrentPlayer()
    gameView.updateTokenPosition(GameEngine.currentPlayer)
    gameView.updateDiceImg(dice1, dice2)
    (dice1, dice2)

  /** End the turn of the current player.
    */
  def endTurn(): Unit =
    GameEngine.endTurn()
    gameView.updateStyleForCurrentPLayer()
