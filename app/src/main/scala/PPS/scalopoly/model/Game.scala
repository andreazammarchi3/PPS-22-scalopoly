package PPS.scalopoly.model

class Game:

  private[this] var _currentPlayer: Option[Player] = None
  private[this] var _players: List[Player] = List.empty
  private[this] var _gameBoard: Map[Int, SpaceName] = Map.empty

  def currentPlayer: Option[Player] = _currentPlayer

  def currentPlayer_=(value: Option[Player]): Unit = {
    _currentPlayer = value
  }

  def players: List[Player] = _players

  def players_=(value: List[Player]): Unit = {
    _players = value
  }

  def gameBoard: Map[Int, SpaceName] = _gameBoard

  def gameBoard_=(value: Map[Int, SpaceName]): Unit = {
    _gameBoard = value
  }

  def addPlayer(newPlayer: Player): Unit =
    players = newPlayer :: players

