package exercises.part1basics

object Functions extends App {

  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }
  println(aFunction("Hello", 10))

  // Parameterless Functions
  def aParameterlessFunction(): Int = 42
  println("With Paranthesis " + aParameterlessFunction())
  println("Without Paranthesis " + aParameterlessFunction)

  // Recursive Function
  def aRecursiveFunction(s: String, n: Int): String = {
    if(n == 1) s
    else s + "," + aRecursiveFunction(s, n-1)
  }
  println("A recursive Function " + aRecursiveFunction("Himalaya",3))

  // Always use tail recursive functions instead of loops in Scala
  def aUnitFunction(s: String): Unit = println("The string is " + s)

  // Functions inside a function
  def outerFunction(n: Int): Int = {
    def innerFunction(a: Int, b: Int): Int = a + b
    innerFunction(n,n-1)
  }

  aUnitFunction(outerFunction(10).toString)

  /*
  1. A greeting function (name,age) => I am $name and I am $age years old.
  2. Factorial (N) => 1 * 2 * ... N
  3. Fibonacci (N) => Fibonacci(N-1) + Fibonacci(N-2)
  4. ifPrime(N) => Check if a number is Prime.
   */

  def greet(name: String, age: String): Unit = println(s"Hello I am $name and I am $age years old.")

  def factorial(n: Long): Long = {
    if(n < 0L) return -1L
    if(n == 0L || n == 1L) 1L
    else n * factorial(n-1L)
  }

  def fibonacci(n: Long): Long = {
    if(n <= 0) return -1L
    if(n == 1L || n == 2L) 1L
    else fibonacci(n-1L) + fibonacci(n-2L)
  }

  def isPrime(n: Long): Boolean = {
    def isPrimeUnitl(x: Long): Boolean = {
      if(x <= 1L) true
      else n % x != 0L && isPrimeUnitl(x-1L)
    }
    isPrimeUnitl(n-1)
  }

  println("---")
  println("Greet " + greet("Himalaya",25.toString))
  println("Factorial of 5 " + factorial(5))
  println("Fibonacci of 5 " + fibonacci(6))
  println("isPrime of 10 " + isPrime(10L))
  println("isPrime of 101 " + isPrime(101L))
  println("isPrime of 2003 " + isPrime(2003L))
  println("isPrime of 2003 * 101 " + isPrime(2003L * 101L))
}
