package PPS.scalopoly.utils.resources

/** Enumeration of the Prolog resources used in the game.
  */
enum PrologResources(_path: String):
  case GAMEUTILS_PROLOG extends PrologResources("/prolog/GameUtilsProlog.pl")
  case RENTS_CALCULATOR_PROLOG extends PrologResources("/prolog/RentsCalculatorProlog.pl")
  case CHANCE_CALCULATOR_PROLOG extends PrologResources("/prolog/ChanceCalculatorProlog.pl")

  /** @return
    *   the path of the Prolog resource.
    */
  val path: String = _path
