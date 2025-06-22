package basics

object Collections:
  def run(): Unit =
    val nums = List(1, 2, 3, 4)
    val doubled = nums.map(_ * 2)

    val capitals = Map("France" -> "Paris", "Japan" -> "Tokyo")

    val maybeName: Option[String] = Some("Scala")

    println(s"Doubled: $doubled")
    println(s"Capital of Japan: ${capitals.get("Japan").getOrElse("Unknown")}")

    maybeName match
      case Some(name) => println(s"Name: $name")
      case None       => println("No name")
