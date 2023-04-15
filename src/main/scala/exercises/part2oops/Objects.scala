package exercises.part2oops

object Objects {

  // SCALA DOES NOT HAVE CLASS LEVEL FUNCTIONALITY i.e it doesn't have "static"
  object Person {
    // "static"/class-level functionality
    val eyes = 2
    def canFly: Boolean = false

    // factory method
    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }
  class Person(val name: String) {
    // instance level functionality
  }
  // COMPANIONS
  // Using Object Person & Class Person together are called as COMPANIONS
  // Companions can access each others Private members

  // SCALA OBJECT = SINGLETON Instance
  println("Will I be executed ??? Ans -> YES")

  def main(args: Array[String]): Unit = {
    println(Person.eyes)
    println(Person.canFly)
    val mary = new Person("Mary")
    val john = new Person("John")
    println(mary == john)

    val person1 = Person
    val person2 = Person
    println(person1 == person2)

    val bobbie = Person(mary, john)
    println(bobbie.name)
  }

  // SCALA Application = Scala Object with
  // def main(args: Array[String]): Unit
}
