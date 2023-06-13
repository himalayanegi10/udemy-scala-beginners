package exercises.part3fp

object MapFlatMapFilterFor extends App {

  val list = List(1,2,3)
  println(list)
  println(list.head)
  println(list.tail)

  // Map
  println(list.map(_ * 4))
  println(list.map(_.toString + "th number"))

  // FlatMap
  val aFlatMapFunction = (x: Int) => List(x + 1, x + 3)
  println(list.flatMap(aFlatMapFunction))

  // Filter
  println(list.filter(_ % 2 == 0))

  // Exercise
  // Print all combinations between two Lists = (a1, a2, ... d4)
  val numbers = List(1,2,3,4)
  val chars = List('a','b','c','d')
  val result = chars.flatMap( x => numbers.map(x + _.toString))
  println(result)
  val colors = List("Black", "White")

  // "iterations"
  val result2 = chars.flatMap(c => numbers.flatMap(n => colors.map(color => "" + c + n + "-" + color)))
  println(result2)

  // foreach
  list.foreach(println)

  // for Comprehensions
  val forComprehensions = for {
    c <- chars
    n <- numbers
    color <- colors
  } yield "" + c + n + "-" + color
  println(forComprehensions)

  for {
    n <- numbers
  } println(n)

  // syntax overload
  println(list.map (x =>
    x * 2
  ))

  /*
  Exercises:
  1. MyList support for comprehensions
  2. A small collection of at most ONE element - Maybe[+T]
      -map, flatmap, filter

   */
  /*
  Answer:
  1. for comprehensions internally use map, flatMap and filter functions with below signature
      map(f: A => B): MyList[B]
      filter(f: A => Boolean): MyList[A]
      flatMap(f: A => MyList[B]): MyList[B]
   */
}
