package PPS.scalopoly.engine.prolog

import PPS.scalopoly.Launcher.getClass
import PPS.scalopoly.model.Player
import PPS.scalopoly.utils.resources.PrologResources
import alice.tuprolog.{Prolog, Struct, Term, Theory}

import scala.io.Source

object PrologEngine:
  implicit def stringToTerm(s: String): Term = Term.createTerm(s)
  implicit def termToInt(t: Term): Int = t.toString.toInt
  implicit def termToListOfInt(t: Term): List[Int] =
    t.toString.replace("[", "").replace("]", "").split(",").map(_.toInt).toList

  private val engine = new Prolog

  private val isComment: String => Boolean = _(0) == "%" (0)

  /** Return the coordinate of the nth cell in a grid of gridSize dimensions.
    *
    * @param n
    *   The number of the cell which coordinates are to be returned.
    * @param gridSize
    *   The dimensions of the grid.
    * @return
    *   The coordinates of the nth cell.
    */
  def getNthCellInGrid(n: Int, gridSize: (Int, Int)): (Int, Int) =
    val SolX = "SolX"
    val SolY = "SolY"
    val goal = s"getNthCellInGrid($n, ${gridSize._1}, ${gridSize._1}, $SolY, $SolX)"
    resolveGameUtilsGoal(goal, SolY, SolX)

  /** Return the coordinates of a grid cell given the position of the player on the game board.
    *
    * @param position
    *   The position of the player on the game board.
    * @param cellsInSide
    *   The number of cells in a side of the grid.
    * @return
    *   The coordinates of the grid cell.
    */
  def getCoordinateFromPosition(position: Int, cellsInSide: Int): (Int, Int) =
    val SolX = "SolX"
    val SolY = "SolY"
    val goal = s"getCoordinateFromPosition($position, $cellsInSide, $SolY, $SolX)"
    resolveGameUtilsGoal(goal, SolY, SolX)

  /** Return the rents list of a property given the multiplier and the number of buildable houses.
    *
    * @param baseRent
    *   The base rent of the property.
    * @param houseMultiplier
    *   The multiplier of the property.
    * @param nHouses
    *   The number of buildable houses.
    * @return
    *   The rents list of the property.
    */
  def calculateRents(baseRent: Int, houseMultiplier: Int, nHouses: Int): List[Int] =
    val rents = "R"
    val goal = s"rents($baseRent, $houseMultiplier, $nHouses, $rents)"
    val theory = Source
      .fromInputStream(getClass.getResourceAsStream(PrologResources.RENTS_CALCULATOR_PROLOG.path))
      .getLines()
      .filter(l => l.nonEmpty && !isComment(l))
      .mkString(" ")
    engine.setTheory(Theory.parseWithStandardOperators(theory))
    val solution = engine.solve(goal)
    if solution.isSuccess then termToListOfInt(solution.getTerm(rents)).reverse
    else throw new RuntimeException(s"Prolog error while solving goal: $goal")

  private def resolveGameUtilsGoal(goal: String, sol1: String, sol2: String): (Int, Int) =
    val theory = Source
      .fromInputStream(getClass.getResourceAsStream(PrologResources.GAMEUTILS_PROLOG.path))
      .getLines()
      .filter(l => l.nonEmpty && !isComment(l))
      .mkString(" ")
    engine.setTheory(Theory.parseWithStandardOperators(theory))
    val solution = engine.solve(goal)
    if solution.isSuccess then (termToInt(solution.getTerm(sol1)), termToInt(solution.getTerm(sol2)))
    else throw new RuntimeException(s"Prolog error while solving goal: $goal")
