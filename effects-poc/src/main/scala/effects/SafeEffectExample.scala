package effects

case class SafeEffect[A](run: () => Either[Throwable, A]) {

  def map[B](f: A => B): SafeEffect[B] =
    SafeEffect(() => run() match {
      case Right(value) => Right(f(value))
      case Left(err) => Left(err)
    })

  def flatMap[B](f: A => SafeEffect[B]): SafeEffect[B] =
    SafeEffect(() => run() match {
      case Right(value) => f(value).run()
      case Left(err) => Left(err)
    })
}

object SafeEffectExample {

  val safePrint: SafeEffect[Unit] = SafeEffect(() =>
    try Right(println("Safe effect!"))
    catch { case t: Throwable => Left(t) }
  )

  val riskyEffect: SafeEffect[Int] = SafeEffect(() =>
    try Right(10 / 0) // Will fail
    catch { case t: Throwable => Left(t) }
  )

  val program: SafeEffect[Unit] = for {
    _ <- safePrint
    value <- riskyEffect
    _ <- SafeEffect(() => Right(println(s"Result: $value")))
  } yield ()

  def run(): Unit = {
    println("=== SafeEffectExample ===")
    program.run() match {
      case Right(_) => println("Program completed successfully")
      case Left(err) => println(s"Program failed with error: ${err.getMessage}")
    }
    println()
  }
}
