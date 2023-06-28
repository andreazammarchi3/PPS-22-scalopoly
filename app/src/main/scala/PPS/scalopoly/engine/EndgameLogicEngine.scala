package PPS.scalopoly.engine

import PPS.scalopoly.model.Player
import PPS.scalopoly.engine.GameEngine.{MIN_PLAYERS, Game}
import PPS.scalopoly.utils.FxmlUtils
import PPS.scalopoly.utils.resources.FxmlResources

object EndgameLogicEngine:

  /** Checks if the current player has won the game.
    *
    * @return
    *   true if the current player has won the game, false otherwise.
    */
  def checkVictory(): Boolean =
    checkVictoryForSurrender(GameEngine.players) match
      case true =>
        Game.winner = Some(Game.players.head)
        true
      case _ => false

  private def checkVictoryForSurrender(players: List[Player]): Boolean =
    players.length match
      case players if players == MIN_PLAYERS - 1 => true
      case _                                     => false
