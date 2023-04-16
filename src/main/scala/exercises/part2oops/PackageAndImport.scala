package exercises.part2oops

// aliasing
import exercises.part2oops.Exceptions.{MathCalculationException => mathExcept, OverflowException => overflow,
  UnderflowException => underflow, pocketCalculator}


object PackageAndImport extends App {
  // Package members are accessed by their simple name
  val pc1 = new pocketCalculator(3,5)
  // import the package
  val pc2 = new Exceptions.OverflowException // fully qualified name
  // packages are in hierarchy
  // matching folder structure

  // package objects : these are the files in which we store constants/methods that we will use throughout the package.
  // their name is package.scala and is allocated once per package
  // this file is not required to be imported
  println(SPEED_OF_LIGHT)
  sayHello
  // imports
//  1. Either use fully qualified name
  val pc0 = new Exceptions.myException
//  2. Or use aliasing
  val pc3 = new underflow
  val pc4 = new overflow
  val pc5 = new mathExcept

  // default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???
}
/*
Takeaways:
- package = a group af definitions under the same name
- To use a definition
  be in the same package
  import the package
- Best practice - Mirror the folder structure
- package objects hold standalone methods/constants
  it is one per package
- Aliasing in imports
 */