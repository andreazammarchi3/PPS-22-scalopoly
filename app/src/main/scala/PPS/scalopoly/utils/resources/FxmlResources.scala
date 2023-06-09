package PPS.scalopoly.utils.resources

enum FxmlResources(_path: String):
  case START_MENU extends FxmlResources("/fxml/StartMenuFXML.fxml")
  case GAME_VIEW extends FxmlResources("/fxml/GameViewFXML.fxml")

  val path: String = _path