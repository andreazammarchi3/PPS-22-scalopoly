package PPS.scalopoly.utils

import PPS.scalopoly.Launcher.getClass
import PPS.scalopoly.deserialization.{
  BuildableSpaceJsonReader,
  CompanySpaceJsonReader,
  MyJsonReader,
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

import java.io.{Reader, StringReader}
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

  def readTypeSpaces[T](
      jsonResources: JsonResources,
      myJsonReader: MyJsonReader[T]
  ): List[T] =
    val js = Source.fromFile(getClass.getResource(jsonResources.path).toURI)
    val spaces: List[T] =
      readSpaces[T](new StringReader(js.mkString), myJsonReader)
    js.close()
    spaces

  private def readSpaces[T](in: Reader, myJsonReader: MyJsonReader[T]): List[T] =
    val reader = new JsonReader(in)
    val spaces = new ListBuffer[T]
    reader.beginArray()
    while (reader.hasNext)
      spaces += myJsonReader.read(reader)
    reader.endArray()
    spaces.toList
