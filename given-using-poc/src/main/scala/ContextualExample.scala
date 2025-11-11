// ContextualExample.scala
object ContextualExample:

  // 1. Type class definition
  trait Show[T]:
    def show(value: T): String

  // 2. Given instances (contextual implementations)
  given Show[Int] with
    def show(value: Int): String = s"Int($value)"

  given Show[String] with
    def show(value: String): String = s"String('$value')"

  // 3. Function with contextual parameter (using)
  def printValue[T](value: T)(using s: Show[T]): Unit =
    println(s.show(value))

  // 4. Function with multiple contextual parameters
  def printPair[A, B](a: A, b: B)(using sa: Show[A], sb: Show[B]): Unit =
    println(s"Pair: ${sa.show(a)} and ${sb.show(b)}")

  // 5. Contextual dependency (service-style)
  trait Logger:
    def log(message: String): Unit

  given ConsoleLogger: Logger with
    def log(message: String): Unit =
      println(s"[LOG] $message")

  def process(data: String)(using logger: Logger): Unit =
    logger.log(s"Processing: $data")

  // 6. Explicitly summon a given instance
  def summonExample(): Unit =
    val s = summon[Show[Int]]
    println(s.show(99))

  // 7. Entry point
  @main def run(): Unit =
    println("=== Using given / using / summon in Scala 3 ===")

    printValue(42)
    printValue("Scala 3")
    printPair(10, "cats")

    summonExample()
    process("Important data")
