package PPS.scalopoly.model

import org.junit.jupiter.api.Assertions.{assertEquals, assertThrows, assertTrue}
import org.junit.jupiter.api.Test

class TestPurchasableSpace:

  private val VICOLO_CORTO_POSITION = 0
  private val NUM_SPACES = 28
  val RENT_SELLING_PRICE_PERC = 10
  val VICOLO_CORTO_SELLING_PRICE = 150
  @Test
  def testFromOrdinal(): Unit =
    assertEquals(
      PurchasableSpace.VICOLO_CORTO,
      PurchasableSpace.fromOrdinal(VICOLO_CORTO_POSITION)
    )
    assertThrows(
      classOf[NoSuchElementException],
      () => PurchasableSpace.fromOrdinal(PurchasableSpace.values.length + 1)
    )

  @Test
  def testValues(): Unit =
    assertEquals(NUM_SPACES, PurchasableSpace.values.length)

  @Test
  def testValueOf(): Unit =
    for (i <- PurchasableSpace.values.indices)
      assertEquals(
        PurchasableSpace.fromOrdinal(i),
        PurchasableSpace.valueOf(PurchasableSpace.fromOrdinal(i).toString)
      )
    assertThrows(
      classOf[IllegalArgumentException],
      () => PurchasableSpace.valueOf("NOT_EXIST")
    )

  @Test
  def testCalculateRent(): Unit =
    assertEquals(
      PurchasableSpace.VICOLO_CORTO.sellingPrice / RENT_SELLING_PRICE_PERC,
      PurchasableSpace.VICOLO_CORTO.calculateRent()
    )

  @Test
  def testSpaceName(): Unit =
    assertEquals(
      SpaceName.VICOLO_CORTO,
      PurchasableSpace.VICOLO_CORTO.spaceName
    )

  @Test
  def testSellingPrice(): Unit =
    assertEquals(
      VICOLO_CORTO_SELLING_PRICE,
      PurchasableSpace.VICOLO_CORTO.sellingPrice
    )

  @Test
  def testSpaceGroup(): Unit =
    assertEquals(SpaceGroup.ROSA, PurchasableSpace.VICOLO_CORTO.spaceGroup)
