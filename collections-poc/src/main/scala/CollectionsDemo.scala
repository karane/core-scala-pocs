// CollectionsDemo.scala
object CollectionsDemo {

  def main(args: Array[String]): Unit = {

    println("=== 1. Immutable vs Mutable Collections ===")
    import scala.collection.mutable

    val immutableList = List(1, 2, 3)
    // immutableList(0) = 10 // not allowed

    val mutableList = mutable.ListBuffer(1, 2, 3)
    mutableList += 4 //  allowed
    println(s"Immutable: $immutableList")
    println(s"Mutable:   $mutableList")

    println("\n=== 2. List, Vector, Set, Map ===")
    val list = List(1, 2, 3, 4)
    val vector = Vector(1, 2, 3, 4)
    val set = Set(1, 2, 2, 3) // duplicates removed
    val map = Map("a" -> 1, "b" -> 2)
    println(s"List:   $list")
    println(s"Vector: $vector")
    println(s"Set:    $set")
    println(s"Map:    $map")

    println("\n=== 3. Array and Range ===")
    val arr = Array(10, 20, 30)
    arr(0) = 99
    val range = 1 to 5
    println(s"Array: ${arr.mkString(", ")}")
    println(s"Range: $range")

    println("\n=== 4. For Comprehensions with yield ===")
    val squares = for (x <- 1 to 5) yield x * x
    println(s"Squares: $squares")

    println("\n=== 5. Higher-order Functions: map, flatMap, filter ===")
    val nums = List(1, 2, 3, 4, 5)
    val mapped = nums.map(_ * 2)
    val filtered = nums.filter(_ % 2 == 0)
    val flatMapped = nums.flatMap(x => List(x, x * 10))
    println(s"map:      $mapped")
    println(s"filter:   $filtered")
    println(s"flatMap:  $flatMapped")

    println("\n=== 6. fold, reduce, scan ===")
    val sumFold = nums.fold(0)(_ + _)   // start with 0
    val sumReduce = nums.reduce(_ + _)  // no initial value
    val runningSum = nums.scan(0)(_ + _) // cumulative
    println(s"fold:  $sumFold")
    println(s"reduce: $sumReduce")
    println(s"scan:  $runningSum")

    println("\n=== 7. groupBy, partition, zip, sliding ===")
    val group = nums.groupBy(_ % 2) // even/odd
    val (evens, odds) = nums.partition(_ % 2 == 0)
    val zipped = nums.zip(List("a", "b", "c", "d", "e"))
    val slidingPairs = nums.sliding(2).toList
    println(s"groupBy: $group")
    println(s"partition: evens=$evens, odds=$odds")
    println(s"zip: $zipped")
    println(s"sliding: $slidingPairs")

    println("\n=== 8. Lazy Collections: Stream / LazyList ===")
    lazy val stream = LazyList.from(1).map(_ * 2)
    println(s"First 5 doubled numbers (lazy): ${stream.take(5).toList}")
    // Only first 5 are computed

    println("\n=== End of Demo ===")
  }
}
