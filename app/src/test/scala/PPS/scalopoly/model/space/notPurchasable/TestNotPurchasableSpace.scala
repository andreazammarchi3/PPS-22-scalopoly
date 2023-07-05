package PPS.scalopoly.model.space.notPurchasable

import PPS.scalopoly.model.{Player, Token}
import org.junit.jupiter.api.Assertions.{assertEquals, assertTrue}
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.{CsvSource, ValueSource}
import org.junit.jupiter.params.provider.MethodSource

class TestNotPurchasableSpace:

  @Test
  def testCreateSpace(): Unit =
    val name = "testName"
    val spaceType = NotPurchasableSpaceType.CHANCE
    val spaceValue = 100
    val space = NotPurchasableSpaceBuilder(name, spaceType, spaceValue).build()
    assertEquals(name, space.name)
    assertEquals(spaceType, space.spaceType)
    assertEquals(spaceValue, space.spaceValue)

  @ParameterizedTest
  @MethodSource(Array("inputDataProvider"))
  def testCheckSpacePayAction(input: InputData): Unit =
    val spaceType = input.spaceType
    val expectedParameterResult = input.expectedParameterResult
    val (resultPlayer: Player, moneyOperationResult: Int) = runAction(spaceType)
    assertEquals(expectedParameterResult, resultPlayer.money)

  @Test
  def testCheckSpaceChanceAction(): Unit =
    val (resultPlayer: Player, moneyOperationResult: Int) = runAction(NotPurchasableSpaceType.CHANCE)
    assert(resultPlayer.money == 1900 || resultPlayer.money == 2100)

  private def runAction(spaceType: NotPurchasableSpaceType):  (Player, Int) =
    val player: Player = Player("player", Token.CANE)
    val spaceValue = 100
    val chanceSpace = NotPurchasableSpaceBuilder("", spaceType, spaceValue).build()
    chanceSpace.action(player)


object TestNotPurchasableSpace:
  def inputDataProvider(): java.util.stream.Stream[Array[Matchable]] =
    java.util.stream.Stream.of(
      Array(InputData(NotPurchasableSpaceType.LUXURY_TAX, 1900)),
      Array(InputData(NotPurchasableSpaceType.INCOME_TAX, 1900)),
      Array(InputData(NotPurchasableSpaceType.BLANK, 2000)),
    )

case class InputData(spaceType: NotPurchasableSpaceType, expectedParameterResult: Int)