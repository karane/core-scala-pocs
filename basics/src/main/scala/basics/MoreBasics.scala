package basics

import scala.util.{Success, Failure, Try}

object MoreBasics:

  def showTypes(): Unit =
    val a: Int = 42
    val b = "Hello"
    val c = 3.14
    println(s"a: $a b: $b  c: $c")

  def defaultParams(x: Int = 10, y: Int = 5): Int =
    x + y

  def namedParams(): Unit =
    println(defaultParams())
    println(defaultParams(y = 100))
    println(defaultParams(x = 1, y = 2))

  def tuples(): Unit =
    val pair = (1, "Scala")
    val (id, lang) = pair
    println(s"ID: $id Language: $lang")

  def options(): Unit =
    val maybeName : Option[String] = Some("Unknown")
    val noneVal : Option[String] = None

    println(maybeName.getOrElse("Unknown"))
    println(noneVal.map( _ * 2).getOrElse(0))

  def matchCase(): Unit =
    case class User(name: String, age: Int)
    val user = User("Alice", 25)

    val msg = user match
      case User("Alice", age) => s"Alice is $age"
      case User(_, age) if age < 18 => "Ungerage"
      case _ => "Someone else"

    println(msg)

  def forComprehension(): Unit =
    val result = for
      x <- List(1, 2)
      y <- List("a", "b")
    yield s"$x$y"

    println(result)

  def lazyVal(): Unit =
    lazy val expensive = {
      println("Computing expensive ...")
      42
    }

    println("Before access ...")
    println(s"Value: $expensive")

  def tryExample(): Unit =
    def safeDivide(x: Int, y: Int): Try[Int] = Try(x/y)

    safeDivide(10, 0) match
      case Success(value) => println(s"Result: $value")
      case Failure(e) => println(s"Error: ${e.getMessage}")


  def contextParams(): Unit =
    given Int = 10

    def multiply(x: Int)(using factor: Int): Int =
      x * factor

    println(multiply(5))

  def run(): Unit =
    println("\n== Type Inference ==");
    showTypes()
    println("\n== Default & Named Params ==");
    namedParams()
    println("\n== Tuples ==");
    tuples()
    println("\n== Options ==");
    options()
    println("\n== Pattern Match on Case Class ==");
    matchCase()
    println("\n== For Comprehension ==");
    forComprehension()
    println("\n== Lazy Evaluation ==");
    lazyVal()
    println("\n== Try / Exception Handling ==");
    tryExample()
    println("\n== Context Parameters ==");
    contextParams()