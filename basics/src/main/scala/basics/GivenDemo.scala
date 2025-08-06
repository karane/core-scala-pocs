package basics

object GivenDemo:
  // Define a custom ordering for Ints (descending order)
  given descendingOrdering: Ordering[Int] with
    def compare(a: Int, b: Int): Int = b.compareTo(a)
  // Define a custom ordering for Ints (descending order)
  given descendingOrderingString: Ordering[String] with
    def compare(a: String, b: String): Int = b.compareTo(a)

  // A function that takes an implicit Ordering[Int] (using context bound)
  def sortDescending(xs: List[Int])(using ord: Ordering[Int]): List[Int] =
    xs.sorted

  // A function that takes an implicit Ordering[Int] (using context bound)
  def sortDescendingString(xs: List[String])(using ord: Ordering[String]): List[String] =
    xs.sorted

  @main def run(): Unit =
    val numbers = List(5, 2, 9, 1)
    val alphabet = List("Alfa", "Beta", "Zeta", "Delta")

    val sorted = sortDescending(numbers) // uses the given Ordering automatically
    println(sorted) // Output: List(9, 5, 2, 1)

    val sortedStrings = sortDescendingString(alphabet)
    println(sortedStrings)