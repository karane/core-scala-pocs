package models

abstract class Animal(val name: String) {
  def makeSound(): Unit
  def move(): Unit = println(s"$name is moving")
}

class Dog(name: String) extends Animal(name) {
  override def makeSound(): Unit = println(s"$name says: Woof!")
}
