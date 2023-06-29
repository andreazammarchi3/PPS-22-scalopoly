package PPS.scalopoly.utils

import PPS.scalopoly.engine.GameEngine
import PPS.scalopoly.model.{GameBoard, Player, PurchasableSpace, SpaceName}

import scala.util.Random

/** A collection of utility methods used in the game.
  */
object GameUtils:

  val GAMEBOARD_SIDES = 4
  val CELLS_IN_SIDE = GameBoard.size / GAMEBOARD_SIDES

  /** Shuffles a list of players.
    * @param players
    *   List of players to shuffle.
    * @return
    *   Shuffled list of players.
    */
  def shufflePlayers(players: List[Player]): List[Player] =
    val shuffledList = Random.shuffle(players)
    shuffledList

  /** Return the new position of a player after a dice roll.
    * @param sum
    *   The sum of the dice roll.
    * @param position
    *   The current position of the player.
    * @return
    *   The new position of the player.
    */
  def addSumToPosition(sum: Int, position: Int): Int =
    sum + position match
      case result if result >= GameBoard.size => result - GameBoard.size
      case result                             => result

  /** Return the coordinates of a grid cell given the position of the player on
    * the game board.
    * @param position
    *   The position of the player on the game board.
    * @return
    *   The coordinates of the grid cell.
    */
  def getCoordinateFromPosition(position: Int): (Int, Int) =
    position match
      case _ if position < 0 =>
        throw new IllegalArgumentException("Position cannot be negative")
      case _ if position >= GameBoard.size =>
        throw new IllegalArgumentException(
          "Position cannot be greater than board size"
        )
      case _ if position < CELLS_IN_SIDE =>
        (CELLS_IN_SIDE - position, CELLS_IN_SIDE)
      case _ if position < CELLS_IN_SIDE * 2 =>
        (0, CELLS_IN_SIDE * 2 - position)
      case _ if position < CELLS_IN_SIDE * 3 =>
        (position - CELLS_IN_SIDE * 2, 0)
      case _ => (CELLS_IN_SIDE, position - CELLS_IN_SIDE * 3)

  /** Return the coordinate of the nth cell in a grid of gridSize dimensions,
    * starting from startingCell.
    * @param n
    *   The number of the cell which coordinates are to be returned.
    * @param gridSize
    *   The dimensions of the grid.
    * @param startingCell
    *   The starting cell of the grid.
    * @return
    *   The coordinates of the nth cell.
    */
  def getNthCellInGrid(
      n: Int,
      gridSize: (Int, Int),
      startingCell: (Int, Int)
  ): (Int, Int) = n match
    case _ if gridSize._1 <= 0 =>
      throw new IllegalArgumentException("Grid columns must be positive")
    case _ if gridSize._2 <= 0 =>
      throw new IllegalArgumentException("Grid rows must be positive")
    case _ if n <= 0 => throw new IllegalArgumentException("N must be positive")
    case _ if n > gridSize._1 * gridSize._2 =>
      throw new IllegalArgumentException("N cannot be greater than grid size")
    case _ if n % gridSize._1 != 0 => (n % gridSize._1 - 1, n / gridSize._1)
    case _                         => (gridSize._1 - 1, n / gridSize._1 - 1)

  /** Check if a property is already owned by a player.
    * @param purchasableSpace
    *   The property to check.
    * @return
    *   True if the property is already owned, false otherwise.
    */
  def propertyIsAlreadyOwned(
      purchasableSpace: PurchasableSpace
  ): Boolean =
    GameEngine.players.exists(_.ownedProperties.contains(purchasableSpace))

  /** Return the purchasable space given its name.
    *
    * @param spaceName
    *   The name of the purchasable space.
    * @return
    *   The purchasable space if it exists, None otherwise.
    */
  def getPurchasableSpaceFromSpaceName(
      spaceName: SpaceName
  ): Option[PurchasableSpace] =
    PurchasableSpace.values
      .find(_.spaceName == spaceName)

  def getOwnerFromPurchasableSpace(purchasableSpace: PurchasableSpace): Option[Player] =
    GameEngine.players.find(_.ownedProperties.contains(purchasableSpace))
