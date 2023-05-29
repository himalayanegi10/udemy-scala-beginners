package exercises.part3fp

import javax.naming.NameNotFoundException

/*
  1. Generic Trait MyPredicate[-T] with a method test(T) => Boolean
  2. Generic Trait MyTransformer[-A,B] with a method transform(A) => B
  3. MyListContinued:
    -map(transformer) => MyListContinued
    -filter(predicate) => MyListContinued
    -flatMap(transformer) => MyListContinued

    class EvenPredicate extends MyPredicate[Int]
    class StringToIntTransformer extends MyTransformer[String,Int]

    Pseudo code
    [1,2,3].map(n*2) = [2,4,6]
    [1,2,3,4].filter(n%2 == 0) = [2,4]
    [1,2,3].flatMap(n => [n,n+1]) = [1,2,2,3,3,4]
 */
object MyListContinued extends App {

  abstract class myList[+A] {
    def head: A
    def tail: myList[A]
    def isEmpty: Boolean
    def add[B >: A](element: B): myList[B]
    def printString: String
    override def toString: String = s"[${this.printString}]"

    // Higher Order Function
    def map[B](transformer: A => B): myList[B]
    def flatMap[B](transformer: A => myList[B]): myList[B]
    def filter(predicate: A => Boolean): myList[A]
    // concatenation
    def ++[B >: A](list: myList[B]): myList[B]
  }

  object myEmptyList extends myList[Nothing] {
    override def head: Nothing = throw new NoSuchElementException
    override def tail: myList[Nothing] = throw new NoSuchElementException
    override def isEmpty: Boolean = true
    override def printString: String = ""
    override def add[B >: Nothing](element: B): myList[B] = new myCons(element, myEmptyList)

    override def map[B](transformer: Nothing => B): myList[B] = myEmptyList
    def flatMap[B](transformer: Nothing => myList[B]): myList[B] = myEmptyList
    override def filter(predicate: Nothing => Boolean): myList[Nothing] = myEmptyList
    override def ++[B >: Nothing](list: myList[B]): myList[B] = list
  }

  class myCons[+A](h: A, t: myList[A]) extends myList[A] {
    override def head: A = h
    override def tail: myList[A] = t
    override def isEmpty: Boolean = false
    override def printString: String = {
      if(tail.isEmpty) s"$head"
      else s"$head " + tail.printString
    }
    override def add[B >: A](element: B): myList[B] = new myCons(element, this)

    /*
    [1,2,3].filter(n%2 == 1)
      if 1%2 == 1 => new myCons(1, [2,3].filter(n%2 == 1))
      if 2%2 == 1 => new myCons(1, [3].filter(n%2 == 1))
      if 3%2 == 1 => new myCons(1, new myCons(3, myEmptyList.filter(n%2 == 1))
                  => new myCons(1, new myCons(3, myEmptyList))
     */
    override def filter(predicate: A => Boolean): myList[A] = {
      if(predicate(this.h)) new myCons[A](this.h, t.filter(predicate))
      else t.filter(predicate)
    }
    /*
    [1,2,3].map(n * 2)
    new myCons(2, [2,3].map(n*2))
    new myCons(2, new myCons(4, [3].map(n * 2))
    new myCons(2, new myCons(4, new myCons(6, myEmptyList.map(n * 2))))
    new myCons(2, new myCons(4, new myCons(6, myEmptyList)))
     */
    override def map[B](transformer: A => B): myList[B] = {
      new myCons[B](transformer(h), t.map(transformer))
    }
    /*
    [1,2] ++ [3,4,5]
    new myCons(1, [2] ++ [3,4,5])
    new myCons(1, new myCons(2, myEmptyList ++ [3,4,5])))
    new myCons(1, new myCons(2, new myCons(3, new myCons(4, new myCons(5, myEmptyList)))))
     */
    override def ++[B >: A](list: myList[B]): myList[B] = {
      new myCons[B](h, t ++ list)
    }

    /*
    [1,2].flatMap(n => [n, n+1])
    [1,2] ++ [2].flatMap(n => [n, n+1])
    [1,2] ++ [2,3] ++ myEmptyList.flatMap(n => [n,n+1])
    [1,2] ++ [2,3] ++ myEmptyList
    [1,2,2,3]
     */
    override def flatMap[B](transformer: A => myList[B]): myList[B] = {
      transformer(h) ++ t.flatMap(transformer)
    }
  }
}

object runner extends App {
  val listOfInt: MyListContinued.myCons[Int] = new MyListContinued.myCons[Int](1, new MyListContinued.myCons[Int](2, new MyListContinued.myCons[Int](3, MyListContinued.myEmptyList)))
  val listOfInt2: MyListContinued.myCons[Int] = new MyListContinued.myCons[Int](4, new MyListContinued.myCons[Int](5, new MyListContinued.myCons[Int](6, MyListContinued.myEmptyList)))
  println(listOfInt)
  println(listOfInt.map(new Function1[Int, Int] {
    override def apply(data: Int): Int = data * 2
  }))
  println(listOfInt.filter(new Function1[Int, Boolean] {
    override def apply(data: Int): Boolean = data % 2 == 1
  }))
  println(listOfInt ++ listOfInt2)
  println(listOfInt.flatMap(new Function1[Int, MyListContinued.myList[Int]] {
    override def apply(data: Int): MyListContinued.myList[Int] = new MyListContinued.myCons[Int](data, new MyListContinued.myCons[Int](data + 1, MyListContinued.myEmptyList))
  }))
}