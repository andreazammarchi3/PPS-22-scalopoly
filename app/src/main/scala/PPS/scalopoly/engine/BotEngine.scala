package PPS.scalopoly.engine

import PPS.scalopoly.controller.GameController
import PPS.scalopoly.model.space.purchasable.PurchasableSpace

import scala.annotation.tailrec

object BotEngine:
  def play(): Unit =
    println(s"${GameEngine.currentPlayer.nickname} plays")
    checkOptions()
    GameController.endTurn()

  def decideToBuySpace(purchasableSpace: PurchasableSpace): Boolean =
    println(s"${GameEngine.currentPlayer.nickname} decides to buy ${purchasableSpace.name}")
    true

  @tailrec
  private def checkOptions(): Unit =
    println(s"${GameEngine.currentPlayer.nickname} checks options")
    if canThrowDice(GameEngine.currentPlayer.actualPosition) then
      val (dice1, dice2) = GameController.throwDice()
      println(s"${GameEngine.currentPlayer.nickname} throws dice: $dice1, $dice2")
      if dice1 == dice2 then checkOptions()
    else GameController.endTurn()

  private def canThrowDice(startingPosition: Int): Boolean =
    GameEngine.currentPlayer.actualPosition == startingPosition
