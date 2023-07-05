package PPS.scalopoly.utils.resources

import org.junit.jupiter.api.Assertions.{assertEquals, assertNotNull, assertThrows}
import org.junit.jupiter.api.Test

class TestJsonResources:

  private val BUILDABLE_SPACES_POSITION = 0
  private val STATION_SPACES_POSITION = 2
  private val NUM_JSON_RESOURCES = 5

  @Test
  def testPath(): Unit =
    assertEquals("/json/spaces.json", JsonResources.SPACES.path)
    assertEquals(
      "/json/buildableSpaces.json",
      JsonResources.BUILDABLE_SPACES.path
    )

  @Test
  def testFromOrdinal(): Unit =
    assertEquals(
      JsonResources.BUILDABLE_SPACES,
      JsonResources.fromOrdinal(BUILDABLE_SPACES_POSITION)
    )
    assertEquals(
      JsonResources.STATION_SPACES,
      JsonResources.fromOrdinal(STATION_SPACES_POSITION)
    )
    assertThrows(
      classOf[NoSuchElementException],
      () => JsonResources.fromOrdinal(JsonResources.values.length + 1)
    )

  @Test
  def testGetResource(): Unit =
    assertNotNull(getClass.getResource(JsonResources.SPACES.path))
    assertNotNull(getClass.getResource(JsonResources.BUILDABLE_SPACES.path))

  @Test
  def testValues(): Unit =
    assertEquals(NUM_JSON_RESOURCES, JsonResources.values.length)

  @Test
  def testValueOf(): Unit =
    for (i <- JsonResources.values.indices)
      assertEquals(
        JsonResources.fromOrdinal(i),
        JsonResources.valueOf(JsonResources.fromOrdinal(i).toString)
      )
    assertThrows(
      classOf[IllegalArgumentException],
      () => JsonResources.valueOf("NOT_EXIST")
    )

  @Test
  def testOrdinal(): Unit =
    assertEquals(
      BUILDABLE_SPACES_POSITION,
      JsonResources.BUILDABLE_SPACES.ordinal
    )
    assertEquals(STATION_SPACES_POSITION, JsonResources.STATION_SPACES.ordinal)
