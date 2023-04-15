package exercises.part1basics

object CallByNameAndValue extends App {
  def callByValue(n: Long): Unit = {
    println("By Value : " + n)
    println("By Value : " + n)
  }

  def callByName(n: => Long): Unit = {
    println("By Name : " + n) // This is literally replaced by println("By Name : " + System.nanoTime())
    println("By Name : " + n) // This is literally replaced by println("By Name : " + System.nanoTime())
  }

  callByValue(System.nanoTime())
  callByName(System.nanoTime())

  // Call By Name is evaluated Lazy i.e expression is only evaluated when used
  // it delays the execution of the expression
  // example

  def infinite(): Int = 1 + infinite()
  def printNumber(x: Int, y: => Int): Unit = println("The number is : " + x)

//  printNumber(infinite(),10)
  printNumber(10,infinite())

}
