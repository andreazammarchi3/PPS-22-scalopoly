package PPS.scalopoly.controller

import PPS.scalopoly.engine.GameEngine
import PPS.scalopoly.view.GameView

object GameController:
  private var gameView: GameView = _
  
  def setView(view: GameView): Unit =
    gameView = view

  def currentPlayerQuit(): Unit =
    GameEngine.currentPlayerQuit()

  def throwDice(): Unit =
    GameEngine.moveCurrentPlayer()
    gameView.updateTokenPosition(GameEngine.currentPlayer.get)

  def endTurn(): Unit =
    GameEngine.endTurn()
