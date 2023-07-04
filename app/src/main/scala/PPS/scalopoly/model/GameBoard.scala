package PPS.scalopoly.model

import PPS.scalopoly.deserialization.{
  BuildableSpaceJsonReader,
  CompanySpaceJsonReader,
  SpacesJsonReader,
  StationSpaceJsonReader
}
import PPS.scalopoly.model.space.purchasable.{
  BuildableSpace,
  CompanySpace,
  PurchasableSpace,
  StationSpace
}
import PPS.scalopoly.model.space.{Space, SpaceImpl}
import PPS.scalopoly.utils.JsonUtils
import PPS.scalopoly.utils.resources.JsonResources

class GameBoard(spaces: List[Space]):
  private val _companySpaces: List[CompanySpace] = JsonUtils.readCompanySpaces
  private val _stationSpaces: List[StationSpace] = JsonUtils.readStationSpaces
  private val _buildableSpaces: List[BuildableSpace] =
    JsonUtils.readBuildableSpaces

  def companySpaces: List[CompanySpace] = _companySpaces

  def stationSpaces: List[StationSpace] = _stationSpaces

  def buildableSpaces: List[BuildableSpace] = _buildableSpaces

  def purchasableSpaces: List[PurchasableSpace] =
    _companySpaces ++ _stationSpaces ++ _buildableSpaces

  /** Returns the game board map.
    *
    * @return
    *   the game board map.
    */
  def gameBoardList: List[Space] = spaces

  /** Returns the size of the game board.
    *
    * @return
    *   the size of the game board.
    */
  def size: Int = spaces.length

/** The game board is a map of the game spaces represented by a list of
  * [[SpaceName]].
  */
object GameBoard:

  def apply(): GameBoard = new GameBoard(JsonUtils.readSpaces)
