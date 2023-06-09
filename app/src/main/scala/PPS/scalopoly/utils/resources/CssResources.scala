package PPS.scalopoly.utils.resources

enum CssResources(_path: String):
  case START_MENU_STYLE extends CssResources("/css/StartMenuStyle.css")

  val path: String = _path