package basics

object Basics:
  def run(): Unit =
    val immutable = "Scala"
    var mutable = 10
    mutable += 5

    val pi: Double = 3.1415
    val isFun: Boolean = true

    println(s"Immutable: $immutable, Mutable: $mutable")
    println(s"Pi: $pi, Is Scala fun: $isFun")
