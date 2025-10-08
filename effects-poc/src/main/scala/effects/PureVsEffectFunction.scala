package effects

object PureVsEffectFunction {

  // Pure function: no side effects
  val square: Int => Int = x => x * x

  // Effectful function: has a side effect (printing)
  val printEffect: () => Unit = () => println("Hello from effect!")

  def run(): Unit = {
    println("=== PureVsEffectFunction ===")
    println(s"Square of 5 is ${square(5)}")
    printEffect()
    println()
  }
}
