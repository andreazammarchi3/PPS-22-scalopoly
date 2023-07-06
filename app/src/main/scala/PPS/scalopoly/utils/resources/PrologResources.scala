package PPS.scalopoly.utils.resources

/** Enumeration of the Prolog resources used in the game.
  */
enum PrologResources(_path: String):
  case GAMEUTILS_PROLOG extends PrologResources("/prolog/GameUtilsProlog.pl")

  /** @return
    * the path of the Prolog resource.
    */
  val path: String = _path
