package PPS.scalopoly.controller

import PPS.scalopoly.model.{Dice, Game, GameBoard, Player, SpaceName}
import PPS.scalopoly.utils.GameUtils
import PPS.scalopoly.view.GameViewCLI

class GameController(game: Game, view: GameViewCLI, _dice: Dice = new Dice, _gameBoard: GameBoard = new GameBoard):

  def dice: Dice = _dice
  
  def gameBoard: GameBoard = _gameBoard
  
  def initialize(): Unit =
    view.setController(this)

  def run(): Unit =
    view.showGameBoard(game)
    view.showGameStart(game)

  def startGame(): Unit =
    game.players = GameUtils.shufflePlayers(game.players)
    game.currentPlayer = Some(game.players.head)

  def exitGame(): Unit =
    sys.exit(0)

  def endTurn(): Unit =
    val currentIndex = game.players.indexOf(game.currentPlayer.get)
    val newIndex = (currentIndex + 1) % game.players.length
    game.currentPlayer = Some(game.players(newIndex))

  def moveCurrentPlayer(): Unit =
    dice.rollDice()
    game.currentPlayer.get.actualPosition =
      GameUtils.addSumToPosition(dice.sum(), game.currentPlayer.get.actualPosition, gameBoard)

  def currentPlayerQuit(): Unit =
    val playerToDelete = game.currentPlayer.get
    endTurn()
    game.players.length match
      case 1 => exitGame()
      case _ => game.players = game.removePlayer(playerToDelete)

  def getSpaceNameFromPlayerPosition(player: Player): SpaceName =
    gameBoard.gameBoardMap(player.actualPosition)
