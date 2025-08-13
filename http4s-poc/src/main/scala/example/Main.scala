package example

import cats.effect._
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.implicits._
import org.http4s.ember.server._
import org.http4s.server.staticcontent._
import org.http4s.circe._
import io.circe.generic.auto._
import io.circe.syntax._
import cats.syntax.semigroupk._
import com.comcast.ip4s._

object Main extends IOApp.Simple {

  // Example case class for JSON
  case class Greeting(message: String)

  // Query param matchers
  object AParam extends QueryParamDecoderMatcher[Int]("a")
  object BParam extends QueryParamDecoderMatcher[Int]("b")

  // Routes
  val helloRoute = HttpRoutes.of[IO] {
    case GET -> Root / "hello" / name =>
      Ok(Greeting(s"Hello, $name!").asJson)

    // Query parameter example: /add?a=3&b=7
    case GET -> Root / "add" :? AParam(a) +& BParam(b) =>
      Ok(Greeting(s"The sum is ${a + b}").asJson)
  }

  // Static file route
  val staticRoute = fileService[IO](FileService.Config("src/main/resources/public"))

  // Combine all routes
  val httpApp = (helloRoute <+> staticRoute).orNotFound

  // Server
  val run = EmberServerBuilder
    .default[IO]
    .withHost(ipv4"0.0.0.0")
    .withPort(port"8080")
    .withHttpApp(httpApp)
    .build
    .useForever
}
