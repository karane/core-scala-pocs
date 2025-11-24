package models

// Demonstrates: Classes, constructors, encapsulation, access modifiers
class Person(private var _name: String, private var _age: Int) {
  // Getter and setter with encapsulation
  def name: String = _name
  def name_=(newName: String): Unit = _name = newName

  def age: Int = _age
  def age_=(newAge: Int): Unit = {
    if (newAge >= 0) _age = newAge
    else println("Age cannot be negative")
  }

  def greet(): Unit = println(s"Hi, I’m $name and I’m $age years old.")
}

// Companion object for Person
object Person {
  // Acts as a factory
  def apply(name: String, age: Int): Person = new Person(name, age)

  def adult(name: String): Person = new Person(name, 18)
}
