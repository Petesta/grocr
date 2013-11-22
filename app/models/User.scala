package models

import anorm._
import anorm.SqlParser._

import java.util.Date

import play.api.db._
import play.api.Play.current

case class User(id: Long, firstName: String, lastName: String, createdAt: Date)

object User {
  val user = {
    get[Long]("id") ~
    get[String]("first_name") ~
    get[String]("last_name") ~
    get[Date]("created_at") map {
      case id~firstName~lastName~createdAt => User(id, firstName, lastName, createdAt)
    }
  }

  def create(firstName: String, lastName: String) {}
}
