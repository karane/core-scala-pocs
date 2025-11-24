package models

// Demonstrates: Inheritance, overriding, traits, and mixins
class Employee(name: String, age: Int, val position: String)
  extends Person(name, age)
    with Worker
    with Speaker {

  override def greet(): Unit =
    println(s"Hello, I’m $name, a $position, and I’m $age years old.")

  def work(): Unit = println(s"$name is working hard as a $position.")
}
