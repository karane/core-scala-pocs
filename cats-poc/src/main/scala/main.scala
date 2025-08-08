import cats.*
import cats.data.*
import cats.syntax.all.*
import cats.effect.{IO, IOApp}

// === Custom Box with Functor ===
final case class Box[A](value: A)

given Functor[Box] with
  def map[A, B](fa: Box[A])(f: A => B): Box[B] =
    Box(f(fa.value))

// === Validated example ===
object Validations:
  type ValidationResult[A] = Validated[List[String], A]

  def validateName(name: String): ValidationResult[String] =
    if name.nonEmpty then name.valid else List("Name is empty").invalid

  def validateAge(age: Int): ValidationResult[Int] =
    if age >= 18 then age.valid else List("Must be 18 or older").invalid

  def validatePerson(name: String, age: Int): ValidationResult[String] =
    (validateName(name), validateAge(age)).mapN((n, a) => s"$n is $a years old")

// === EitherT example ===
object EitherTExamples:
  type ErrorOr[A] = Either[String, A]
  type IOEither[A] = EitherT[IO, String, A]

  def fetchUser(): IOEither[String] = EitherT.rightT("Alice")
  def fetchScore(): IOEither[Int] = EitherT.rightT(95)

  def result: IOEither[String] =
    for
      user <- fetchUser()
      score <- fetchScore()
    yield s"$user scored $score"

// === Main IO App ===
object Main extends IOApp.Simple:
  def run: IO[Unit] =

    // Functor
    val box = Box(10)
    val mappedBox = box.map(_ + 5)

    // Monoid
    val combinedString = "Hello, " |+| "Cats!"
    val combinedInts = 1 |+| 2 |+| 3

    // Eq
    given Eq[Int] = Eq.fromUniversalEquals
    val eqResult = (42 === 42, 42 =!= 100)

    for
      _ <- IO.println("Functor")
      _ <- IO.println(s"Box(10).map(_ + 5) = $mappedBox")

      _ <- IO.println("\nMonoid")
      _ <- IO.println(s"Strings: $combinedString")
      _ <- IO.println(s"Integers: $combinedInts")

      _ <- IO.println("\nEq")
      _ <- IO.println(s"42 === 42: ${eqResult._1}")
      _ <- IO.println(s"42 =!= 100: ${eqResult._2}")

      _ <- IO.println("\nValidated")
      valid <- IO(Validations.validatePerson("John", 15))
      _ <- IO.println(s"Validation result: $valid")

      _ <- IO.println("\nEitherT")
      eitherResult <- EitherTExamples.result.value
      _ <- IO.println(s"EitherT result: $eitherResult")

      _ <- IO.println("\nIO (user input)")
      _ <- IO.print("Enter your name: ")
      name <- IO.readLine
      _ <- IO.println(s"Hello, $name!")
    yield ()
