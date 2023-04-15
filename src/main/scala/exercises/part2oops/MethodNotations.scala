package exercises.part2oops

import scala.language.postfixOps

object MethodNotations extends App {

  // A package can not have multiple classes with the same name
  class Person(val name: String, favoriteMovie: String,val age: Int = 10) {
    def likes(movie: String): Boolean = movie.equalsIgnoreCase(this.favoriteMovie)
    def hangsOutWith(person: Person): String = s"${this.name} likes to hang out with ${person.name}"
    def +(person: Person): String = s"${this.name} likes to hang out with ${person.name}"
    def unary_! : String = s"${this.name}, what are you doing ???"
    def isAlive: Boolean = true
    def apply(): String = s"Hello ${this.name}, why do you like ${this.favoriteMovie} so much ?"
    def apply(s: String): String = s"Provided String for ${this.name} is $s ..."
    // EXERCISES :
    def +(aka: String): Person = new Person(name = this.name.concat(s" ($aka)"), favoriteMovie = this.favoriteMovie)
    def unary_+ : Person = new Person(name = this.name, favoriteMovie = this.favoriteMovie, age = this.age + 1)
    def learns(what: String): String = s"${this.name} learns $what"
    def learnsScala: String = this learns "Scala"
    def apply(times: Int): String = s"${this.name} watches ${this.favoriteMovie} $times times"

  }
  val mary = new Person(name = "Mary", favoriteMovie = "Inception")
  val tom = new Person(name = "Tom", favoriteMovie = "Fight Club")

  println(mary.likes("inception"))
  println(mary likes "inception") // equivalent
  // INFIX notation = operator notation ( syntactic sugar)
  // NOTE : infix notation works only with a single parameter

  // OPERATORS IN SCALA
  println(mary.hangsOutWith(tom))
  println(mary hangsOutWith tom)
  println(mary + tom)

  println(1 + 2)
  println(1.+(2))
  // OPERATORS ARE METHODS

  // PREFIX Notation (unary operators)
  val x = -1 // equivalent to 1.unary_-
  val y = 1.unary_-
  // unary_ prefix only works with 4 operators i.e (+ - ~ !)
  println(!mary)
  println(!tom)

  // POSTFIX Notation
  // Available to methods without parameters
  println(mary.isAlive)
  println(mary isAlive)

  // APPLY method
  println(mary.apply())
  println(tom())
  println(tom("Mary"))
  // USING OBJECTS AS METHODS

  /*
  Exercises:

  1. Overload the + operator
      mary + "the rockstar" => new Person with name "Mary (the rockstar)"
  2. Add an age to Person class
      Add a unary operator => new person with age + 1
      +mary => mary with age incremented
  3. Add a "learns" method in the Person class => "Mary learns Scala"
      Add a learnsScala method , call the learns method with "Scala"
      Use it in postfix notation
  4. Overload the apply method apply(2) => Mary watches Inception 2 times
   */
  println("=== *** ===\nExercices :")
  val newMary = mary + "The Keeper"
  println(newMary.name)
  println((+newMary).age)
  println(newMary learnsScala)
  println(newMary(3))
}
