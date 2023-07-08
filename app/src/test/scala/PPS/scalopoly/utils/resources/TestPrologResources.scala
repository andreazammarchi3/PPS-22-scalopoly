package PPS.scalopoly.utils.resources

import org.junit.jupiter.api.Assertions.{assertEquals, assertNotNull, assertThrows}
import org.junit.jupiter.api.Test

class TestPrologResources:
  private val GAMEUTILS_PROLOG_POSITION = 0
  private val NUM_PROLOG_RESOURCES = 3

  @Test
  def testPath(): Unit =
    assertEquals("/prolog/GameUtilsProlog.pl", PrologResources.GAMEUTILS_PROLOG.path)
    assertEquals("/prolog/RentsCalculatorProlog.pl", PrologResources.RENTS_CALCULATOR_PROLOG.path)
    assertEquals("/prolog/ChanceCalculatorProlog.pl", PrologResources.CHANCE_CALCULATOR_PROLOG.path)

  @Test
  def testFromOrdinal(): Unit =
    assertEquals(
      PrologResources.GAMEUTILS_PROLOG,
      PrologResources.fromOrdinal(GAMEUTILS_PROLOG_POSITION)
    )
    assertThrows(
      classOf[NoSuchElementException],
      () => CssResources.fromOrdinal(PrologResources.values.length + 1)
    )

  @Test
  def testGetResource(): Unit =
    assertNotNull(getClass.getResource(PrologResources.GAMEUTILS_PROLOG.path))
    assertNotNull(getClass.getResource(PrologResources.RENTS_CALCULATOR_PROLOG.path))
    assertNotNull(getClass.getResource(PrologResources.CHANCE_CALCULATOR_PROLOG.path))

  @Test
  def testValues(): Unit =
    assertEquals(NUM_PROLOG_RESOURCES, PrologResources.values.length)

  @Test
  def testValueOf(): Unit =
    for (i <- PrologResources.values.indices)
      assertEquals(
        PrologResources.fromOrdinal(i),
        PrologResources.valueOf(PrologResources.fromOrdinal(i).toString)
      )
    assertThrows(
      classOf[IllegalArgumentException],
      () => PrologResources.valueOf("NOT_EXIST")
    )

  @Test
  def testOrdinal(): Unit =
    assertEquals(GAMEUTILS_PROLOG_POSITION, PrologResources.GAMEUTILS_PROLOG.ordinal)
