package exercises.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  // Just a Recursive Function
  def factorial(n: Long): Long = {
    if (n < 0L) return -1L
    if (n == 0L || n == 1L) 1L
    else n * factorial(n - 1L)
  }

  // A tail Recursive Function
  @tailrec
  def anotherFactorial(n: Int, accumulator: BigInt): BigInt = {
    if (n < 0L) return -1L
    if (n == 0L || n == 1L) accumulator
    else anotherFactorial(n-1, accumulator * n)
  }
  println(anotherFactorial(6, 1))

  /*
  Do below functions as Tail Recursive
  1. Print a string n times
  2. isPrime
  3. Fibonacci
   */

  @tailrec
  def aTailRecFunction(s: String, n: Int, accumulator: String): String = {
    if(n <= 1) s + "," + accumulator
    else aTailRecFunction(s, n-1, s + "," + accumulator)
  }
  // Calling
  println(aTailRecFunction("Himalaya",3,""))

  @tailrec
  def isPrime(n: Int, div: Int, isPrimeYet: Boolean): Boolean = {
    if(div <= 1) isPrimeYet
    else isPrime(n, div - 1, isPrimeYet && n % div != 0)
  }
  // Calling
  println(isPrime(999,998,true))
  println(isPrime(2003,2002,true))
  println(isPrime(629,628,true))

  // Another Approach
  def isPrimeUdemy(n: Int): Boolean = {
    @tailrec
    def isPrimeTailrec(t: Int, accumulator: Boolean): Boolean = {
      if(!accumulator) false
      else if(t <= 1) true
      else isPrimeTailrec(t-1, n%t != 0 && accumulator)
    }
    isPrimeTailrec(n/2,true)
  }

  println(isPrimeUdemy(2003))
  println(isPrimeUdemy(629))

  // Fibonacci with Udemy Approach
  def getFibonacci(n: Int): Int = {
    @tailrec
    def fibonacci(i: Int, last: Int, nextToLast: Int): Int = {
      if(i >= n) last
      else fibonacci(i+1, last + nextToLast, last)
    }
    if(n <= 2) 1
    else fibonacci(2,1,1)// started from 2 because 2 is the 3rd fibonacci number
  }
  // 1 1 2 3 5 8 13 21 34 55
  println(getFibonacci(10))
  println(getFibonacci(2))

}
