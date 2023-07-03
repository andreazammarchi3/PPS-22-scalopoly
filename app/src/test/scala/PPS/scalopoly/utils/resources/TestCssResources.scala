package PPS.scalopoly.utils.resources

import org.junit.jupiter.api.Assertions.{
  assertEquals,
  assertNotNull,
  assertThrows
}
import org.junit.jupiter.api.Test

class TestCssResources:

  private val GAME_STYLE_POSITION = 0
  private val NUM_CSS_RESOURCES = 1
  @Test
  def testPath(): Unit =
    assertEquals("/css/GameStyle.css", CssResources.GAME_STYLE.path)

  @Test
  def testFromOrdinal(): Unit =
    assertEquals(
      CssResources.GAME_STYLE,
      CssResources.fromOrdinal(GAME_STYLE_POSITION)
    )
    assertThrows(
      classOf[NoSuchElementException],
      () => CssResources.fromOrdinal(CssResources.values.length + 1)
    )

  @Test
  def testGetResource(): Unit =
    assertNotNull(getClass.getResource(CssResources.GAME_STYLE.path))

  @Test
  def testValues(): Unit =
    assertEquals(NUM_CSS_RESOURCES, CssResources.values.length)

  @Test
  def testValueOf(): Unit =
    for (i <- CssResources.values.indices)
      assertEquals(
        CssResources.fromOrdinal(i),
        CssResources.valueOf(CssResources.fromOrdinal(i).toString)
      )
    assertThrows(
      classOf[IllegalArgumentException],
      () => CssResources.valueOf("NOT_EXIST")
    )

  @Test
  def testOrdinal(): Unit =
    assertEquals(GAME_STYLE_POSITION, CssResources.GAME_STYLE.ordinal)
