object PureFunctionsExample {

  // PURE FUNCTION EXAMPLE
  // It always produces the same output for the same input,
  // and does not depend on or modify any external state.
  def add(a: Int, b: Int): Int = a + b

  // Another pure function example
  def multiplyBy2(x: Int): Int = x * 2

  // You can safely compose pure functions
  def doubleSum(a: Int, b: Int): Int = multiplyBy2(add(a, b))


  // IMPURE FUNCTION EXAMPLE
  // It depends on external mutable state (counter)
  // and modifies it, so it’s not pure.
  var counter = 0

  def incrementCounter(): Int = {
    counter += 1
    counter
  }

  // Another impure example: side effect (printing to console)
  def greet(name: String): Unit = {
    println(s"Hello, $name!")  // causes a side effect
  }


  def main(args: Array[String]): Unit = {
    // --- PURE FUNCTIONS ---
    println(add(2, 3))           // always 5
    println(add(2, 3))           // still 5
    println(doubleSum(2, 3))     // 10 (since (2+3)*2)

    // --- IMPURE FUNCTIONS ---
    println(incrementCounter())  // 1
    println(incrementCounter())  // 2 (different result!)
    greet("Alice")               // prints to console (side effect)
  }
}
