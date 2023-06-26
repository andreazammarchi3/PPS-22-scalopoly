package PPS.scalopoly.utils.resources

import PPS.scalopoly.Utils
import org.junit.jupiter.api.Assertions.{
  assertEquals,
  assertNotNull,
  assertTrue
}
import org.junit.jupiter.api.Test

class TestFxmlResources:

  private val START_MENU_POSITION = 0
  private val CONFIGURATION_MENU_POSITION = 1
  private val GAME_VIEW_POSITION = 2
  private val NUM_FXML_RESOURCES = 4
  @Test
  def testPath(): Unit =
    assertEquals("/fxml/StartMenuFXML.fxml", FxmlResources.START_MENU.path)
    assertEquals("/fxml/GameFXML.fxml", FxmlResources.GAME_VIEW.path)

  @Test
  def testFromOrdinal(): Unit =
    assertEquals(
      FxmlResources.START_MENU,
      FxmlResources.fromOrdinal(START_MENU_POSITION)
    )
    assertEquals(
      FxmlResources.GAME_VIEW,
      FxmlResources.fromOrdinal(GAME_VIEW_POSITION)
    )
    assertTrue(
      Utils.testCatchException[IllegalArgumentException, Int, FxmlResources](
        FxmlResources.fromOrdinal,
        FxmlResources.values.length + 1
      )
    )

  @Test
  def testGetResource(): Unit =
    assertNotNull(getClass.getResource(FxmlResources.START_MENU.path))
    assertNotNull(getClass.getResource(FxmlResources.GAME_VIEW.path))

  @Test
  def testValues(): Unit =
    assertEquals(NUM_FXML_RESOURCES, FxmlResources.values.length)

  @Test
  def testValueOf(): Unit =
    for (i <- FxmlResources.values.indices)
      assertEquals(
        FxmlResources.fromOrdinal(i),
        FxmlResources.valueOf(FxmlResources.fromOrdinal(i).toString)
      )
    assertTrue(
      Utils.testCatchException[IllegalArgumentException, String, FxmlResources](
        FxmlResources.valueOf,
        "NOT_EXIST"
      )
    )

  @Test
  def testOrdinal(): Unit =
    assertEquals(START_MENU_POSITION, FxmlResources.START_MENU.ordinal)
    assertEquals(
      CONFIGURATION_MENU_POSITION,
      FxmlResources.CONFIGURATION_MENU.ordinal
    )
