package PPS.scalopoly.utils.resources

enum ImgResources(_path: String):
  case GAMEBOARD extends ImgResources("/img/Gameboard.png")
  case GAMEBOARD_SQUARED extends ImgResources("/img/GameboardSquare.png")
  case IMG_TOKEN_NAVE extends ImgResources("/img/token/Nave.png")
  case IMG_TOKEN_STIVALE extends ImgResources("/img/token/Stivale.png")
  case IMG_TOKEN_AUTOMOBILE extends ImgResources("/img/token/Automobile.png")
  case IMG_TOKEN_GATTO extends ImgResources("/img/token/Gatto.png")
  case IMG_TOKEN_CANE extends ImgResources("/img/token/Cane.png")
  case IMG_TOKEN_CILINDRO extends ImgResources("/img/token/Cilindro.png")
  case IMG_TOKEN_DITALE extends ImgResources("/img/token/Ditale.png")
  case IMG_TOKEN_CARRIOLA extends ImgResources("/img/token/Carriola.png")

  val path: String = _path