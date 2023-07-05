package PPS.scalopoly.deserialization

import PPS.scalopoly.model.SpaceGroup
import PPS.scalopoly.model.space.{Space, SpaceImpl}
import PPS.scalopoly.utils.JsonUtils
import com.google.gson.stream.JsonReader

import java.io.Reader
import scala.collection.mutable.ListBuffer

object SpacesJsonReader extends MyJsonReader[Space]:
  override def read(reader: JsonReader): SpaceImpl =
    var name = ""

    reader.beginObject()
    while (reader.hasNext)
      val currentName = reader.nextName()
      currentName match
        case "name" => name = reader.nextString()
        case _      => reader.skipValue()
    reader.endObject()
    SpaceImpl(name)
