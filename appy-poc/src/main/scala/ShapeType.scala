enum ShapeType:
  case Circle, Rectangle

trait Shape:
  def area: Double

case class Circle(radius: Double) extends Shape:
  override def area: Double = Math.PI * radius * radius

case class Rectangle(width: Double, height: Double) extends Shape:
  override def area: Double = width * height

// Factory object that uses apply and overloading
object Shape:

  // Overloaded methods for creating shapes directly
  def create(radius: Double): Shape = Circle(radius)
  def create(width: Double, height: Double): Shape = Rectangle(width, height)

  // Apply method — makes creation look like a constructor call
  def apply(shapeType: ShapeType, dims: Double*): Shape =
    shapeType match
      case ShapeType.Circle if dims.length == 1 =>
        Circle(dims.head)
      case ShapeType.Rectangle if dims.length == 2 =>
        Rectangle(dims(0), dims(1))
      case _ =>
        throw new IllegalArgumentException("Invalid arguments for shape type")

// Main app to test
@main def Main(): Unit =
  // Using overloaded methods
  val c1 = Shape.create(3.0)
  val r1 = Shape.create(4.0, 5.0)

  // Using factory apply method
  val c2 = Shape(ShapeType.Circle, 2.5)
  val r2 = Shape(ShapeType.Rectangle, 2.0, 6.0)

  println(s"Circle1 area: ${c1.area}")
  println(s"Rectangle1 area: ${r1.area}")
  println(s"Circle2 area: ${c2.area}")
  println(s"Rectangle2 area: ${r2.area}")
