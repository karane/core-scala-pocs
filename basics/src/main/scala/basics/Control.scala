package basics

object Control:
  def run(): Unit =
    val age = 20
    if age >= 18 then
      println("You're an adult")
    else
      println("You're a minor")

    for i <- 1 to 3 do println(s"Loop $i")

    val result = age match
      case 18 => "Just became adult"
      case a if a > 18 => "Adult"
      case _ => "Minor"


    println(s"Match result: $result")