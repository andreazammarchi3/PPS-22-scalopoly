package PPS.scalopoly.utils.resources

enum ImgResources(_path: String):
  case GAMEBOARD extends ImgResources("/img/Gameboard.png")
  case GAMEBOARD_SQUARED extends ImgResources("/img/GameboardSquare.png")
  case BOAT_TOKEN extends ImgResources("/img/token/Boat.png")
  case BOOT_TOKEN extends ImgResources("/img/token/Boot.png")
  case CAR_TOKEN extends ImgResources("/img/token/Car.png")
  case CAT_TOKEN extends ImgResources("/img/token/Cat.png")
  case DOG_TOKEN extends ImgResources("/img/token/Dog.png")
  case HAT_TOKEN extends ImgResources("/img/token/Hat.png")
  case THIMBLE_TOKEN extends ImgResources("/img/token/Thimble.png")
  case WHEELBARROW_TOKEN extends ImgResources("/img/token/Wheelbarrow.png")

  val path: String = _path