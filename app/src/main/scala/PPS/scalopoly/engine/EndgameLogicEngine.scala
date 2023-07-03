package PPS.scalopoly.engine

import PPS.scalopoly.model.Player
import PPS.scalopoly.engine.GameEngine.MIN_PLAYERS
import PPS.scalopoly.engine.Game
import PPS.scalopoly.utils.FxmlUtils
import PPS.scalopoly.utils.resources.FxmlResources

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
