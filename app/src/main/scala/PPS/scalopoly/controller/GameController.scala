package PPS.scalopoly.controller

import PPS.scalopoly.engine.GameEngine
import PPS.scalopoly.view.GameView

object GameController:
  private var gameView: GameView = _
  
  def setView(view: GameView): Unit =
    gameView = view

  def currentPlayerQuit(): Unit =
    GameEngine.currentPlayerQuit()
    gameView.updateStyleForCurrentPLayer()

  def throwDice(): (Int, Int) =
    val (dice1, dice2) = GameEngine.moveCurrentPlayer()
    gameView.updateTokenPosition(GameEngine.currentPlayer)
    gameView.updateDiceImg(dice1, dice2)
    (dice1, dice2)

  def endTurn(): Unit =
    GameEngine.endTurn()
    gameView.updateStyleForCurrentPLayer()
