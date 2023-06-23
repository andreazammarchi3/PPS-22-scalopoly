package PPS.scalopoly.controller

import PPS.scalopoly.engine.GameEngine
import PPS.scalopoly.view.GameView

/** Controller for the game view.
  */
object GameController:

  /** Remove current player from the game.
    */
  def currentPlayerQuit(): Unit =
    GameEngine.currentPlayerQuit()

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
