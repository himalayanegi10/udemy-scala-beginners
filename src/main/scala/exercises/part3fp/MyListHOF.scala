package exercises.part3fp

import javax.naming.NameNotFoundException
import scala.runtime.Nothing$

/*
  1. Generic Trait MyPredicate[-T] with a method test(T) => Boolean
  2. Generic Trait MyTransformer[-A,B] with a method transform(A) => B
  3. MyListFPAnonymousFunctions:
    -map(transformer) => MyListFPAnonymousFunctions
    -filter(predicate) => MyListFPAnonymousFunctions
    -flatMap(transformer) => MyListFPAnonymousFunctions

    class EvenPredicate extends MyPredicate[Int]
    class StringToIntTransformer extends MyTransformer[String,Int]

    Pseudo code
    [1,2,3].map(n*2) = [2,4,6]
    [1,2,3,4].filter(n%2 == 0) = [2,4]
    [1,2,3].flatMap(n => [n,n+1]) = [1,2,2,3,3,4]
 */
object MyListHOF extends App {

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

    // HOFs
    def foreach(f: A => Unit): Unit
    def sort(compare: (A, A) => Int): myList[A]
    def zipwith[B,C](list: myList[B], zip: (A,B) => C): myList[C]
    def fold[B](start: B)(operator: (B,A) => B): B
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

    // HOFs
    override def foreach(f: Nothing => Unit): Unit = ()

    override def sort(compare: (Nothing, Nothing) => Int): myList[Nothing] = myEmptyList

    override def zipwith[B, C](list: myList[B], zip: (Nothing, B) => C): myList[C] = {
      if(!list.isEmpty) throw new RuntimeException("Lists do not have same lengths")
      else myEmptyList
    }

    override def fold[B](start: B)(operator: (B, Nothing) => B): B = start
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

    override def foreach(f: A => Unit): Unit = {
      f(h)
      t.foreach(f)
    }

    override def sort(compare: (A, A) => Int): myList[A] = {
      // insertion sort
      def insert(a: A, value: MyListHOF.myList[A]): myList[A] = {
        if(value.isEmpty) new myCons[A](a, myEmptyList)
        else if(compare(a, value.head) <= 0) new myCons[A](a, value)
        else new myCons[A](value.head, insert(a, value.tail))
      }

      val sortedTail = t.sort(compare)
      insert(h, sortedTail)
    }

    /*
    Generally used by Spark Programmers
     */
    override def zipwith[B, C](list: myList[B], zip: (A, B) => C): myList[C] = {
      if(list.isEmpty) throw new RuntimeException("Lists do not have same lengths")
      else new myCons[C](zip(h, list.head), t.zipwith(list.tail, zip))
    }

    /*
    Reduce method in Hadoop/Scala:
    [1,2,3].fold(0)(_ + _) ===
    ---------------------------
    [2,3].fold(0 + 1)(_ + _)
    [3].fold(1 + 2)(_ + _)
    [].fold(3 + 3)(_ + _)
    = 6
    ---------------------------
    Above is a reduce method!!!
     */
    override def fold[B](start: B)(operator: (B, A) => B): B = {
      t.fold(operator(start, h))(operator)
    }
  }
}

object runnerFPHOF extends App {
  val listOfInt: MyListHOF.myCons[Int] = new MyListHOF.myCons[Int](1, new MyListHOF.myCons[Int](2, new MyListHOF.myCons[Int](3, MyListHOF.myEmptyList)))
  val listOfInt2: MyListHOF.myCons[Int] = new MyListHOF.myCons[Int](4, new MyListHOF.myCons[Int](5, new MyListHOF.myCons[Int](6, MyListHOF.myEmptyList)))
  println(listOfInt)
  println(listOfInt.map(x => x * 2))
  println(listOfInt.map(_ * 2))
  println(listOfInt.filter(x => x%2 == 0))
  println(listOfInt.filter(_%2 == 0))
  println(listOfInt ++ listOfInt2)
  println(listOfInt.flatMap(data => new MyListHOF.myCons[Int](data, new MyListHOF.myCons[Int](data + 1, MyListHOF.myEmptyList))))
  println("HOFs")
  listOfInt.foreach(println)
  println("Sort:")
  val sortedList = listOfInt.sort((x,y) => y - x)
  println(sortedList)
  println("ZipWith:")
  println(listOfInt)
  println(listOfInt2)
  val zippedList = listOfInt2.zipwith[Int, String](listOfInt, (x,y) => x.toString + "--" + y.toString)
  println(zippedList)
  println("Fold")
  println(listOfInt.fold(2)(_ + _))
}