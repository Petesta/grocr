package models

import anorm._
import anorm.SqlParser._

import java.util.Date

import play.api.db._
import play.api.Play.current

case class Trip(id: Long, createdAt: Date)

object Trip {
  // Trip is a parser that given a JDBC ResultSet row with at least
  // an id and createdAt column, is able to create a Trip value
  val trip = {
    get[Long]("id") ~
    get[Date]("created_at") map {
      case id~createdAt => Trip(id, createdAt)
    }
  }

  // withConnection automatically creates/releases a JDBC connection
  // We parse the ResultSet using the `trip *` parser
  //def all(): List[Trip] = DB.withConnection { implicit c =>
    //SQL("SELECT * FROM trips").as(trip *)
  //}

  def create(items: String) {
    // TODO: parse items, create one for each
    DB.withConnection { implicit c =>
      //SQL("INSERT INTO trips (label) VALUES ({label})").on('label -> label)
        //.executeUpdate()
    }
  }

  //def delete(id: Long) {
    //DB.withConnection { implicit c =>
      //SQL("DELETE FROM trip WHERE id = {id}").on('id -> id)
        //.executeUpdate()
    //}
  //}
}
