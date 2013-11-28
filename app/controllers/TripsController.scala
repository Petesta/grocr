package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import models.Trip
import models.Item

object TripsController extends Controller {
  val tripForm = Form(
    "items" -> nonEmptyText
  )

  def newTrip = Action {
    Ok(views.html.trips.new_trip(Item.all(), tripForm))
  }

  def createTrip = Action { implicit request =>
    tripForm.bindFromRequest.fold(
      errors => BadRequest(views.html.trips.new_trip(Item.all(), tripForm)),
      items => {
        Trip.create(items)
        Redirect(routes.TripsController.newTrip)
      }
    )
  }

}

