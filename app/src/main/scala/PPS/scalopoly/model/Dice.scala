package PPS.scalopoly.model

import scala.util.Random

object Dice:
  private var _dice1: Int = _
  private var _dice2: Int = _

  rollDice()

  def dice1: Int = _dice1

  def dice2: Int = _dice2
  
  def rollDice(): Unit = 
    _dice1 = Random.between(1,7)
    _dice2 = Random.between(1,7)

  def sum(): Int = dice1 + dice2

  def checkSame(): Boolean = dice1 == dice2

