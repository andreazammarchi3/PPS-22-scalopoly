package PPS.scalopoly.model

class Game:

  private[this] var _currentPlayer: Option[Player] = None
  private[this] var _players: List[Player] = List.empty
  private[this] val _gameBoard: GameBoard = new GameBoard

  def currentPlayer: Option[Player] = _currentPlayer

  def currentPlayer_=(value: Option[Player]): Unit = {
    _currentPlayer = value
  }

  def players: List[Player] = _players

  def players_=(value: List[Player]): Unit = {
    _players = value
  }
  
  def gameBoard: GameBoard = _gameBoard

  def addPlayer(newPlayer: Player): Unit =
    players = newPlayer :: players

