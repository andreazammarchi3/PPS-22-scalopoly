package PPS.scalopoly.utils.resources

enum ImgResources(_path: String):
  case GAMEBOARD extends ImgResources("/img/Gameboard.png")
  case GAMEBOARD_SQUARED extends ImgResources("/img/GameboardSquare.png")
  case IMG_TOKEN_NAVE extends ImgResources("/img/token/Boat.png")
  case IMG_TOKEN_STIVALE extends ImgResources("/img/token/Boot.png")
  case IMG_TOKEN_AUTOMOBILE extends ImgResources("/img/token/Car.png")
  case IMG_TOKEN_GATTO extends ImgResources("/img/token/Cat.png")
  case IMG_TOKEN_CANE extends ImgResources("/img/token/Dog.png")
  case IMG_TOKEN_CILINDRO extends ImgResources("/img/token/Hat.png")
  case IMG_TOKEN_DITALE extends ImgResources("/img/token/Thimble.png")
  case IMG_TOKEN_CARRIOLA extends ImgResources("/img/token/Wheelbarrow.png")

  val path: String = _path