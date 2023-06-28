package PPS.scalopoly.utils.resources

/** Enumeration of all the FXML resources used in the application.
  */
enum FxmlResources(_path: String):
  case START_MENU extends FxmlResources("/fxml/StartMenuFXML.fxml")
  case CONFIGURATION_MENU
      extends FxmlResources("/fxml/ConfigurationMenuFXML.fxml")
  case GAME_VIEW extends FxmlResources("/fxml/GameFXML.fxml")

  /** @return
    *   the path of the FXML resource.
    */
  val path: String = _path
