package models

import anorm._
import anorm.SqlParser._

import java.util.Date

import play.api.db._
import play.api.Play.current

case class Item(id: Long, tripID: Long, name: String, createdAt: Date)

object Item {
  val item = {
    get[Long]("id") ~
    get[Long]("tripID") ~
    get[String]("name") ~
    get[Date]("createdAt") map {
      case id~tripID~name~createdAt => Item(id, tripID, name, createdAt)
    }
  }

  def all(tripID: Int): List[Item] = DB.withConnection { implicit c =>
    SQL("select * from items where tripID = {tripID}").on(
      'tripID -> tripID).as(item *)
  }
}
