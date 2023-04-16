package exercises.part2oops

abstract class MyGenericList[+A] {
  /*
  head => return an int with first element of the list
  tail => pointer to remainder of the list
  isEmpty => Boolean if the list is empty
  add(int) => new list with this element added
  toString => a String representation of the list (override)
   */

  def head: A
  def tail: MyGenericList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyGenericList[B]
  def printElements: String
  override def toString: String = "[" + printElements + "]"
}

// Nothing is a proper substitute of Any type
case object genericEmpty extends MyGenericList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyGenericList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def printElements: String = ""
  def add[B >: Nothing](element: B): MyGenericList[B] = new genericCons(element, genericEmpty)
}

case class genericCons[+A](h: A, t: MyGenericList[A]) extends MyGenericList[A] {
  //  def head: Int = ???
  //  def tail: MyGenericList = ???
  //  def isEmpty: Boolean = ???
  //  def add(element: Int): MyGenericList = ???

  def head: A = h
  def tail: MyGenericList[A] = t
  def isEmpty: Boolean = false
  def printElements: String = {
    if(tail.isEmpty) s"$head"
    else s"$head " + tail.printElements
  }
  def add[B >: A](element: B): MyGenericList[B] = new genericCons(element, this)
}

object GenericExercise extends App {
  val ListOfIntegers: MyGenericList[Integer] = new genericCons(1, new genericCons(2, new genericCons(3, genericEmpty)))
  val cloneOfListOfIntegers: MyGenericList[Integer] = new genericCons(1, new genericCons(2, new genericCons(3, genericEmpty)))
  val ListOfString: MyGenericList[String] = new genericCons("Hello", new genericCons("Rock", new genericCons("The JVM", genericEmpty)))

  println(ListOfIntegers)
  println(ListOfString)

  println(ListOfIntegers.toString)
  println(ListOfString.toString)
  println(ListOfIntegers == cloneOfListOfIntegers)
}
