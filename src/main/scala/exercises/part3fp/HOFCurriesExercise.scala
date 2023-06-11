package exercises.part3fp

object HOFCurriesExercise extends App {
  /*
  1. Expand MyList
    -foreach method A => Unit
      [1,2,3].foreach(x => println(x))

     -sort function ((A,A) => Int) => MyList
       [1,2,3].sort((x,y) => y-x) => [3,2,1]

      -zipWith (list, (A,A) => B) => MyList[B]
        [1,2,3].zipWith([4,5,6], x*y) => [1*4, 2*5, 3*6] = [4, 10, 18]

       -fold(start)(function) => a value
        [1,2,3].fold(0)(x+y) = 6

   2. toCurry(f: (Int, Int) => Int) => (Int => Int => Int)
      fromCurry(f: (Int => Int => Int)) => (Int, Int) => Int

   3. compose(f,g) => x => f(g(x))
      andThen(f,g) => x => g(f(x))

   */

  /*
  Answers:
  1. Check MyListHOF.scala
  2. Check below
   */
  def toCurry(f: (Int, Int) => Int): (Int => Int => Int) =
    x => y => f(x,y)

  def fromCurry(f: Int => Int => Int): (Int, Int) => Int =
    (x, y) => f(x)(y)

  // FunctionX
  def compose(f: Int => Int, g: Int => Int): Int => Int =
    x => f(g(x))

  def andThen(f: Int => Int, g: Int => Int): Int => Int =
    x => g(f(x))

  def genericCompose[X,Y,Z](f: Y => Z, g: X => Y): X => Z =
    input => f(g(input))

  def genericAndThen[X, Y, Z](f: Y => Z, g: Z => X): Y => X =
    input => g(f(input))

  def superMultiplier: Int => Int => Int = toCurry((x,y) => x * y)
  // usage ===
  val multiply10 = superMultiplier(10)
  val answer = multiply10(15)
  println("superMultiplier: " + answer)

  def simpleMultiplier: (Int, Int) => Int = fromCurry(superMultiplier)
  // usage ===
  val result = simpleMultiplier(5 , 7)
  println("simpleMultiplier: " + result)

  val add2 = (x: Int) => x + 2
  val times3 = (x: Int) => x * 3

  val composed = compose(add2 , times3)
  val ordered = andThen(add2, times3)

  println("Composed: " + composed(5))
  println("Ordered: " + ordered(5))
}
