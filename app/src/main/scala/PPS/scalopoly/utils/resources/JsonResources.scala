package PPS.scalopoly.utils.resources

/** Enumeration of the JSON resources used in the game.
  */
enum JsonResources(_path: String):
  case BUILDABLE_SPACES extends JsonResources("/json/buildableSpaces.json")
  case COMPANY_SPACES extends JsonResources("/json/companySpaces.json")
  case STATION_SPACES extends JsonResources("/json/stationSpaces.json")
  case NOT_PURCHASABLE_SPACES extends JsonResources("/json/notPurchasableSpaces.json")
  case SPACES extends JsonResources("/json/spaces.json")

  /** @return
    *   the path of the JSON resource.
    */
  val path: String = _path
