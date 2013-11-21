package models

import anorm._
import anorm.SqlParser._

import play.api.db._
import play.api.Play.current


case class Task(id: Long, label: String)

object Task {
  // Task is a parser that given a JDBC ResultSet row with at least
  // an id and label column, is able to create a Task value
  val task = {
    get[Long]("id") ~
    get[String]("label") map {
      case id~label => Task(id, label)
    }
  }

  // withConnection automatically creates/releases a JDBC connection
  // We parse the ResultSet using the `task *` parser
  def all(): List[Task] = DB.withConnection { implicit c =>
    SQL("SELECT * FROM task").as(task *)
  }

  def create(label: String) {
    DB.withConnection { implicit c =>
      SQL("INSERT INTO task (label) VALUES ({label})").on('label -> label)
        .executeUpdate()
    }
  }

  def delete(id: Long) {
    DB.withConnection { implicit c =>
      SQL("DELETE FROM task WHERE id = {id}").on('id -> id)
        .executeUpdate()
    }
  }
}
