package PPS.scalopoly.engine

private[engine] enum SpaceStatus:
  case NOT_PURCHASABLE
  case OWNED_BY_ANOTHER_PLAYER
  case OWNED_BY_CURRENT_PLAYER
  case PURCHASABLE
