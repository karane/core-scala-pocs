import scala.concurrent._
import scala.concurrent.duration._
import ExecutionContext.Implicits.global

object MonadPatternExample extends App {

  // 1. Option example
  val maybeUser: Option[String] = Some("Karane")

  val optionResult =
    maybeUser
      .flatMap(u => Some(s"$u@example.com"))
      .flatMap(email => if (email.contains("@")) Some(email.toUpperCase) else None)

  println(s"Option result: $optionResult")


  // 2. List example
  val numbers = List(1, 2, 3)

  val listResult =
    numbers
      .flatMap(n => List(n, n * 10))
      .flatMap(n => List(n + 1))
      .map(n => s"num=$n")

  println(s"List result: $listResult")


  // 3. Future example
  def fetchUser(): Future[String] = Future {
    println("Fetching user...")
    Thread.sleep(500)
    "Karane"
  }

  def fetchEmail(user: String): Future[String] = Future {
    println(s"Fetching email for $user...")
    Thread.sleep(500)
    s"$user@example.com"
  }

  def validateEmail(email: String): Future[String] = Future {
    println(s"Validating $email...")
    Thread.sleep(500)
    if (email.contains("@example.com")) "Valid Email" else "Invalid"
  }

  val futureResult =
    fetchUser()
      .flatMap(fetchEmail)
      .flatMap(validateEmail)
      .map(_.toUpperCase)

  try {
    val output = Await.result(futureResult, 5.seconds)
    println(s"Future result: $output")
  } catch {
    case ex: TimeoutException => println(s"Timeout waiting for future: ${ex.getMessage}")
  }


  // 4. For-comprehension equivalents
  val optionFor = for {
    user <- maybeUser
    email <- Some(s"$user@example.com")
    valid <- if (email.contains("@")) Some(email.toUpperCase) else None
  } yield valid

  val listFor = for {
    n <- numbers
    m <- List(n, n * 10)
  } yield s"num=${m + 1}"

  val futureFor = for {
    u <- fetchUser()
    e <- fetchEmail(u)
    v <- validateEmail(e)
  } yield v.toUpperCase

  try {
    val outputFor = Await.result(futureFor, 5.seconds)
    println(s"For Future result: $outputFor")
  } catch {
    case ex: TimeoutException => println(s"Timeout waiting for futureFor: ${ex.getMessage}")
  }

  println(s"For Option: $optionFor")
  println(s"For List: $listFor")
}
