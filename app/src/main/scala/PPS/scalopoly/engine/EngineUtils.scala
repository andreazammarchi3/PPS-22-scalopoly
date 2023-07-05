package PPS.scalopoly.engine

import PPS.scalopoly.model.Player
import PPS.scalopoly.model.space.purchasable.BuildableSpace

/** A utility object for the engines.
  */
protected[engine] object EngineUtils:

  /** Updates the player with the given index with the given player.
    *
    * @param index
    *   the index of the player to update
    * @param playerUpdated
    *   the updated player
    */
  def updatePlayerWith(index: Int, playerUpdated: Player): Unit =
    Game.players = Game.players.updated(index, playerUpdated)

  /** Updates the game board with the given space.
    *
    * @param updatedSpace
    *   the updated space
    */
  def updateBuildableSpacesWith(updatedSpace: BuildableSpace): Unit =
    Game.gameBoard = Game.gameBoard.updateBuildableSpace(updatedSpace)
