package PPS.scalopoly.engine

import PPS.scalopoly.controller.GameController

object BotEngine:
  def play(): Unit =
    println("Bot plays")
    checkOptions()
    GameEngine.endTurn()

  private def checkOptions(): Unit =
    println("Bot checks options")
    if canThrowDice(GameEngine.currentPlayer.actualPosition) then GameController.throwDice()
    else GameEngine.endTurn()

  private def canThrowDice(startingPosition: Int): Boolean =
    GameEngine.currentPlayer.actualPosition == startingPosition
