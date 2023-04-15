package exercises.part2oops

object Inheritance extends App {
  class Animal {
    val creatureType: String = "wild"
    def eat: Unit = println("Nom Nom")
  }
  class Cat extends Animal {
    def crunch: Unit = {
      eat
      println("Crunch Crunch")
    }
  }
  val cat = new Cat
  cat.crunch

  // Access modifiers are of 4 types : public, private, protected, no access specified

  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name,0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name)

  // overriding
  class Dog extends Animal {
    override val creatureType: String = "domestic"
    override def eat: Unit = println("Crunch Crunch")
  }
  val dog = new Dog
  dog.eat
  println(dog.creatureType)

  class Rabbit(override val creatureType: String) extends Animal {
    override def eat: Unit = {
      super.eat
      println("Slient munch")
    }
  }
  val rabbit = new Rabbit("both")
  rabbit.eat
  println(rabbit.creatureType)

  // type substitution (broad: Polymorphism)
  val unknownAnimal: Animal = new Rabbit("pet")
  unknownAnimal.eat

  //super

  // How to prevent overriding
  // 1- Put final to members
  // 2- Put final to the class
  // 3- Put sealed to the class, class can be extended in CURRENT FILE, but won't be extended in other files.

}
