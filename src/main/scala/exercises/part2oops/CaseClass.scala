package exercises.part2oops

object CaseClass extends App {
  case class Person(name: String, age: Int)

  // 1. Class parameters are fields
  val jim = new Person("Jim", 34)
  println(jim.name)
  // 2. Sensible toString
  println(jim)
  // 3. Equal and hashcode are implemented out of the box
  val jim2 = new Person("Jim", 34)
  println(jim == jim2)
  // 4. Case classes have handy copy menthod
  val jim3 = jim.copy(age=20)
  println(jim3)
  // 5. Case classes have companion objects
  val thePerson = Person // A companion object
  val mary = Person("Mary", 20) // Calling apply method; apply() method of object acts as a constructor
  // 6. Case classes are serializable\
  // Used in Akka
  // 7. Case classes have extractor Patterns = Case classes can be used in Pattern matching

  // Case Objects
  case object India {
    def name: String = "Bharat Mahan"
  } // Case objects are same as case classes but do not have any companion object as they themselves are object
}
/*
Takeaways:
1. CCs are quick lightweight datastructures with little boilerplate
2. Companion objects are already implemented out of the box
3. Sensible equals, hashCode, toString
4. Auto promoted parameters to fields
5. Easy cloning
6. Case objects which are same os CCs but are objects
 */