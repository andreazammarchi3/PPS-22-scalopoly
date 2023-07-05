package PPS.scalopoly.deserialization

import com.google.gson.stream.JsonReader

trait MyJsonReader[T]:
  def read(in: JsonReader): T
