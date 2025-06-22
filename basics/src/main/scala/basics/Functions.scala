package basics

object Functions:
  def greet(name: String): String =
    s"Hello, $name"

  def add(x: Int, y: Int): Int = x + y

  def run(): Unit = {
    println(greet("Scala"))
    println(s"2 + 3 = ${add(2,3)}")
  }
