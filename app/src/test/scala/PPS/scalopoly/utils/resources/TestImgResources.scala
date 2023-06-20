package PPS.scalopoly.utils.resources

import org.junit.jupiter.api.Assertions.{assertEquals, assertNotNull, assertTrue}
import org.junit.jupiter.api.Test

import PPS.scalopoly.Utils
class TestImgResources :

  @Test
  def testPath(): Unit =
    assertEquals("/img/Gameboard.png", ImgResources.GAMEBOARD.path)
    assertEquals("/img/token/Cane.png", ImgResources.IMG_TOKEN_CANE.path)

  @Test
  def testFromOrdinal(): Unit =
    assertEquals(ImgResources.DICE_4, ImgResources.fromOrdinal(13))
    assertEquals(ImgResources.IMG_TOKEN_STIVALE, ImgResources.fromOrdinal(3))
    assertTrue(Utils.testCatchException[IllegalArgumentException, Int, ImgResources](ImgResources.fromOrdinal, ImgResources.values.length + 1))

  @Test
  def testGetResource(): Unit =
    assertNotNull(getClass.getResource(ImgResources.DICE_1.path))
    assertNotNull(getClass.getResource(ImgResources.IMG_TOKEN_DITALE.path))

  @Test
  def testValues(): Unit =
    assertEquals(16, ImgResources.values.length)

  @Test
  def testValueOf(): Unit =
    assertEquals(ImgResources.GAMEBOARD, ImgResources.valueOf("GAMEBOARD"))
    assertEquals(ImgResources.GAMEBOARD_SQUARED, ImgResources.valueOf("GAMEBOARD_SQUARED"))
    assertEquals(ImgResources.IMG_TOKEN_NAVE, ImgResources.valueOf("IMG_TOKEN_NAVE"))
    assertEquals(ImgResources.IMG_TOKEN_STIVALE, ImgResources.valueOf("IMG_TOKEN_STIVALE"))
    assertEquals(ImgResources.IMG_TOKEN_AUTOMOBILE, ImgResources.valueOf("IMG_TOKEN_AUTOMOBILE"))
    assertEquals(ImgResources.IMG_TOKEN_GATTO, ImgResources.valueOf("IMG_TOKEN_GATTO"))
    assertEquals(ImgResources.IMG_TOKEN_CANE, ImgResources.valueOf("IMG_TOKEN_CANE"))
    assertEquals(ImgResources.IMG_TOKEN_CILINDRO, ImgResources.valueOf("IMG_TOKEN_CILINDRO"))
    assertEquals(ImgResources.IMG_TOKEN_DITALE, ImgResources.valueOf("IMG_TOKEN_DITALE"))
    assertEquals(ImgResources.IMG_TOKEN_CARRIOLA, ImgResources.valueOf("IMG_TOKEN_CARRIOLA"))
    assertEquals(ImgResources.DICE_1, ImgResources.valueOf("DICE_1"))
    assertEquals(ImgResources.DICE_2, ImgResources.valueOf("DICE_2"))
    assertEquals(ImgResources.DICE_3, ImgResources.valueOf("DICE_3"))
    assertEquals(ImgResources.DICE_4, ImgResources.valueOf("DICE_4"))
    assertEquals(ImgResources.DICE_5, ImgResources.valueOf("DICE_5"))
    assertEquals(ImgResources.DICE_6, ImgResources.valueOf("DICE_6"))
    assertTrue(Utils.testCatchException[IllegalArgumentException, String, ImgResources](ImgResources.valueOf, "NOT_EXIST"))

  @Test
  def testOrdinal(): Unit =
    assertEquals(5, ImgResources.IMG_TOKEN_GATTO.ordinal)
    assertEquals(15, ImgResources.DICE_6.ordinal)