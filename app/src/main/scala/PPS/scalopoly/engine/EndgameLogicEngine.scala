package PPS.scalopoly.engine

import PPS.scalopoly.model.Player
import PPS.scalopoly.engine.GameEngine.MIN_PLAYERS
import PPS.scalopoly.utils.FxmlUtils
import PPS.scalopoly.utils.resources.FxmlResources

object EndgameLogicEngine:

  def checkVictoryForSurrender(players: List[Player]): Boolean =
    players.length match
      case players if players == MIN_PLAYERS - 1 => true
      case _                                     => false

  def showVictory(): Unit =
    FxmlUtils.changeScene(FxmlResources.VICTORY_VIEW.path)
