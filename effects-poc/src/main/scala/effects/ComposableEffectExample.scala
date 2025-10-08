package effects

case class ComposableEffect[A](run: () => A) {
  def map[B](f: A => B): ComposableEffect[B] = ComposableEffect(() => f(run()))
  def flatMap[B](f: A => ComposableEffect[B]): ComposableEffect[B] =
    ComposableEffect(() => f(run()).run())
}

object ComposableEffectExample {

  val pureEffect: ComposableEffect[Int] = ComposableEffect(() => 42)
  val printEffect: ComposableEffect[Unit] = ComposableEffect(() => println("Hello, Effects!"))

  val program: ComposableEffect[Unit] = for {
    _ <- printEffect
    value <- pureEffect
    _ <- ComposableEffect(() => println(s"The result of the pure effect is $value"))
  } yield ()

  def run(): Unit = {
    println("=== ComposableEffectExample ===")
    program.run()
    println()
  }
}
