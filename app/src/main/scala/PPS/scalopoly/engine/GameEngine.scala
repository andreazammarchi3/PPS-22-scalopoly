package PPS.scalopoly.engine

import PPS.scalopoly.model.*
import PPS.scalopoly.utils.GameUtils

object GameEngine:

  private val _game: Game = new Game
  private val _dice: Dice = new Dice
  private val _gameBoard: GameBoard = new GameBoard

  def dice: Dice = _dice

  def gameBoard: GameBoard = _gameBoard

  def game: Game = _game

  def players: List[Player] = game.players

  def currentPlayer: Option[Player] = game.currentPlayer

  def addPlayer(player: Player): Unit =
    game.addPlayer(player)

  def startGame(): Unit =
    game.players = GameUtils.shufflePlayers(game.players)
    game.currentPlayer = Some(game.players.head)

  def newGame(): Unit =
    game.reset()

  def exitGame(): Unit =
    sys.exit(0)

  def endTurn(): Unit =
    val currentIndex = game.players.indexOf(game.currentPlayer.get)
    val newIndex = (currentIndex + 1) % game.players.length
    game.currentPlayer = Some(game.players(newIndex))

  def moveCurrentPlayer(): Unit =
    dice.rollDice()
    game.currentPlayer.get.actualPosition = GameUtils.addSumToPosition(
      dice.sum(),
      game.currentPlayer.get.actualPosition,
      gameBoard
    )

  def currentPlayerQuit(): Unit =
    val playerToDelete = game.currentPlayer.get
    endTurn()
    game.players.length match
      case 1 => exitGame()
      case _ => game.players = game.removePlayer(playerToDelete)

  def getSpaceNameFromPlayerPosition(player: Player): SpaceName =
    gameBoard.gameBoardMap(player.actualPosition)
