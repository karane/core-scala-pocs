package models

// Demonstrates: Traits and mixins
trait Worker {
  def work(): Unit
}

trait Speaker {
  def speak(message: String): Unit = println(s"Speaking: $message")
}
