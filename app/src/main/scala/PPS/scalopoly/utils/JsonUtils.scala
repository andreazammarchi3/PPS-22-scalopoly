package PPS.scalopoly.utils

import PPS.scalopoly.Launcher.getClass
import PPS.scalopoly.deserialization.MyJsonReader
import PPS.scalopoly.utils.resources.JsonResources
import com.google.gson.stream.JsonReader

import java.io.{Reader, StringReader}
import scala.collection.mutable.ListBuffer
import scala.io.Source

/** An object that provides utilities for reading JSON files.
  */
object JsonUtils:

  /** Reads a list of spaces from a JSON file.
    * @param jsonResources
    *   the JSON file
    * @param myJsonReader
    *   the reader of the JSON file
    * @tparam T
    *   the type of the spaces
    * @return
    *   the list of spaces
    */
  def readTypeSpaces[T](jsonResources: JsonResources, myJsonReader: MyJsonReader[T]): List[T] =
    val js = Source.fromInputStream(getClass.getResourceAsStream(jsonResources.path))
    val spaces: List[T] = readSpaces[T](new StringReader(js.mkString), myJsonReader)
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
