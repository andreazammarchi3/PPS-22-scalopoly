package PPS.scalopoly.model

class Game:

  var currentPlayer: Option[Player] = None
  var players: List[Player] = List.empty
  var gameBoard: Map[Integer, SpaceName] = Map.empty

  def addPlayer(newPlayer: Player): Unit =
    players = newPlayer :: players
