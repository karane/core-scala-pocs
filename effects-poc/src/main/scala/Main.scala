import effects.{ComposableEffectExample, PureVsEffectFunction, SafeEffectExample}

object Main {
  def main(args: Array[String]): Unit = {
    PureVsEffectFunction.run()
    ComposableEffectExample.run()
    SafeEffectExample.run()
  }
}
