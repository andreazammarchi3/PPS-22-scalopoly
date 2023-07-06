package PPS.scalopoly.model.space.purchasable

import PPS.scalopoly.engine.GameEngine
import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue}
import org.junit.jupiter.api.Test

class TestBuildableSpace:

  private val vicoloCorto = GameEngine.gameBoard.buildableSpaces(0)

  @Test
  def testNumHouse(): Unit =
    assertEquals(0, vicoloCorto.numHouse)

  @Test
  def testBuildingCost(): Unit =
    val VICOLO_CORTO_BUILDING_COST = 50
    assertEquals(VICOLO_CORTO_BUILDING_COST, vicoloCorto.buildingCost)

  @Test
  def testBuildHouse(): Unit =
    assertEquals(1, vicoloCorto.buildHouse.numHouse)

  @Test
  def calculateRent(): Unit =
    val VICOLO_CORTO_RENTS = List(2, 52, 102, 152, 202, 252)
    assertEquals(VICOLO_CORTO_RENTS(0), vicoloCorto.calculateRent)
    assertEquals(VICOLO_CORTO_RENTS(1), vicoloCorto.buildHouse.calculateRent)
