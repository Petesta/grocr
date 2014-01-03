package models

import anorm._
import anorm.SqlParser._

import java.util.Date

import play.api.db._
import play.api.Play.current

case class Item(id: Long, trip_id: Long, name: String, created: Date)

object Item {
  val item = {
    get[Long]("id") ~
    get[Long]("trip_id") ~
    get[String]("name") ~
    get[Date]("created") map {
      case id~trip_id~name~created => Item(id, trip_id, name, created)
    }
  }

  def all(): List[Item] = DB.withConnection { implicit c =>
    SQL("SELECT * FROM items").as(item *)
  }

  def create(name: String, created: Date) {
    DB.withConnection { implicit c =>
      SQL("INSERT INTO items (name, created) VALUES ({name, created})")
        .on('name -> name, 'created -> created)
        .executeInsert()
    }
  }
}
