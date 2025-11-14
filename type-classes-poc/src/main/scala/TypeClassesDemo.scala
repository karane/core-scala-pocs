object TypeClassesDemo:
  // --- 1. Define the type classes ---

  // Show: defines how to convert a value to a String
  trait Show[A]:
    def show(a: A): String

  // Eq: defines equality for a type
  trait Eq[A]:
    def eqv(x: A, y: A): Boolean

  // Monoid: combines two values and defines an identity element
  trait Monoid[A]:
    def empty: A
    def combine(x: A, y: A): A


  // --- 2. Create instances for basic types ---
  given Show[Int] with
    def show(a: Int): String = s"Int($a)"

  given Show[String] with
    def show(a: String): String = s""""$a""""

  given Eq[Int] with
    def eqv(x: Int, y: Int): Boolean = x == y

  given Eq[String] with
    def eqv(x: String, y: String): Boolean = x.equalsIgnoreCase(y)

  given Monoid[Int] with
    def empty: Int = 0
    def combine(x: Int, y: Int): Int = x + y

  given Monoid[String] with
    def empty: String = ""
    def combine(x: String, y: String): String = x + y


  // --- 3. Define generic helper functions using context bounds ---
  def printShow[A: Show](a: A): Unit =
    println(s"Show: ${summon[Show[A]].show(a)}")

  def isEqual[A: Eq](x: A, y: A): Unit =
    val eq = summon[Eq[A]]
    println(s"Eq: ${x} == ${y}? ${eq.eqv(x, y)}")

  def combineAll[A: Monoid](values: List[A]): A =
    val m = summon[Monoid[A]]
    values.foldLeft(m.empty)(m.combine)


  // --- 4. Define a custom type and provide instances for it ---
  case class Person(name: String, age: Int)

  given Show[Person] with
    def show(p: Person): String = s"Person(name=${p.name}, age=${p.age})"

  given Eq[Person] with
    def eqv(p1: Person, p2: Person): Boolean =
      p1.name.equalsIgnoreCase(p2.name) && p1.age == p2.age

  given Monoid[Person] with
    def empty: Person = Person("", 0)
    def combine(p1: Person, p2: Person): Person =
      Person(p1.name + p2.name, p1.age + p2.age)


  // --- 5. Main demo ---

  @main def runTypeClassesDemo(): Unit =
    println("=== Show ===")
    printShow(42)
    printShow("Scala")
    printShow(Person("Alice", 30))

    println("\n=== Eq ===")
    isEqual(10, 10)
    isEqual("Scala", "scala")
    isEqual(Person("Bob", 25), Person("bob", 25))

    println("\n=== Monoid ===")
    println(s"Combine Ints: ${combineAll(List(1, 2, 3, 4))}")
    println(s"Combine Strings: ${combineAll(List("Hello, ", "Type ", "Classes!"))}")
    println(s"Combine Persons: ${combineAll(List(Person("A", 10), Person("B", 15)))}")
