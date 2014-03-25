package models

import anorm._
import anorm.SqlParser._

import java.util.Date

import play.api.db._
import play.api.Play.current

case class User(id: Long, firstName: String, lastName: String, email: String, password: String, created: Date)

object User {
  val user = {
    get[Int]("id") ~
    get[String]("firstName") ~
    get[String]("lastName") ~
    get[String]("email") ~
    get[String]("password") ~
    get[Date]("created") map {
      case id~firstName~lastName~email~password~created => User(id, firstName, lastName, email, password, created)
    }
  }

  def all(): List[User] = DB.withConnection { implicit c =>
    SQL("select * from users").as(user *)
  }

  def create(firstName: String, lastName: String, email: String, password: String) {
    DB.withConnection { implicit c =>
      SQL("insert into users(firstName, lastName, email, password, created) values ({firstName}, {lastName}, {email}, {password}, {created})").on(
        'firstName -> firstName,
        'lastName -> lastName,
        'email -> email,
        'password -> password,
        'created -> new Date()).executeInsert()
    }
  }

  def findUserID(): String = DB.withConnection { implicit c =>
    val row = SQL("select id from users order by id desc limit 1").apply().head
    row[Int]("id").toString
  }

  def authenticate(email: String, password: String): Option[User] = {
    DB.withConnection { implicit c =>
      SQL("select * from users where email = {email} and password = {password}").on(
        'email -> email,
        'password -> password
      ).(User.user.singleOpt)
    }
  }
}
