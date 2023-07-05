package PPS.scalopoly.utils

import PPS.scalopoly.Launcher.getClass
import PPS.scalopoly.deserialization.{
  BuildableSpaceJsonReader,
  CompanySpaceJsonReader,
  NotPurchasableSpaceJsonReader,
  SpacesJsonReader,
  StationSpaceJsonReader
}
import PPS.scalopoly.model.space.notPurchasable.NotPurchasableSpace
import PPS.scalopoly.model.space.purchasable.{
  BuildableSpace,
  CompanySpace,
  StationSpace
}
import PPS.scalopoly.model.space.{Space, SpaceImpl}
import PPS.scalopoly.utils.resources.JsonResources
import com.google.gson.stream.JsonReader

import java.io.StringReader
import scala.collection.mutable.ListBuffer
import scala.io.Source

object JsonUtils:
  def readRents(reader: JsonReader): List[Int] =
    val rents = new ListBuffer[Int]
    reader.beginArray()
    while (reader.hasNext)
      rents += reader.nextInt()
    reader.endArray()
    rents.toList

  def readBuildableSpaces: List[BuildableSpace] =
    val file = getClass.getResource(JsonResources.BUILDABLE_SPACES.path).toURI
    val jsonString = Source.fromFile(file).mkString
    val spaces: List[BuildableSpace] =
      BuildableSpaceJsonReader.read(new StringReader(jsonString))
    spaces

  def readCompanySpaces: List[CompanySpace] =
    val file = getClass.getResource(JsonResources.COMPANY_SPACES.path).toURI
    val jsonString = Source.fromFile(file).mkString
    val spaces: List[CompanySpace] =
      CompanySpaceJsonReader.read(new StringReader(jsonString))
    spaces

  def readStationSpaces: List[StationSpace] =
    val file = getClass.getResource(JsonResources.STATION_SPACES.path).toURI
    val jsonString = Source.fromFile(file).mkString
    val spaces: List[StationSpace] =
      StationSpaceJsonReader.read(new StringReader(jsonString))
    spaces

  def readNotPurchasableSpaces: List[NotPurchasableSpace] =
    val file =
      getClass.getResource(JsonResources.NOT_PURCHASABLE_SPACES.path).toURI
    val jsonString = Source.fromFile(file).mkString
    val spaces: List[NotPurchasableSpace] =
      NotPurchasableSpaceJsonReader.read(new StringReader(jsonString))
    spaces

  def readSpaces: List[Space] =
    val file = getClass.getResource(JsonResources.SPACES.path).toURI
    val jsonString = Source.fromFile(file).mkString
    val spaces: List[SpaceImpl] =
      SpacesJsonReader.read(new StringReader(jsonString))
    spaces
