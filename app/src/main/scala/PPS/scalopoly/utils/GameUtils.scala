package PPS.scalopoly.utils

import PPS.scalopoly.engine.GameReader
import PPS.scalopoly.engine.prolog.PrologEngine
import PPS.scalopoly.model.space.Space
import PPS.scalopoly.model.space.notPurchasable.NotPurchasableSpace
import PPS.scalopoly.model.space.purchasable.{BuildableSpace, PurchasableSpace}
import PPS.scalopoly.model.{GameBoard, Player, SpaceGroup}

import scala.util.Random

/** A collection of utility methods used in the game.
  */
object GameUtils:

  val GAMEBOARD_SIDES = 4
  val CELLS_IN_SIDE: Int = GameReader.gameBoard.size / GAMEBOARD_SIDES

  /** Shuffles a list of players.
    * @param players
    *   List of players to shuffle.
    * @return
    *   Shuffled list of players.
    */
  def shufflePlayers(players: List[Player]): List[Player] =
    val shuffledList = Random shuffle players
    shuffledList

  /** Return the new position of a player after a dice roll.
    * @param sum
    *   The sum of the dice roll.
    * @param position
    *   The current position of the player.
    * @return
    *   The new position of the player.
    */
  def addSumToPosition(sum: Int, position: Int): Int = sum + position match
    case result if result >= GameReader.gameBoard.size => result - GameReader.gameBoard.size
    case result                                        => result

  /** Return the coordinates of a grid cell given the position of the player on the game board.
    * @param position
    *   The position of the player on the game board.
    * @return
    *   The coordinates of the grid cell.
    */
  def getCoordinateFromPosition(position: Int): (Int, Int) = position match
    case _ if position < 0 => throw new IllegalArgumentException("Position cannot be negative")
    case _ if position >= GameReader.gameBoard.size =>
      throw new IllegalArgumentException("Position cannot be greater than board size")
    case _ => PrologEngine.getCoordinateFromPosition(position, CELLS_IN_SIDE)

  /** Return the coordinate of the nth cell in a grid of gridSize dimensions, starting from startingCell.
    * @param n
    *   The number of the cell which coordinates are to be returned.
    * @param gridSize
    *   The dimensions of the grid.
    * @param startingCell
    *   The starting cell of the grid.
    * @return
    *   The coordinates of the nth cell.
    */
  def getNthCellInGridWithStartingPos(n: Int, gridSize: (Int, Int), startingCell: (Int, Int)): (Int, Int) = n match
    case _ if gridSize._1 <= 0              => throw new IllegalArgumentException("Grid columns must be positive")
    case _ if gridSize._2 <= 0              => throw new IllegalArgumentException("Grid rows must be positive")
    case _ if n <= 0                        => throw new IllegalArgumentException("N must be positive")
    case _ if n > gridSize._1 * gridSize._2 => throw new IllegalArgumentException("N cannot be greater than grid size")
    case _ =>
      PrologEngine.getNthCellInGrid(n + (startingCell._1 * gridSize._2 + startingCell._2 * gridSize._1), gridSize)

  /** Check if a property is already owned by a player.
    * @param purchasableSpace
    *   The property to check.
    * @return
    *   True if the property is already owned, false otherwise.
    */
  def propertyIsAlreadyOwned(purchasableSpace: PurchasableSpace): Boolean =
    GameReader.players.exists(_.ownedProperties.contains(purchasableSpace))

  /** Returns the owner of a purchasable space if it exists.
    *
    * @param purchasableSpace
    *   the purchasable space.
    * @return
    *   the owner of the purchasable space or None otherwise.
    */
  def getOwnerFromPurchasableSpace(purchasableSpace: PurchasableSpace): Option[Player] =
    GameReader.players.find(_.ownedProperties.contains(purchasableSpace))

  /** Returns the purchasable space where the player is if the player is on a purchasable space.
    *
    * @param player
    *   the player.
    * @return
    *   the purchasable space where the player is or None otherwise.
    */
  def getPurchasableSpaceFromPlayerPosition(player: Player): Option[PurchasableSpace] =
    GameReader.gameBoard.purchasableSpaces.find(
      _.name == GameReader.gameBoard.gameBoardList(player.actualPosition).name
    )

  /** Returns the not purchasable space where the player is if the player is on a not purchasable space.
    *
    * @param player
    *   the player.
    * @return
    *   the not purchasable space where the player is or None otherwise.
    */
  def getNotPurchasableSpaceFromPlayerPosition(player: Player): Option[NotPurchasableSpace] =
    GameReader.gameBoard.notPurchasableSpaces.find(
      _.name == GameReader.gameBoard.gameBoardList(player.actualPosition).name
    )

  /** Checks if all the properties of a space group are owned by the same player.
    *
    * @param spaceGroup
    *   the space group to check.
    * @return
    *   true if all the properties of the space group are owned by the same player, false otherwise.
    */
  def checkIfPlayerOwnsAllPropertiesOfSameGroup(spaceGroup: SpaceGroup): Boolean =
    val propertiesOfSameGroup = GameReader.gameBoard.purchasableSpaces.count(_.spaceGroup == spaceGroup)
    GameReader.players.exists(p => p.ownedProperties.count(_.spaceGroup == spaceGroup) == propertiesOfSameGroup)

  /** Return a buildable space given its name.
    * @param name
    *   the name of the buildable space.
    * @return
    *   the buildable space with the given name or None if it doesn't exist.
    */
  def getBuildableSpaceFromName(name: String): Option[BuildableSpace] =
    GameReader.gameBoard.buildableSpaces.find(_.name == name)

  /** Returns the number of stations owned by the owner of the actual position of the player.
    *
    * @param stationSpaceGroup
    *   the space group of the stations.
    * @return
    *   the number of stations owned by the owner of the actual position of the player.
    */
  def getNumStationFromOwner(stationSpaceGroup: SpaceGroup, owner: Player): Int =
    owner.ownedProperties.count(_.spaceGroup == stationSpaceGroup)
