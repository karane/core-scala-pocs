object PartialFunctionExample extends App {

  // A PartialFunction[Int, String] that only handles even numbers
  val evenHandler: PartialFunction[Int, String] = {
    case x if x % 2 == 0 => s"$x is even"
  }

  // Another PartialFunction[Int, String] that handles odd numbers
  val oddHandler: PartialFunction[Int, String] = {
    case x if x % 2 != 0 => s"$x is odd"
  }

  // Combine them using `orElse`
  val numberClassifier = evenHandler orElse oddHandler

  // Demonstrate usage
  println(numberClassifier(2))  // 2 is even
  println(numberClassifier(3))  // 3 is odd

  // Check if function is defined for an input
  println(numberClassifier.isDefinedAt(10))  // true
  println(numberClassifier.isDefinedAt(-1))  // true

  // Example: a function that only handles positive numbers
  val positiveHandler: PartialFunction[Int, String] = {
    case x if x > 0 => s"$x is positive"
  }

  // A function that handles negatives
  val negativeHandler: PartialFunction[Int, String] = {
    case x if x < 0 => s"$x is negative"
  }

  // Combine and then transform the output using `andThen`
  val signClassifier =
    (positiveHandler orElse negativeHandler) andThen (_.toUpperCase)

  println(signClassifier(10))   // 10 IS POSITIVE
  println(signClassifier(-5))   // -5 IS NEGATIVE

  // You can use PartialFunctions with collections like collect()
  val numbers = List(-3, -2, 0, 1, 2, 3)

  val positivesOnly: PartialFunction[Int, Int] = {
    case x if x > 0 => x
  }

  val result = numbers.collect(positivesOnly)
  println(result) // List(1, 2, 3)

  // Handling undefined inputs safely
  try {
    println(positiveHandler(0)) // Will throw MatchError
  } catch {
    case e: MatchError => println("MatchError: input not defined for this partial function")
  }
}
