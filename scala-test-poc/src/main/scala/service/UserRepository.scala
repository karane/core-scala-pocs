package org.karane
package service

trait UserRepository {
  def findUser(userId: String): Option[String]
  def saveUser(userId: String, data: String): Boolean
}
