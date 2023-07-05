package PPS.scalopoly.model

/** Represents the status of a space in the board. A space can be not
  * purchasable, owned by another player, owned by the current player or
  * purchasable.
  */
enum SpaceStatus:
  case NOT_PURCHASABLE
  case OWNED_BY_ANOTHER_PLAYER
  case OWNED_BY_CURRENT_PLAYER
  case PURCHASABLE
