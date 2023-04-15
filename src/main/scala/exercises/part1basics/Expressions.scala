package exercises.part1basics

object Expressions extends App {
  // Instructions (commands that let us "DO" something)
  // Expressions (Something that is evaluated, these are values)

  val aValue: Int = 2 + 3
  println(aValue) // A side effect

  // Logical Operators
  // + - * / & | ^ >> << >>>(right shift with zero extensions)
  var aLogicalExpression = aValue + 5
  println(aLogicalExpression)

  aLogicalExpression += 10
  println(aLogicalExpression)

  // Everything is scala is an expression
  val condition = 2 < 3
  var anIfExpression = if(condition) 15 else 25
  println(anIfExpression)

  // Code Block
  val aCodeBlock = {
    val a = 10
    val b = 12
    val c = a * b
    if(condition) c else b
  }
  println(aCodeBlock)
  // Value of a code block is the value of last return expression

  // Side Effects
  // A side effect is an expression which return Unit, Unit can be compared to Void in C/Java
  // examples of side effects are assigning expression, while loops, println statement
  // A unit = ()
  var i = 0
  val unitValue = while(i < 10) {
    i += 1
    println("i : " + i)
  }
  println(unitValue)
}
