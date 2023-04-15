package exercises.part1basics

import scala.annotation.tailrec

object DefaultArgs extends App {
  @tailrec
  def factorial(n: Int, acc: Int = 1): Int = {
    if(n <= 1) acc
    else factorial(n-1, n * acc)
  }
  val fact10 = factorial(10)
  println("Factorial = " + fact10)

  def plotAPoint(width: Int = 1920, height: Int = 1080, rgb: String = "Blue"): Unit = println("Point Printed at (width,height) = (" + width + "," + height + ") with RGB = " + rgb)

  /*
  To give less arguments for calling a function with default arguments:
  1. Provide all leading arguments
  2. Give names to arguments
   */

  plotAPoint()
  plotAPoint(height = 10, width = 90, rgb = "Red")
}
