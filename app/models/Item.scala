package models

import anorm._
import anorm.SqlParser._

import java.util.Date

import play.api.db._
import play.api.Play.current

//case class Item(id: Long, trip_id: Long, name: String)
//
//object Item {
//  val item = {
//    get[Long]("id") ~
//    get[Long]("trip_id") ~
//    get[String]("name") ~
//    get[Date]("created_at") map {
//      // TODO: createdAt
//      case id~tripId~name~createdAt => Trip(id, tripId, name, createdAt)
//    }
//  }
//
//  def all(): List[Item] = DB.withConnection { implicit c =>
//    SQL("SELECT * FROM items").as(item *)
//  }
//}
