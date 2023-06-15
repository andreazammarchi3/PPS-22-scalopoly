package PPS.scalopoly.utils.resources

enum FxmlResources(_path: String):
  case START_MENU extends FxmlResources("/fxml/StartMenuFXML.fxml")
  case CONFIGURATION_MENU extends FxmlResources("/fxml/ConfigurationMenuFXML.fxml")
  case GAME_VIEW extends FxmlResources("/fxml/GameFXML.fxml")

  val path: String = _path