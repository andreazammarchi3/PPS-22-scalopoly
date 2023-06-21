package PPS.scalopoly.engine

import PPS.scalopoly.model.*
import PPS.scalopoly.utils.GameUtils

object GameEngine:

  def players: List[Player] = Game.players

  def currentPlayer: Player = players(Game.currentPlayer)

  def addPlayer(player: Player): Unit =
    Game.addPlayer(player)

  def startGame(): Unit =
    Game.players = GameUtils.shufflePlayers(Game.players)

  def newGame(): Unit =
    Game.reset()

  def exitGame(): Unit =
    sys.exit(0)

  def endTurn(): Unit =
    Game.currentPlayer = (Game.currentPlayer + 1) % Game.players.length

  def moveCurrentPlayer(): Unit =
    Dice.rollDice()
    Game.players = Game.players.updated(Game.currentPlayer, currentPlayer.move(Dice.sum()))

  def currentPlayerQuit(): Unit =
    val playerToDelete = currentPlayer
    endTurn()
    val nextPlayer = currentPlayer
    Game.players.length match
      case 1 => exitGame()
      case _ =>
        Game.removePlayer(playerToDelete)
        Game.currentPlayer = Game.players.indexOf(nextPlayer)

  def getSpaceNameFromPlayerPosition(player: Player): SpaceName =
    GameBoard.gameBoardMap(player.actualPosition)
