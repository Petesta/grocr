package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import models.{Item, Trip}

object ItemsController extends Controller {


  def allItems = Action {
    Ok(views.html.items.all_items(Item.all()))
  }


}

