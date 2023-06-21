package PPS.scalopoly

object Utils:
  def testCatchException[T, A, B](f: A => B, x: A): Boolean =
    try
      f(x)
      false
    catch case _: T => true
