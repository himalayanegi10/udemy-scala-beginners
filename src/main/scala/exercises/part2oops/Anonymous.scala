package exercises.part2oops

object Anonymous extends App {

  abstract class Animal {
    def eat: Unit
  }
  val funnyAnimal: Animal = new Animal {
    override def eat: Unit = println("I am a funny animal")
  }
  println(funnyAnimal.getClass)
  // above line prints "class exercises.part2oops.Anonymous$$anon$1"
  // Behind the scene, the compiler does below things
  /*
    class Anonymous$$anon$1 extends Animal {
      override def eat: Unit = println("Hisss")
    }
    val funnyAnimal2 :Animal = new Anonymous$$anon$1
    println(funnyAnimal2.getClass)
    // above line prints "class exercises.part2oops.Anonymous$Anonymous$$anon$1"
   */
  class Person(val name: String) {
    def getName: String = this.name
  }
  val aPerson: Person = new Person("Himalaya") {
    override def getName: String = s"Name is ${this.name}"
  }
  println(aPerson.getName)
}

/*
Takeaways:
1) We can instantiate types and override fields or methods on the spot
  trait Animal {
    def eat: Unit
  }
  val predator = new Animal {
    override def eat: Unit = println("RAWR!")
  }
 Rules:
  -Pass required constructor arguments if needed
  -Implement all abstract methods/fields

 Works for both traits and classes(abstract or non-abstract)
 */
