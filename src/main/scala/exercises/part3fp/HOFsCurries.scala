package exercises.part3fp

object HOFsCurries extends App {
  val superFunction: (Int, (String, Int => Boolean) => Int ) => (Int => Int) = null
  // This is a higher order function (HOF)

  // map, flatMap, filter in MyListFP are an example of HOF

  /*
  example:
  function that applies a function n times over a value x
  nTimes(f, n, x)
  nTimes(f, 3, x) = f(f(f(x))) = nTimes(f, 2, f(x)) = f(f(f(x)))
  nTimes(f, n, x) = f(f(...f(x))) = nTimes(f, n-1, f(x))
   */

  def nTimes(f: Int => Int, n: Int, x: Int): Int = {
    if(n <= 0) x
    else nTimes(f, n-1, f(x))
  }
  val plusOne: Int => Int = x => x + 1
  println(nTimes(plusOne, 10, 1))

  /*
  ntb(f,n) = x => f(f(...f(x)))
  increment10 = ntb(plusOne, 10) = x => plusOne(plusOne(...plusOne(x)))
  val y = increment10(1)
   */
  def nTimesBetter(f: Int => Int, n: Int): (Int => Int) = {
    if(n <= 0) (x: Int) => x
    else (x:Int) => nTimesBetter(f, n-1)(f(x))
  }
  val plus10 = nTimesBetter(plusOne, 10)
  println(plus10)
  println(plus10(1))

  // curried function

  val superAdder: Int => (Int => Int) = (x: Int) => ((y: Int) => x + y)
  val add3 = superAdder(3) // 3 + y
  val getSum = add3(17)
  println(getSum)

  // Functions with multiple parameter lists
  def curriedFormatter(c: String)(x: Double): String = {
    c.format(x)
  }

  val standardFormat: (Double => String) = curriedFormatter("%4.2f")
  val preciseFormat: (Double => String) = curriedFormatter("%10.8f")
  println(standardFormat(Math.PI))
  println(preciseFormat(Math.PI))

  /*
  Takeaways:
  -Functional programming is about working with functions
    -Pass functions as a parameter
    -Return functions as a list

   Higher Order Function (HOFs)
   def nTimesBetter(f: Int => Int, n: Int): Int => Int = ???

   Currying = Functions with multiple parameter lists
   def curriedFormatter(a: Int, b: Int, c: String)(c: String): String
                        parameter list 1            parameter list 2
   */
}
