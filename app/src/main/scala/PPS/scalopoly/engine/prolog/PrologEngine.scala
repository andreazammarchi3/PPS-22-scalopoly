package PPS.scalopoly.engine.prolog

import PPS.scalopoly.Launcher.getClass
import PPS.scalopoly.model.Player
import PPS.scalopoly.utils.resources.PrologResources
import alice.tuprolog.{Prolog, Struct, Term, Theory}

import scala.io.Source

object PrologEngine:
  implicit def stringToTerm(s: String): Term = Term.createTerm(s)
  implicit def termToInt(t: Term): Int = t.toString.toInt

  private val engine = new Prolog

  private val isComment: String => Boolean = _(0) == "%" (0)

  def getNthCellInGrid(n: Int, gridSize: (Int, Int)): (Int, Int) =
    val SolX = "SolX"
    val SolY = "SolY"
    val goal = s"getNthCellInGrid($n, ${gridSize._1}, ${gridSize._1}, $SolY, $SolX)"
    val theory = Source
      .fromInputStream(getClass.getResourceAsStream(PrologResources.GAMEUTILS_PROLOG.path))
      .getLines()
      .filter(l => l.nonEmpty && !isComment(l))
      .mkString(" ")
    engine.setTheory(Theory.of(theory))
    val solution = engine.solve(goal)
    if solution.isSuccess then (termToInt(solution.getTerm(SolY)), termToInt(solution.getTerm(SolX)))
    else throw new RuntimeException(s"Prolog error while solving goal: $goal")
