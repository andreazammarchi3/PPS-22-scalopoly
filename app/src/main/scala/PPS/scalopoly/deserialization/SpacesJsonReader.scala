package PPS.scalopoly.deserialization

import PPS.scalopoly.model.SpaceGroup
import PPS.scalopoly.model.space.{Space, SpaceImpl}
import PPS.scalopoly.model.space.purchasable.CompanySpace
import PPS.scalopoly.utils.JsonUtils
import com.google.gson.stream.JsonReader

import java.io.Reader
import scala.collection.mutable.ListBuffer

object SpacesJsonReader:
  def read(in: Reader): List[SpaceImpl] = readSpaces(new JsonReader(in))

  private def readSpaces(reader: JsonReader): List[SpaceImpl] =
    val spaces = new ListBuffer[SpaceImpl]
    reader.beginArray()
    while (reader.hasNext)
      spaces += readSpace(reader)
    reader.endArray()
    spaces.toList

  private def readSpace(reader: JsonReader): SpaceImpl =
    var name = ""

    reader.beginObject()
    while (reader.hasNext)
      val currentName = reader.nextName()
      currentName match
        case "name" => name = reader.nextString()
        case _      => reader.skipValue()
    reader.endObject()
    SpaceImpl(name)
