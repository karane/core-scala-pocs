package org.karane
package service

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import org.mockito.Mockito.*
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentMatchers.*
import org.scalatestplus.mockito.MockitoSugar

import scala.language.postfixOps

class PaymentProcessorTest extends AnyFunSuite with Matchers with MockitoSugar {

  test("stub methods with return values") {
    val userRepo = mock[UserRepository]
    when(userRepo.findUser("user1")).thenReturn(Some("Alice"))
    when(userRepo.saveUser(any[String], any[String])).thenReturn(true)

    val processor = new PaymentProcessor(userRepo)
    processor.processPayment("user1", 100.0) shouldBe true

    verify(userRepo).findUser("user1")
    verify(userRepo).saveUser("user1", "Payment processed: 100.0")
  }

  test("stub method to throw exception") {
    val userRepo = mock[UserRepository]
    when(userRepo.findUser("user2")).thenThrow(new RuntimeException("DB error"))

    val processor = new PaymentProcessor(userRepo)

    assertThrows[RuntimeException] {
      processor.processPayment("user2", 50.0)
    }

    verify(userRepo).findUser("user2")
    verify(userRepo, never()).saveUser(any[String], any[String])
  }

  test("verify call counts") {
    val userRepo = mock[UserRepository]
    when(userRepo.findUser(any[String])).thenReturn(Some("User"))
    when(userRepo.saveUser(any[String], any[String])).thenReturn(true)

    val processor = new PaymentProcessor(userRepo)
    processor.processPayment("userA", 10.0)
    processor.processPayment("userB", 20.0)

    verify(userRepo, times(2)).findUser(any[String])
    verify(userRepo, times(2)).saveUser(any[String], any[String])
    verify(userRepo, never()).saveUser("userC", "Payment processed: 30.0")
  }

  test("argument captor") {
    val userRepo = mock[UserRepository]
    when(userRepo.findUser(any[String])).thenReturn(Some("CapturedUser"))
    when(userRepo.saveUser(any[String], any[String])).thenReturn(true)

    val processor = new PaymentProcessor(userRepo)
    processor.processPayment("capturedUser", 75.0)

    val userIdCaptor = ArgumentCaptor.forClass(classOf[String])
    val dataCaptor = ArgumentCaptor.forClass(classOf[String])

    verify(userRepo).saveUser(userIdCaptor.capture(), dataCaptor.capture())

    userIdCaptor.getValue shouldBe "capturedUser"
    dataCaptor.getValue shouldBe "Payment processed: 75.0"
  }

  test("reset mocks") {
    val userRepo = mock[UserRepository]
    when(userRepo.findUser("resetUser")).thenReturn(Some("UserBeforeReset"))

    userRepo.findUser("resetUser") shouldBe Some("UserBeforeReset")

    reset(userRepo) // resets all stubs and interactions

    when(userRepo.findUser("resetUser")).thenReturn(Some("UserAfterReset"))

    userRepo.findUser("resetUser") shouldBe Some("UserAfterReset")
  }

// Failing because of java
  test("spy example") {
    val realList = scala.collection.mutable.ListBuffer(1, 2, 3)
    val spyList = spy(realList)

    spyList += 4
    spyList.size shouldBe 4

    verify(spyList).+=(4)
  }
}
