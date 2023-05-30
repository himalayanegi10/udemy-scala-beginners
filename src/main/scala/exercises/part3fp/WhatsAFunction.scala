package exercises.part3fp

object WhatsAFunction extends App {
  // DREAM : Use function as first class elements
  // Problem : OOP

  val doubler = new MyFunction[Int, Int]{
    override def apply(element: Int): Int = element * 2
  }
  println(doubler(4))

  // Function Types = Function1[A,B]
  val stringToIntConverter = new Function[String, Int] {
    override def apply(v1: String): Int = v1.toInt
  }
  println(stringToIntConverter("7") + 3)

  val adder:(Int,Int) => Int = new Function2[Int, Int, Int] {
    override def apply(v1: Int, v2: Int): Int = v1 + v2
  }
  println(adder(4,9))
  // Function type FunctionN[A,B,R] === (A,B) => R where N is in [1,22]
  // FunctionN means (v1, ... vN) => R

  // Key takeaway : ALL SCALA FUNCTIONS ARE OBJECTS
  /*
  Exercise:
    1. A function that takes two strings and concatenates them
    2. Transform MyPredicate and MyTransformer to function types
    3. Define a function that takes an Int and returns a function that takes an Int and returns an Int
      - what's the type of this function?
      - How to do it ?
   */
  val stringCocatenator: (String, String) => String = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1.concat(v2)
  }
  println(stringCocatenator(stringCocatenator("Himalaya", " "), "Negi"))

  val superAdder: Function[Int, Function[Int, Int]] = new Function[Int, Function[Int, Int]] {
    override def apply(v1: Int): Function[Int, Int] = new Function[Int, Int] {
      override def apply(v2: Int): Int = v1 + v2
    }
  }
  val add3 = superAdder(5)
  println(add3(3))
  println(superAdder(5)(6) + " <- Curried Function")
  /*
  Takeaways:
    We want to work with functions:
      -Pass function as a parameter
      -use function as a value
    Problem: Scala works on top of JVM
      -Designed for java
      -First class element: object
    Solution: All scala functions are objects
      -function trait, upto 22 params
      -syntactic sugar function types

      trait Function1[-A,+B] {
        def apply(element: A): B
      }
      Function2[Int, String, Int], its syntactic sugar === (Int, String) => Int
   */
}

trait MyFunction[A,B]{
  def apply(element: A): B = ???
}