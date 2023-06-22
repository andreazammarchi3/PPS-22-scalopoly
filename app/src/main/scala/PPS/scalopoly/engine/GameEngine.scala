package PPS.scalopoly.engine

import PPS.scalopoly.model.*
import PPS.scalopoly.utils.GameUtils

object GameEngine:
  private val diceManager: DiceManager = DiceManager()

  def players: List[Player] = Game.players

  def currentPlayer: Player = players(Game.currentPlayer)

  def availableTokens(): List[Token] =
    Game.availableTokens

  def addPlayer(player: Player): Unit =
    Game.addPlayer(player)

  def removePlayer(player: Player): Unit =
    Game.removePlayer(player)

  def canStartGame(): Boolean =
    Game.players.length match
      case i if i < 1 => false
      case _ => true

  def canAddPlayer(): Boolean =
    Game.players.length match
      case i if i < 8 => true
      case _ => false

  def startGame(): Unit =
    Game.players = GameUtils.shufflePlayers(Game.players)

  def newGame(): Unit =
    Game.reset()

  def exitGame(): Unit =
    sys.exit(0)

  def endTurn(): Unit =
    Game.currentPlayer = (Game.currentPlayer + 1) % Game.players.length

  def moveCurrentPlayer(): (Int, Int) =
    val (dice1, dice2) = diceManager.roll()
    Game.players = Game.players.updated(
      Game.currentPlayer,
      currentPlayer.move(dice1 + dice2)
    )
    (dice1, dice2)

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
