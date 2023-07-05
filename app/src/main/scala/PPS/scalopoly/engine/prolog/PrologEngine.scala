package PPS.scalopoly.engine.prolog

import alice.tuprolog.*

/** Represents an engine based on multiple theories designed to solve goals. */
trait PrologEngine:

  /**
   * Processes a goal with the registered theories and sees whether it is accepted or not.
   * @param goal goal to solve
   * @return true if the goal is accepted, false otherwise
   */
  def solveWithSuccess(goal: Term): Boolean

  /**
   * Processes a goal with the registered theories and, if a solution is found, gives the accepted value
   * of the specified term.
   * @param goal goal to solve
   * @param term name of the [[Term]] that should be found
   * @return the value of the [[Term]] that makes the goal acceptable
   */
  def solveOneAndGetTerm(goal: Term, term: String): Term

  /**
   * Processes a goal with the registered theories and, if a solution is found, gives the accepted values
   * of the specified terms.
   * @param goal goal to solve
   * @param terms list of the names of the [[Term]]s that should be found
   * @return the map of the values of [[Term]]s that make the goal acceptable
   */
  def solveOneAndGetTerms(goal: Term, terms: String*): Map[String, Term]

  /**
   * Processes a goal with the registered theories and, if at least one solution is found, gives the accepted values
   * of the specified terms of each of the found solutions.
   * @param goal goal to solve
   * @param terms list of the names of the [[Term]]s that should be found
   * @return the list of the map of the values of [[Term]]s that make the goal acceptable
   */
  def solveAll(goal: Term, terms: String*): LazyList[Map[String, Term]]

  /**
   * Processes a goal with the registered theories and, if at least one solution is found, gives the accepted value
   * of the specified term of each of the found solutions.
   * @param goal goal to solve
   * @param term name of the [[Term]] that should be found
   * @return the list of the values of the [[Term]] that make the goal acceptable
   */
  def solveAll(goal: Term, term: String): LazyList[Term]

/** Object helper for [[PrologEngine]]. */
object PrologEngine:

  /** [[Conversion]] to convert from [[String]] to [[Term]]. */
  given Conversion[String, Term] = Term.createTerm(_)

  /** [[Conversion]] to convert from [[Seq]] of [[Any]] to [[Term]]. */
  given Conversion[Seq[_], Term] = _.mkString("[", ",", "]")

  /** [[Conversion]] to convert from [[String]] to [[Theory]]. */
  given Conversion[String, Theory] = fileName =>
    Theory.parseLazilyWithStandardOperators(getClass.getResourceAsStream(fileName))

  /**
   * Creates a new [[PrologEngine]] using one or more [[Theory]].
   * @param theories one or more [[Theory]]
   * @return a new fresh [[PrologEngine]]
   */
  def apply(theories: Theory*): PrologEngine = PrologEngineImpl(theories*)

  private case class PrologEngineImpl(theories: Theory*) extends PrologEngine:

    private val engine: Term => LazyList[SolveInfo] =
      val prolog = Prolog()
      theories.foreach(prolog.addTheory)
      goal =>
        new Iterable[SolveInfo] {

          override def iterator: Iterator[SolveInfo] = new Iterator[SolveInfo]:
            var solution: Option[SolveInfo] = Some(prolog.solve(goal))

            override def hasNext: Boolean = solution match
              case Some(value) => value.isSuccess || value.hasOpenAlternatives
              case None        => false

            override def next(): SolveInfo =
              val sol = solution match
                case Some(value) => value
                case None        => throw Exception()
              solution = if (sol.hasOpenAlternatives) Some(prolog.solveNext()) else None
              sol
        }.to(LazyList)

    override def solveWithSuccess(goal: Term): Boolean =
      engine(goal).map(_.isSuccess).headOption.contains(true)

    override def solveOneAndGetTerm(goal: Term, term: String): Term =
      engine(goal).headOption.map(extractTerm(_, term)) match
        case Some(value) => value
        case None        => "no."

    override def solveOneAndGetTerms(goal: Term, terms: String*): Map[String, Term] =
      engine(goal).headOption match
        case Some(value) => terms.map(term => (term, extractTerm(value, term))).toMap
        case None        => Map.empty

    override def solveAll(goal: Term, terms: String*): LazyList[Map[String, Term]] =
      engine(goal).map(solution =>
        (for (term <- terms)
          yield (term, extractTerm(solution, term))).toMap
      )

    override def solveAll(goal: Term, term: String): LazyList[Term] =
      engine(goal).map(extractTerm(_, term))

    private def extractTerm(solveInfo: SolveInfo, s: String): Term =
      solveInfo.getTerm(s)