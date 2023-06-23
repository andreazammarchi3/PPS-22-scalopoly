package PPS.scalopoly.utils.resources

/**
 * Enumeration of all the css resources used in the game.
 */
enum CssResources(_path: String):
  case START_MENU_STYLE extends CssResources("/css/StartMenuStyle.css")
  case GAME_STYLE extends CssResources("/css/GameStyle.css")

  /**
   * @return the path of the CSS resource.
   */
  val path: String = _path
