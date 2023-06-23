package PPS.scalopoly.model

import PPS.scalopoly.utils.resources.ImgResources

/**
 * Represents a token that can be used by a player to move on the board.
 */
enum Token(_img: ImgResources):
  case DITALE extends Token(ImgResources.IMG_TOKEN_DITALE)
  case AUTOMOBILE extends Token(ImgResources.IMG_TOKEN_AUTOMOBILE)
  case NAVE extends Token(ImgResources.IMG_TOKEN_NAVE)
  case CILINDRO extends Token(ImgResources.IMG_TOKEN_CILINDRO)
  case CARRIOLA extends Token(ImgResources.IMG_TOKEN_CARRIOLA)
  case STIVALE extends Token(ImgResources.IMG_TOKEN_STIVALE)
  case GATTO extends Token(ImgResources.IMG_TOKEN_GATTO)
  case CANE extends Token(ImgResources.IMG_TOKEN_CANE)

  /**
   * @return the image resource of the token
   */
  val img: ImgResources = _img
