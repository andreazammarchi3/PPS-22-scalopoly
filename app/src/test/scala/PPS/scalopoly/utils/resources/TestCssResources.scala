package PPS.scalopoly.utils.resources

import org.junit.jupiter.api.Assertions.{assertEquals, assertNotNull, assertTrue}
import org.junit.jupiter.api.Test

import PPS.scalopoly.Utils

class TestCssResources :

  @Test
  def testPath(): Unit =
    assertEquals("/css/GameStyle.css" , CssResources.GAME_STYLE.path)
    assertEquals("/css/StartMenuStyle.css" , CssResources.START_MENU_STYLE.path)

  @Test
  def testFromOrdinal(): Unit =
    assertEquals(CssResources.START_MENU_STYLE, CssResources.fromOrdinal(0))
    assertEquals(CssResources.GAME_STYLE, CssResources.fromOrdinal(1))
    assertTrue(Utils.testCatchException[IllegalArgumentException, Int, CssResources](CssResources.fromOrdinal, CssResources.values.length + 1))

  @Test
  def testGetResource(): Unit =
    assertNotNull(getClass.getResource(CssResources.START_MENU_STYLE.path))
    assertNotNull(getClass.getResource(CssResources.GAME_STYLE.path))

  @Test
  def testValues(): Unit =
    assertEquals(2, CssResources.values.length)

  @Test
  def testValueOf(): Unit =
    assertEquals(CssResources.START_MENU_STYLE, CssResources.valueOf("START_MENU_STYLE"))
    assertEquals(CssResources.GAME_STYLE, CssResources.valueOf("GAME_STYLE"))
    assertTrue(Utils.testCatchException[IllegalArgumentException, String, CssResources](CssResources.valueOf, "NOT_EXIST"))

  @Test
  def testOrdinal(): Unit =
    assertEquals(0, CssResources.START_MENU_STYLE.ordinal)
    assertEquals(1, CssResources.GAME_STYLE.ordinal)
