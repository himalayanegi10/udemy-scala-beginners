package exercises.part1basics

object StringOps extends App {
  val aString: String = "Hello, I am learning Scala"

  println(aString.charAt(3))
  println(aString.substring(5, 10))
  println(aString.split(" ").toList)
  println(aString.startsWith("Hello"))
  println(aString.replace(" ", "-"))
  println(aString.toLowerCase())
  println(aString.length)

  val aNumberString: String = "100"
  val aNumber: Int = aNumberString.toInt
  println('O' +: aNumberString :+ '1')
  println(aString.reverse)
  println(aString.take(3))

  // Scala Specific: String Interpolators

  // S Interpolated String
  val name: String = "Himalaya"
  val age: Int = 24
  val greeting: String = s"Hello I am $name, and I am $age years old"
  val anotherGreeting: String = s"Hello I am $name, and I am ${age+5} years old"
  println(greeting)
  println(anotherGreeting)

  // F Interpolated String, Similar to S interpolated Strings but behave like a printf statement
  val burgers:Float = 4.5f
  val anotherString: String = f"$name%s can eat $burgers%2.3f burgers per 10 minutes"
  println(anotherString)

  // Raw Interpolated String
  println(raw"This is a string \n NEWLINE Character")
  val escaped = "This is a string \n NEWLINE Character"
  println(raw"$escaped")

}