package exercises.part2oops

object Generics extends App {

  class MyList[A] {
    // USE THE TYPE A
  }
  class MyMap[Key,Value] {

  }

  val listOfIntegers = new MyList[Integer]
  val listOfString = new MyList[String]

  // Generic Method
  object MyList { // Objects can not be made Generics
//    def empty[A]: MyList[A] = ???
    def empty[A]: MyList[A] = new MyList[A]
  }

  val emptyListOfIntegers = MyList.empty[Integer]

  println("====== VARIANCE PROBLEM ======")
  class Animal
  class Dogs extends Animal
  class Cats extends Animal
  // Both Dogs and Cats extends Animal, but can a list of Dogs or a list of Cats extends a list of Animals ?

  // 1. Covariance : Yes the can
  class CovariantList[+A]
  val covariantListOfAnimals: CovariantList[Animal] = new CovariantList[Cats]
  // covariantListOfAnimals.add(new Dogs) ??? HARD QUESTION => we return a list of Animals
  // Syntax => def add[B >: A](element: B): anotherList[B] = ???

  // 2. Invariance : No
  class InvariantList[A]
  val invariantListOfAnimals: InvariantList[Animal] = new InvariantList[Animal]

  // 3. Contravariance : Hell No !!!
  class ContravariantList[-A]
  val contravariantListOfCats: ContravariantList[Cats] = new ContravariantList[Animal]

  // BOUNDED Types
  class Cage[A <: Animal](animal: A) // SUBTYPE i.e A must be a subtype of class Animal
  val cage = new Cage(new Cats)

  class superCage[A >: Animal](animal: A) // SUPERTYPE i.e A must be a supertype of class Animal
  val supercage = new superCage() // object that is passed here should be a superclass of class Animal

  println("Covariance Hard Problem solved using bounded types")
  class anotherList[+A]{
//      def add(element: A): anotherList[A] = ???
//    Above statement throws this error "Covariant type A occurs in contravariant position in type A of value element"
    def add[B >: A](element: B): anotherList[B] = ???
  }

  // Exercise: Expand anotherList to be generic
}
