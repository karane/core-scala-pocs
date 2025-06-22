package basics

object Classes:
  class Person(val name: String, val age: Int):
    def greet(): String = s"Hi, I'm $name and I'm $age years old."

  case class Animal(name: String, species: String)

  trait Speaker:
    def speak(): String

  class Dog(name: String) extends Speaker:
    def speak(): String = s"$name says Woof!"

  def run(): Unit =
    val john = new Person("John", 30)
    val cat = Animal("Milo", "Cat")
    val dog = new Dog("Buddy")

    println(john.greet())
    println(s"Animal: ${cat.name} is a ${cat.species}")
    println(dog.speak())
