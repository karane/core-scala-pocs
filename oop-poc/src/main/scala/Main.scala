import models._

object Main {
  def main(args: Array[String]): Unit = {
    // --- Classes & Constructors ---
    val p = new Person("Alice", 25)
    p.greet()
    println()

    // Using companion object factory method
    val bob = Person.adult("Bob")
    bob.greet()
    println()

    // --- Encapsulation ---
    bob.age = -30
    println(s"Bob's new age: ${bob.age}")
    println()

    // --- Abstract Class & Inheritance ---
    val dog = new Dog("Rex")
    dog.makeSound()
    dog.move()
    println()

    // --- Traits & Mixins ---
    val emp = new Employee("Carol", 28, "Software Engineer")
    emp.greet()
    emp.work()
    emp.speak("Scala is awesome!")
    println()
  }
}
