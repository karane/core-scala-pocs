object PatternMatchingExamples extends App {

  // 1. Basic pattern matching with integers
  val number = 3
  val numberDesc = number match {
    case 1 => "One"
    case 2 => "Two"
    case 3 => "Three"
    case _ => "Other"
  }
  println(numberDesc) // Three

  // 2. Pattern matching with strings
  val color = "green"
  val colorMsg = color match {
    case "red" => "Stop!"
    case "green" => "Go!"
    case "yellow" => "Caution!"
    case _ => "Unknown color"
  }
  println(colorMsg) // Go!

  // 3. Pattern matching with types
  def matchType(x: Any): String = x match {
    case s: String => s"String: $s"
    case i: Int => s"Int: $i"
    case _ => "Unknown type"
  }
  println(matchType(42))      // Int: 42
  println(matchType("Scala")) // String: Scala

  // 4. Pattern matching with guards
  val x = 15
  val parity = x match {
    case n if n % 2 == 0 => "Even"
    case n if n % 2 != 0 => "Odd"
  }
  println(parity) // Odd

  // 5. Case classes and pattern matching
  sealed trait Shape
  case class Circle(radius: Double) extends Shape
  case class Rectangle(width: Double, height: Double) extends Shape

  def describeShape(shape: Shape): String = shape match {
    case Circle(r) => s"Circle with radius $r"
    case Rectangle(w, h) => s"Rectangle $w x $h"
  }
  println(describeShape(Circle(5)))       // Circle with radius 5.0
  println(describeShape(Rectangle(4, 7))) // Rectangle 4.0 x 7.0

  // 6. Pattern matching with tuples
  val point = (2, 3)
  val pointDesc = point match {
    case (0, 0) => "Origin"
    case (x, 0) => s"X-axis at $x"
    case (0, y) => s"Y-axis at $y"
    case (x, y) => s"Point at ($x,$y)"
  }
  println(pointDesc) // Point at (2,3)

  // 7. Pattern matching with lists
  val numbers = List(1, 2, 3)
  val listDesc = numbers match {
    case Nil => "Empty list"
    case head :: Nil => s"Single element: $head"
    case head :: second :: tail => s"First: $head, Second: $second, Rest: $tail"
  }
  println(listDesc) // First: 1, Second: 2, Rest: List(3)

  // 8. Nested pattern matching
  val shapes: List[Shape] = List(Circle(2), Rectangle(3, 4))
  shapes.foreach {
    case Circle(r) => println(s"Nested Circle radius: $r")
    case Rectangle(w, h) => println(s"Nested Rectangle $w x $h")
  }
  // Output:
  // Nested Circle radius: 2.0
  // Nested Rectangle 3.0 x 4.0

  // 9. Pattern matching with extractors (using unapply)
  object EvenNumber {
    def unapply(x: Int): Boolean = x % 2 == 0
  }

  val num = 10
  val result = num match {
    case EvenNumber() => s"$num is even"
    case _ => s"$num is odd"
  }
  println(result) // 10 is even

}
