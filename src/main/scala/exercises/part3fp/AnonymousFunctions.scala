package exercises.part3fp

object AnonymousFunctions extends App {
  // Anonymous function (LAMBDA)
  val doubler: Int => Int = (x: Int) => x * 2
  println(doubler(1))
  // RHS above is a syntactic sugar for :
  /*
  new Function[Int,Int] {
    override def apply(v1: Int): Int = v1 * 2
  }
   */
  // This is also a syntactic sugar :
  // val doubler: Int => Int = x => x * 2

  // Multiple Parameters in a lambda
  val adder: (Int, Int) => Int = (x: Int, y: Int) => x + y
  println(adder(2,3))
  // This is also a syntactic sugar :
  // val adder: (Int, Int) => Int = (x,y) => x + y

  // No Param
  val justDoSomething: () => Int = () => 10
  println(justDoSomething)
  println(justDoSomething())

  // Curly braces with lambda
  val stringToInteger = {
    (num: String) => num.toInt
  }
  println(stringToInteger("100")+4)

  // MORE Syntactic Sugar
  val niceIncrementer: Int => Int = _ + 1 // equivalent to x => x + 1
  val niceAdder: (Int, Int) => Int = _ + _ // equivalent to (a,b) => a + b
  // NOTE: Every underscore is used for different parameter

  /*
  Exercises:
  - Replace all functionX calls with Lambdas
  - Rewrite "secretAdder" as an anonymous function
   */
  // 1. Done in MyListAnonymousFunctions.scala
  // 2.
  val superAdd = (x: Int) => (y: Int) => x + y
  println(superAdd(4)(5))

  /*
  Takeaways:
  Instead of passing Anonymous FunctionX instance every time which is both:
    -cumbersome
    -still object oriented!!

  we use lambdas : (x,y) => x + y

  (name: String, age: Int) => s"name is ${name} and age is ${age}"
   */
  val nameAge: (String, Int) => String = (name, age) => s"name is ${name} and age is ${age}"
  println(nameAge("Himalaya", 25))
}
