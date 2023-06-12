package PPS.scalopoly.controller

import PPS.scalopoly.engine.GameEngine

object GameController:
  def currentPlayerQuit(): Unit =
    GameEngine.currentPlayerQuit()
    
  def throwDice(): Unit =
    GameEngine.moveCurrentPlayer()
    
  def endTurn(): Unit =
    GameEngine.endTurn()
