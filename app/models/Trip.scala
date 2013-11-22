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
    DB.withConnection { implicit c =>
      // Create a trip to hold the items we're creating
      // Trips have no attributes besides timestamp
      SQL("INSERT INTO trips VALUES ()").executeUpdate()

      // TODO: super hacky way to get trip id of the trip we just created
      val firstRow = SQL("SELECT id from TRIPS order by id DESC LIMIT 1").apply().head
      val tripId = firstRow[Long]("id")


      items.split("\n") foreach { name =>
        SQL("INSERT INTO items (trip_id, name) VALUES ({tripId}, {name})")
          .on('name -> name,'tripId -> tripId)
          .executeUpdate()
      }
    }
  }

}
