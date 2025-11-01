object ImmutabilityExample extends App {

  // Immutable data: 'val' makes this reference constant.
  val balance = 100

  // Function that returns a new balance instead of modifying the old one.
  def deposit(balance: Int, amount: Int): Int = balance + amount

  // Function that withdraws money safely (no mutation)
  def withdraw(balance: Int, amount: Int): Int =
    if (amount > balance) balance else balance - amount

  // Referential transparency demonstration:
  // deposit(balance, 50) can be replaced by its value (150)
  val newBalance = deposit(balance, 50)
  val finalBalance = withdraw(newBalance, 20)

  println(s"Original balance: $balance")
  println(s"After deposit: $newBalance")
  println(s"After withdrawal: $finalBalance")

  // Referential transparency check:
  val result1 = withdraw(deposit(balance, 50), 20)
  val result2 = withdraw(150, 20)

  println(s"Both give the same result? ${result1 == result2}")
}
