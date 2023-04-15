package exercises.part2oops

object AbstractDataTypes extends App {

  abstract class Animal {
    val creatureType: String
    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"
    override def eat: Unit = println("Crunch Crunch")
  }

  // Ultimate Abstract Data Type: Trait
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType = "Reptile"
    override def eat: Unit = println("Grr Grr")
    override def eat(animal: Animal): Unit = println(s"I am a $creatureType and I am eating ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  // traits vs abstract class
  // 1- Traits do not have Constructor Parameters
  // 2- Multiple traits may be inherited by the same class
  // 3- traits = behaviour but abstract class = "thing"

  // Common thing between abstract class & trait
  // 1- Both can have abstract/non-abstract data members or methods

}
