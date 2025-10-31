object TypeInferenceExample {

  def main(args: Array[String]): Unit = {

    // --- Example 1: Inferring variable types ---
    val x = 10            // Compiler infers: Int
    val y = 3.14          // Compiler infers: Double
    val name = "Scala"    // Compiler infers: String

    println(s"x: $x, y: $y, name: $name")

    // --- Example 2: Inference in expressions ---
    val sum = x + y       // Compiler infers: Double (since Int + Double = Double)
    val message = s"Hello, $name!" // Compiler infers: String

    println(s"sum: $sum, message: $message")

    // --- Example 3: Inference in function return types ---
    def square(n: Int) = n * n // Return type inferred: Int
    def greet(user: String) = s"Hello, $user" // Return type inferred: String

    println(square(5))
    println(greet("World"))

    // --- Example 4: Explicit type helps clarity for complex cases ---
    // The compiler infers 'Any' if types differ, which can be less safe
    val mixed = List(1, "two", 3.0) // Inferred type: List[Any]

    // Better: declare it explicitly to restrict types
    val numbers: List[Int] = List(1, 2, 3) // Type explicitly stated

    println(s"Mixed list: $mixed")
    println(s"Numbers list: $numbers")

    // --- Example 5: Function literals (lambdas) ---
    val double = (n: Int) => n * 2 // parameter type given
    val increment: Int => Int = n => n + 1 // type on the left, inferred on right

    println(double(4))
    println(increment(10))

    // --- Example 6: Inference in for-comprehensions ---
    val result = for (n <- numbers) yield n * 10
    // Compiler infers: List[Int]
    println(s"Inferred List type: $result")
  }
}
