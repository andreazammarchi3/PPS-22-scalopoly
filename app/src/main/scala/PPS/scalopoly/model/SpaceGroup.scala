package PPS.scalopoly.model

enum SpaceGroup(name: String):
  case ROSA extends SpaceGroup("Gruppo Rosa")
  case CELESTE extends SpaceGroup("Gruppo Celeste")
  case ARANCIONE extends SpaceGroup("Gruppo Arancione")
  case MARRONE extends SpaceGroup("Gruppo Marrone")
  case ROSSO extends SpaceGroup("Gruppo Rosso")
  case GIALLO extends SpaceGroup("Gruppo Giallo")
  case VERDE extends SpaceGroup("Gruppo Verde")
  case VIOLA extends SpaceGroup("Gruppo Viola")
  case COMPAGNIA_ELETTRICA extends SpaceGroup("Gruppo Compagnia Elettrica")
  case FERROVIARIO extends SpaceGroup("Gruppo Ferroviario")
  case NO_GROUP extends SpaceGroup("")
