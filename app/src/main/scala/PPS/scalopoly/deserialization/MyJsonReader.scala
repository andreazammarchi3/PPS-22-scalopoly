package PPS.scalopoly.deserialization

import com.google.gson.stream.JsonReader

/** Trait that defines a generic JsonReader
  *
  * @tparam T
  *   the type of the object to be read
  */
trait MyJsonReader[T]:
  /** Reads a JsonReader
    *
    * @param in
    *   the JsonReader to be read
    * @return
    *   the object read
    */
  def read(in: JsonReader): T
