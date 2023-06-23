package PPS.scalopoly.utils

import PPS.scalopoly.engine.GameEngine
import PPS.scalopoly.model.{GameBoard, Player}

import scala.util.Random

/** A collection of utility methods used in the game.
  */
object GameUtils:
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
  def getCoordinateFromPosition(position: Int): (Int, Int) = position match
    case _ if position < 0 =>
      throw new IllegalArgumentException("Position cannot be negative")
    case _ if position >= GameBoard.size =>
      throw new IllegalArgumentException(
        "Position cannot be greater than board size"
      )
    case _ if position < 10 => (10 - position, 10)
    case _ if position < 20 => (0, 20 - position)
    case _ if position < 30 => (position - 20, 0)
    case _                  => (10, position - 30)

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
