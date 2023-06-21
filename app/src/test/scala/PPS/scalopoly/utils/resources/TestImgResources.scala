package PPS.scalopoly.utils.resources

import org.junit.jupiter.api.Assertions.{
  assertEquals,
  assertNotNull,
  assertTrue
}
import org.junit.jupiter.api.Test

import PPS.scalopoly.Utils
class TestImgResources:

  @Test
  def testPath(): Unit =
    assertEquals("/img/Gameboard.png", ImgResources.GAMEBOARD.path)
    assertEquals("/img/token/Cane.png", ImgResources.IMG_TOKEN_CANE.path)

  @Test
  def testFromOrdinal(): Unit =
    assertEquals(ImgResources.DICE_4, ImgResources.fromOrdinal(13))
    assertEquals(ImgResources.IMG_TOKEN_STIVALE, ImgResources.fromOrdinal(3))
    assertTrue(
      Utils.testCatchException[IllegalArgumentException, Int, ImgResources](
        ImgResources.fromOrdinal,
        ImgResources.values.length + 1
      )
    )

  @Test
  def testGetResource(): Unit =
    assertNotNull(getClass.getResource(ImgResources.DICE_1.path))
    assertNotNull(getClass.getResource(ImgResources.IMG_TOKEN_DITALE.path))

  @Test
  def testValues(): Unit =
    assertEquals(16, ImgResources.values.length)

  @Test
  def testValueOf(): Unit =
    for (i <- ImgResources.values.indices)
      assertEquals(
        ImgResources.fromOrdinal(i),
        ImgResources.valueOf(ImgResources.fromOrdinal(i).toString)
      )
    assertTrue(
      Utils.testCatchException[IllegalArgumentException, String, ImgResources](
        ImgResources.valueOf,
        "NOT_EXIST"
      )
    )

  @Test
  def testOrdinal(): Unit =
    assertEquals(5, ImgResources.IMG_TOKEN_GATTO.ordinal)
    assertEquals(15, ImgResources.DICE_6.ordinal)
