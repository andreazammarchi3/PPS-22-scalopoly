package PPS.scalopoly.utils.resources

import org.junit.jupiter.api.Assertions.{
  assertEquals,
  assertNotNull,
  assertTrue
}
import org.junit.jupiter.api.Test

import PPS.scalopoly.Utils
class TestImgResources:

  private val DICE_4_POSITION = 13
  private val DICE_6_POSITION = 15
  private val IMG_TOKEN_STIVALE_POSITION = 3
  private val IMG_TOKEN_GATTO_POSITION = 5
  private val NUM_IMG_RESOURCES = 16
  @Test
  def testPath(): Unit =
    assertEquals("/img/Gameboard.png", ImgResources.GAMEBOARD.path)
    assertEquals("/img/token/Cane.png", ImgResources.IMG_TOKEN_CANE.path)

  @Test
  def testFromOrdinal(): Unit =
    assertEquals(ImgResources.DICE_4, ImgResources.fromOrdinal(DICE_4_POSITION))
    assertEquals(
      ImgResources.IMG_TOKEN_STIVALE,
      ImgResources.fromOrdinal(IMG_TOKEN_STIVALE_POSITION)
    )
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
    assertEquals(NUM_IMG_RESOURCES, ImgResources.values.length)

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
    assertEquals(IMG_TOKEN_GATTO_POSITION, ImgResources.IMG_TOKEN_GATTO.ordinal)
    assertEquals(DICE_6_POSITION, ImgResources.DICE_6.ordinal)
