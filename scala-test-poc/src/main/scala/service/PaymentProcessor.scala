package org.karane
package service

class PaymentProcessor(userRepo: UserRepository) {

  def processPayment(userId: String, amount: Double): Boolean = {
    userRepo.findUser(userId) match {
      case Some(user) =>
        userRepo.saveUser(userId, s"Payment processed: $amount")
      case None =>
        false
    }
  }
}
