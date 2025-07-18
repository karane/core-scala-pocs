
import io.circe._
import io.circe.parser._
import io.circe.syntax._
import io.circe.generic.auto._

object Main extends App {


  case class Person(name: String, age: Int)
  val p = Person("Alice", 28)
  val jsonString = p.asJson.noSpaces
  println(s"Encoded JSON: $jsonString")

  val parsed = decode[Person](jsonString)
  println(s"Decoded: $parsed")

  case class Address(city: String, zip: String)
  case class User(name: String, address: Address)

  val user = User("Bob", Address("NYC", "10001"))
  val userJson = user.asJson.spaces2
  println(s"User JSON:\n$userJson")

  val userBack = decode[User](userJson)
  println(s"User from JSON: $userBack")

  val people = List(Person("Tom", 22), Person("Sue", 35))
  val peopleJson = people.asJson
  println(s"People JSON: ${peopleJson.spaces2}")

  val decodedPeople = decode[List[Person]](peopleJson.noSpaces)
  println(s"Decoded People: $decodedPeople")

  sealed trait Shape
  case class Circle(radius: Double) extends Shape
  case class Rectangle(width: Double, height: Double) extends Shape

  // Custom encoders/decoders
  implicit val shapeEncoder: Encoder[Shape] = Encoder.instance {
    case c: Circle    => c.asJson.deepMerge(Json.obj(("type", Json.fromString("circle"))))
    case r: Rectangle => r.asJson.deepMerge(Json.obj(("type", Json.fromString("rectangle"))))
  }

  implicit val shapeDecoder: Decoder[Shape] = Decoder.instance { cursor =>
    cursor.downField("type").as[String].flatMap {
      case "circle"    => cursor.as[Circle]
      case "rectangle" => cursor.as[Rectangle]
      case other       => Left(DecodingFailure(s"Unknown type: $other", cursor.history))
    }
  }

  val shapes: List[Shape] = List(Circle(1.5), Rectangle(3, 4))
  val shapesJson = shapes.asJson
  println(s"Shapes JSON:\n${shapesJson.spaces2}")

  val shapesBack = decode[List[Shape]](shapesJson.noSpaces)
  println(s"Decoded Shapes: $shapesBack")
}
