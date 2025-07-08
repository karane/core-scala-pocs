package basics

// Read is our custom type class
trait Read[A] {
  def read(s: String): A
}

// Companion object with helper and instances
object Read {
  // Summoner method
  def apply[A](implicit reader: Read[A]): Read[A] = reader

  // Type class instances
  implicit val intRead: Read[Int] = (s: String) => s.toInt
  implicit val doubleRead: Read[Double] = (s: String) => s.toDouble
  implicit val stringRead: Read[String] = (s: String) => s

  // You can also define for custom types
  case class Person(name: String, age: Int)

  implicit val personRead: Read[Person] = (s: String) => {
    val parts = s.split(",")
    Person(parts(0), parts(1).toInt)
  }
}

// A function that uses the type class via context bound
object ReaderUtil {
  def parse[A: Read](s: String): A = {
    val reader = implicitly[Read[A]]
    reader.read(s)
  }
}

object ImplicitsDemo {

  def run(): Unit = {
    import Read._
    import ReaderUtil._ //brings parse to the context

    val i: Int = parse[Int]("42")
    val d: Double = parse[Double]("3.14")
    val s: String = parse[String]("hello")
    val p: Person = parse[Person]("Alice,30")

    println(i) // 42
    println(d) // 3.14
    println(s) // hello
    println(p) // Person(Alice,30)
  }
}

