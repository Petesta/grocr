//package controllers
//
//import play.api._
//import play.api.mvc._
//import play.api.data._
//import play.api.data.Forms._
//
//import models.{Trip}
////import models.{Item, Trip}
//
//object ItemsController extends Controller {
//  val itemForm = Form(
//    "name" -> nonEmptyText
//  )
//
//  def newItem = Action {
//    Ok(views.html.items.new_item(itemForm))
//  }
//
//  //def allItems = Action {
//  //  Ok(views.html.items.all_items(Item.all()))
//  //}
//
//
//}
