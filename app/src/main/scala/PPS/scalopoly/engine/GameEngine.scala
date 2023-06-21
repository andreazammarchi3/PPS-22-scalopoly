package PPS.scalopoly.engine

import PPS.scalopoly.model.*
import PPS.scalopoly.utils.GameUtils

object GameEngine:
  private val _game: Game = new Game

  def game: Game = _game

  def players: List[Player] = game.players

  def currentPlayer: Player = players(game.currentPlayer)

  def addPlayer(player: Player): Unit =
    game.addPlayer(player)

  def startGame(): Unit =
    game.players = GameUtils.shufflePlayers(game.players)

  def newGame(): Unit =
    game.reset()

  def exitGame(): Unit =
    sys.exit(0)

  def endTurn(): Unit =
    game.currentPlayer = (game.currentPlayer + 1) % game.players.length

  def moveCurrentPlayer(): Unit =
    Dice.rollDice()
    game.players = game.players.updated(game.currentPlayer, currentPlayer.move(Dice.sum()))

  def currentPlayerQuit(): Unit =
    val playerToDelete = currentPlayer
    endTurn()
    val nextPlayer = currentPlayer
    game.players.length match
      case 1 => exitGame()
      case _ =>
        game.players = game.removePlayer(playerToDelete)
        game.currentPlayer = game.players.indexOf(nextPlayer)

  def getSpaceNameFromPlayerPosition(player: Player): SpaceName =
    GameBoard.gameBoardMap(player.actualPosition)
