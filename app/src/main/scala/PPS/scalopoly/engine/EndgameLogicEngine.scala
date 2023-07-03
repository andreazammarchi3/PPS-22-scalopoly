package PPS.scalopoly.engine

import PPS.scalopoly.model.Player
import PPS.scalopoly.engine.GameEngine.MIN_PLAYERS
import PPS.scalopoly.engine.Game

/** Object that contains the logic for the endgame. It checks if there is a
  * winner and, if so, it sets the winner in the [[Game]] object.
  */
object EndgameLogicEngine:

  /** Checks if the current player has won the game.
    *
    * @return
    *   true if the current player has won the game, false otherwise.
    */
  def checkVictory(): Boolean =
    if (checkVictoryForSurrender(GameEngine.players))
      Game.winner = Game.players.headOption
      true
    else false

  private def checkVictoryForSurrender(players: List[Player]): Boolean =
    players.length match
      case players if players == MIN_PLAYERS - 1 => true
      case _                                     => false
