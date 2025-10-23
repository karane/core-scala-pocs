import scala.util.{Try, Success, Failure}

object ErrorHandlingDemo {

  // --- Example 1: Option ---
  // Option is used when a value might be missing.
  def findUserName(userId: Int): Option[String] = {
    val users = Map(1 -> "Alice", 2 -> "Bob")
    users.get(userId) // returns Some("Alice") or None
  }

  // --- Example 2: Either ---
  // Either is used when you want to return a result OR an error (with details).
  def parseAge(ageStr: String): Either[String, Int] = {
    if (ageStr.forall(_.isDigit)) Right(ageStr.toInt)
    else Left(s"Invalid age: '$ageStr'")
  }

  // --- Example 3: Try ---
  // Try is used for computations that may throw exceptions.
  def divide(a: Int, b: Int): Try[Double] = Try {
    a.toDouble / b
  }

  def main(args: Array[String]): Unit = {

    // ===== Option =====
    println("=== Option Example ===")
    val maybeUser = findUserName(1)
    val maybeUnknownUser = findUserName(99)

    println(maybeUser.getOrElse("Unknown User"))      // Alice
    println(maybeUnknownUser.getOrElse("Unknown User")) // Unknown User

    // You can also map over Option
    val upper = maybeUser.map(_.toUpperCase)
    println(upper) // Some(ALICE)

    // ===== Either =====
    println("\n=== Either Example ===")
    val validAge = parseAge("30")
    val invalidAge = parseAge("thirty")

    validAge match {
      case Right(age) => println(s"Valid age: $age")
      case Left(error) => println(s"Error: $error")
    }

    invalidAge match {
      case Right(age) => println(s"Valid age: $age")
      case Left(error) => println(s"Error: $error")
    }

    // ===== Try =====
    println("\n=== Try Example ===")
    val result1 = divide(10, 2)
    val result2 = divide(10, 0)

    result1 match {
      case Success(value) => println(s"Result: $value")
      case Failure(ex)    => println(s"Error: ${ex.getMessage}")
    }

    result2 match {
      case Success(value) => println(s"Result: $value")
      case Failure(ex)    => println(s"Error: ${ex.getMessage}")
    }

    // You can also use map/flatMap with Try
    val safeComputation = divide(10, 2).map(_ * 3)
    println(s"Computation result: $safeComputation") // Success(15.0)
  }
}
