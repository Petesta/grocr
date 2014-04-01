package models

import anorm._
import anorm.SqlParser._

import java.util.{Date}

import play.api.db._
import play.api.Play.current

case class Trip(id: Int, createdAt: Date)

object Trip {
  // Trip is a parser that given a JDBC ResultSet row with at least
  // an id and createdAt column, is able to create a Trip value
  val trip = {
    get[Int]("id") ~
    get[Date]("createdAt") map {
      case id~createdAt => Trip(id, createdAt)
    }
  }

  def find(id: Int) = DB.withConnection { implicit c =>
    SQL("select * from trips where id = {id}").as(trip *)
  }

  def createTrip(items: String) {
    DB.withConnection { implicit c =>
      SQL("insert into trips(createdAt) values ({createdAt})").on(
        'createdAt -> new Date()
      ).executeUpdate()

      val firstRow = SQL("select id from trips order by id desc limit 1").apply().head
      val tripID = firstRow[Int]("id")

      items.split("\n") foreach { name =>
        SQL("insert into items(tripID, name, createdAt) values ({tripID}, {name}, {createdAt})").on(
          'tripID -> tripID,
          'name -> name,
          'createdAt -> new Date()).executeUpdate()
      }
    }
  }

  def getLatestTrip(): Int = DB.withConnection { implicit c =>
    val firstRow = SQL("select id from trips order by id desc limit 1").apply().head
    firstRow[Int]("id")
  }
}
