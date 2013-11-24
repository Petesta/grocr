package models

import anorm._
import anorm.SqlParser._

import java.util.Date

import play.api.db._
import play.api.Play.current

case class User(id: Long, email: String, password: String, created: Date)

object User {
  val user = {
    get[Long]("id") ~
    get[String]("email") ~
    get[String]("password") ~
    get[Date]("created") map {
      case id~email~password~created => User(id, email, password, created)
    }
  }

  def create(email: String, password: String, created: Date) {
    DB.withConnection { implicit c =>
      SQL("insert into user(email, password, created) values ({email, password, created})").on(
        'email -> email,
        'password -> password,
        'created -> created).executeInsert()
    }
  }
}
