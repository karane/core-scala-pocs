object FunctionDemo extends App {

  // 1. Function literal assigned to a variable
  val square: Int => Int = x => x * x
  val triple: Int => Int = x => x * 3

  // 2. Original list
  val numbers = List(1, 2, 3, 4, 5, 6)

  println(s"Original numbers: $numbers")

  // 3. Anonymous function used in filter (keep even numbers)
  val evens = numbers.filter(x => x % 2 == 0)
  println(s"Evens: $evens")

  // 4. Anonymous function with shorthand to map (square each number)
  val squared = evens.map(square)  // using the function literal 'square'
  println(s"Squared evens: $squared")

  // 5. Chaining multiple anonymous functions (double and then add 1)
  val transformed = numbers
    .filter(_ % 2 != 0)   // keep odd numbers
    .map(triple)           // triple each number
    .map(_ + 1)            // add 1 to each result

  println(s"Transformed odds: $transformed")

  // 6. Higher-order function: apply a function to all numbers
  def applyFunctionToList(lst: List[Int], f: Int => Int): List[Int] = lst.map(f)

  val doubledNumbers = applyFunctionToList(numbers, x => x * 2)  // anonymous function
  println(s"Doubled numbers: $doubledNumbers")

}
