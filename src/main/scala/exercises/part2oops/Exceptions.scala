package exercises.part2oops

object Exceptions extends App {
  val x: String = null
  // println(x.length)
  // above will crash the program with a NPE
  // 1. Throwing exceptions
  // val aWeirdValue: String = throw new NullPointerException("NPE")
  // throwable classes extends the Throwable class.
  // Exceptions and Errors are the major Throwable subtypes.
  // 2. Catch Exceptions
  def getInt(value: Boolean): Int = {
    if(value) throw new RuntimeException("RTE")
    else 50
  }

  val potentialFail = try {
    // Code that might throw exception
    println(getInt(false))
    println(getInt(true))
    getInt(false)
  }
  catch {
    case e: RuntimeException => println("Crashed with RTE, give false in getInt method")
  }
  finally {
    // Code that will get executed no matter what happends
    // It is Optional block
    // does not influence the return type of the expression
    // Use finally only for side effects
    println("Finally")
  }

  println(potentialFail)
  // 3. Defining our own Exceptions

  class myException extends Exception
  val except = new myException

//  throw except
  /*
  Exercise :
  - Crash your program with OutOfMemoryError
  - Crash with SOError
  - PocketCalculator
    * add(x,y)
    * subtract(x,y)
    * divide(x,y)
    * multiply(x,y)

      Throw
      - OverflowException if add(x,y) exceeds Int.MAX_VALUE
      - UnderflowException if subtract(x,y) exceeds Int.MIN_VALUE
      - MathCalculationException if division by 0
   */
   // 1. Crash your program with OutOfMemoryError
//   throw new OutOfMemoryError()
   // 2. Crash with SOError
//    throw new StackOverflowError()
   // 3. PocketCalculator

  case class OverflowException() extends Exception {
    override def toString: String = s"Result greater than ${Int.MaxValue}"
  }
  case class UnderflowException() extends Exception {
    override def toString: String = s"Result smaller than ${Int.MinValue}"
  }
  case class MathCalculationException() extends Exception {
    override def toString: String = s"Value divided by 0"
  }
  case class pocketCalculator(x: Int, y: Int) {
    def add(x: Int, y:Int): Int = {
      if (x > 0 && y > 0 && x + y <= 0) throw new OverflowException
      else if (x < 0 && y < 0 && x + y >= 0) throw new UnderflowException
      else x + y
    }

    def subtract(x: Int, y: Int): Int = {
      if (x > 0 && y < 0 && x - y <= 0) throw new OverflowException
      else if (x < 0 && y > 0 && x - y >= 0) throw new UnderflowException
      else x - y
    }

    def divide(x: Int, y: Int): Int = {
      if (y == 0) throw new MathCalculationException
      else x / y
    }

    def multiply(x: Int, y: Int): Int = {
      if ((x > 0 && y > 0 || x < 0 && y < 0) && x * y < 0) throw new OverflowException
      else if ((x < 0 && y < 0 || x > 0 && y < 0) && x * y > 0) throw new UnderflowException
      else x * y
    }
  }

  try {
    val obj = new pocketCalculator(10, 20)
    println(obj.add(-1, Int.MinValue))
    println(obj.divide(10, 0))
  }
  catch {
    case e: Exception => println(e)
    case e1: OverflowException => println(e1)
    case e2: UnderflowException => println(e2)
    case e3: MathCalculationException => println(e3)
  }
  finally {
    // optional
  }
}
/*
Int + Unit = AnyVal
 */
/*
Takeaways:
- Exceptions crash your program
- throwing exception
- Catching exception
- Defining custom exception
 */
