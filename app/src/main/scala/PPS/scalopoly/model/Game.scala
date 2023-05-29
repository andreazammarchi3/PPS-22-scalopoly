package PPS.scalopoly.model

import PPS.scalopoly.utils.GameUtils

import scala.util.Random

class Game:
  private var _currentPlayer: Option[Player] = None
  private var _players: List[Player] = List.empty
  private var _availableTokens: List[Token] = Token.values.toList
  private val _gameBoard: GameBoard = new GameBoard
  private val dice = new Dice

  def currentPlayer: Option[Player] = _currentPlayer

  def currentPlayer_=(value: Option[Player]): Unit =
    _currentPlayer = value

  def players: List[Player] = _players

  def players_=(value: List[Player]): Unit =
    _players = value
  
  def gameBoard: GameBoard = _gameBoard

  def addPlayer(player: Player): Unit =
    players = player :: players
    val playerToken = player.token
    _availableTokens = _availableTokens.filterNot(elm => elm == playerToken)

  def removePlayer(player: Player): List[Player] =
    _availableTokens = _availableTokens.filterNot(elm => elm == player)
    _availableTokens = player.token :: _availableTokens
    players.filter(_ != player)

  def currentPlayerQuit(): Unit =
    _currentPlayer match
      case Some(player: Player) =>
        endTurn()
        _players = removePlayer(player)
      case _ => exitGame()

  def startGame(): Unit =
    players = GameUtils.shufflePlayers(players)
    currentPlayer = Some(players.head)

  def endTurn(): Unit =
    val currentIndex = players.indexOf(currentPlayer.get)
    val newIndex = (currentIndex + 1) % players.length
    currentPlayer = Some(players(newIndex))

  def movePlayer(): Unit =
    dice.rollDice()
    currentPlayer.get.actualPosition = 
      GameUtils.addSumToPosition(dice.sum(), currentPlayer.get.actualPosition, gameBoard)
      
  def getSpaceNameFromPlayerPosition(player: Player): SpaceName =
    gameBoard.gameBoardMap(player.actualPosition)

  def getAvailableTokens: List[Token] = _availableTokens

  def exitGame(): Unit =
    println("Partita terminata, alla prossima partita!")
    sys.exit(0)