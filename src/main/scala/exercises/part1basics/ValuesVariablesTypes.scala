package exercises.part1basics

object ValuesVariablesTypes extends App {
  // Values
  // Values are immutable
  val anInt: Int = 5
  val aString: String = "Himalaya"
  val aBoolean: Boolean = true
  val aChar: Char = 'a'
  val aFloat: Float = 1.5f
  val aDouble: Double = 2.0
  val aShort: Short = 12346
  val aLong: Long = 123456789123L

  // Variables
  // Variables are mutable
  var aVariable: String = "Himalaya"
  aVariable = "Singh Negi" // a side effect
  println(aVariable)

  // Types are Int, String, Boolean, Char, Float, Double, Short, Long
}
