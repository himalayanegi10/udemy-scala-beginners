package exercises.part2oops

import scala.annotation.tailrec

object OOBasics extends App {
  val person = new Person("Himalaya", 25)
  println(person.age)
  person.greet("Varun")
  person.greet()

  println("=== *** ===")
  val person2 = new Person
  println(person2.age)
  person2.greet("Bob")
  person2.greet()
}


// Constructor
class Person(name: String, val age: Int = 20) { // Putting a val/var in front of fields will make them data members
  // body of class Person
  val height: Int = 170
  // it is a field
  println("Hello, I am executed !")

  // method
  def greet(name: String): Unit = println(s"${this.name} says: Hi, $name")

  // overloading
  def greet(): Unit = println(s"Hello I am $name")

  // constructor overloading
  // Auxiliary Constructor calls the Primary Constructor
  def this(name: String) = this(name, 10)
  def this() = this("Alice")
}

// EXERCISES

/*
  Novel and a Writer
  Writer: first name, surname, year
    -method fullname (first name + surname of writer)
  Novel: name, year of release, author[Writer]
    -method authorAge (age at year of release)
    -isWrittenBy (author)
    -copy new year of release = new Instance of Novel
 */

/*
  Counter Class
    - receives an Int value
    - method current count
    - method to increment/decrement => new counter
    - overload inc/dec to receive an amount
 */


object Exercise extends App {
  println("Exercises:")

  val charlesDickens = new Writer(surname = "Dickens", firstName = "Charles", year = 1812)

  val aNovel = new Novel(name = "A Christmas Carol", yearOfRelease = 1843, writer = charlesDickens)
  println(f"Author Age: ${aNovel.authorAge()}%s")
  println(f"Written By: ${aNovel.isWrittenBy()}%s")
  val newRevision = aNovel.copy(year = 1845)
  println(f"Author Age: ${newRevision.authorAge()}%s")
  println(f"Written By: ${newRevision.isWrittenBy()}%s")
  println(aNovel.compare(new Novel(name = "A Christmas Carol", yearOfRelease = 1843, writer = charlesDickens)))
  println(aNovel.compare(aNovel))

  println("=== *** ===")

  val counter = new Counter
  counter.inc.inc.print
  counter.inc(10).print
  counter.print
}

class Writer(val firstName: String, val surname: String, val year: Int) {
  def getFullName(): String = firstName.concat(" ").concat(surname)
}

class Novel(val name: String, val yearOfRelease: Int, val writer: Writer) {
  def authorAge(): Int = yearOfRelease - writer.year
  def isWrittenBy(): String = writer.getFullName()
  def compare(imposter: Novel): Boolean = imposter == this
  def copy(year: Int): Novel = new Novel(yearOfRelease = year, writer = this.writer, name = this.name)
}

class Counter(val count: Int = 0) {
  println("Counter Initialized !!!")

  def inc:Counter = {
    println("incrementing")
    new Counter(count + 1)
  }

  def dec: Counter = {
    println("decrementing")
    new Counter(count - 1)
  }

  def inc(n: Int): Counter = {
    if (n == 0) this
    else this.inc.inc(n-1)
  }

  def dec(n: Int): Counter = {
    if (n == 0) this
    else this.dec.dec(n-1)
  }

  def print: Unit = println(s"Count is ${this.count}")
}