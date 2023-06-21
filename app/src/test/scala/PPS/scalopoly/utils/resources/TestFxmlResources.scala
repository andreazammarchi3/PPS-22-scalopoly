package PPS.scalopoly.utils.resources

import PPS.scalopoly.Utils
import org.junit.jupiter.api.Assertions.{assertEquals, assertNotNull, assertTrue}
import org.junit.jupiter.api.Test

class TestFxmlResources :

  @Test
  def testPath(): Unit =
    assertEquals("/fxml/StartMenuFXML.fxml" , FxmlResources.START_MENU.path)
    assertEquals("/fxml/GameFXML.fxml" , FxmlResources.GAME_VIEW.path)

  @Test
  def testFromOrdinal(): Unit =
    assertEquals(FxmlResources.START_MENU, FxmlResources.fromOrdinal(0))
    assertEquals(FxmlResources.GAME_VIEW, FxmlResources.fromOrdinal(1))
    assertTrue(Utils.testCatchException[IllegalArgumentException, Int, FxmlResources](FxmlResources.fromOrdinal, FxmlResources.values.length + 1))

  @Test
  def testGetResource(): Unit =
    assertNotNull(getClass.getResource(FxmlResources.START_MENU.path))
    assertNotNull(getClass.getResource(FxmlResources.GAME_VIEW.path))

  @Test
  def testValues(): Unit =
    assertEquals(2, FxmlResources.values.length)

  @Test
  def testValueOf(): Unit =
    for (i <- FxmlResources.values.indices)
      assertEquals(FxmlResources.fromOrdinal(i), FxmlResources.valueOf(FxmlResources.fromOrdinal(i).toString))
    assertTrue(Utils.testCatchException[IllegalArgumentException, String, FxmlResources](FxmlResources.valueOf, "NOT_EXIST"))

  @Test
  def testOrdinal(): Unit =
    assertEquals(0, FxmlResources.START_MENU.ordinal)
    assertEquals(1, FxmlResources.GAME_VIEW.ordinal)
