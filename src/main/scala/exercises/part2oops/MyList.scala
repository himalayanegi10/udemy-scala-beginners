package exercises.part2oops

abstract class MyList {
  /*
  head => return an int with first element of the list
  tail => pointer to remainder of the list
  isEmpty => Boolean if the list is empty
  add(int) => new list with this element added
  toString => a String representation of the list (override)
   */

  def head: Int
  def tail: MyList
  def isEmpty: Boolean
  def add(element: Int): MyList
  def printElements: String
  override def toString: String = "[" + printElements + "]"
}

object Empty extends MyList {
  def head: Int = throw new NoSuchElementException
  def tail: MyList = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def printElements: String = ""
  def add(element: Int): MyList = new cons(element, Empty)
}

class cons(h: Int, t: MyList) extends MyList {
//  def head: Int = ???
//  def tail: MyList = ???
//  def isEmpty: Boolean = ???
//  def add(element: Int): MyList = ???

  def head: Int = h
  def tail: MyList = t
  def isEmpty: Boolean = false
  def printElements: String = {
    if(tail.isEmpty) s"$head"
    else s"$head " + tail.printElements
  }
  def add(element: Int): MyList = new cons(element, this)
}

object ListTest extends App {
  val List = new cons(1, new cons(2, new cons(3, Empty)))
  println(List)
  println(List.tail)
  println(List.tail.tail)
  println(List.tail.head)
  println(List.add(10).head)

}