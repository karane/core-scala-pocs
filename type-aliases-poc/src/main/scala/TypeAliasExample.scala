object TypeAliasExample {

  // --- Example 1: Basic alias ---
  type UserId = Int
  type UserName = String

  case class User(id: UserId, name: UserName)

  // --- Example 2: Alias for a function type ---
  type StringTransformer = String => String

  // --- Example 3: Alias for a complex generic type ---
  type StringMap = Map[String, String]

  // --- Example 4: Alias with multiple type parameters ---
  type Pair[A, B] = (A, B)

  // --- Example 5: Nested alias inside an object or class ---
  object Database {
    type Row = Map[String, Any]
  }

  def main(args: Array[String]): Unit = {

    // Using UserId and UserName (readability improvement)
    val user = User(1, "Alice")
    println(s"User: $user")

    // Using StringTransformer
    val shout: StringTransformer = _.toUpperCase
    val whisper: StringTransformer = _.toLowerCase

    println(shout("Scala Rocks!"))
    println(whisper("Scala Rocks!"))

    // Using StringMap
    val config: StringMap = Map(
      "host" -> "localhost",
      "port" -> "8080"
    )
    println(s"Config: $config")

    // Using Pair
    val coordinates: Pair[Double, Double] = (12.34, 56.78)
    println(s"Coordinates: $coordinates")

    // Using nested alias
    val row: Database.Row = Map(
      "id" -> 1,
      "name" -> "Alice",
      "age" -> 30
    )
    println(s"Database row: $row")
  }
}
