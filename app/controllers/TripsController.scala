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
    Ok(views.html.trips.newTrip(Item.all(1), tripForm))
  }

  def showTrip(id: Int) = Action {
    println(Item.all(id))
    Ok(views.html.trips.showTrip(Item.all(id)))
  }

  def createTrip = Action { implicit request =>
    tripForm.bindFromRequest.fold(
      errors => BadRequest(views.html.trips.newTrip(Item.all(1), errors)),
      items => {
        Trip.createTrip(items)
        val tripNumber = Trip.getLatestTrip()
        Redirect(routes.TripsController.showTrip(tripNumber))
      }
    )
  }
}
