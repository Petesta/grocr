package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._

import models.Trip

object TripsController extends Controller {

  val tripForm = Form(
    "items" -> nonEmptyText
  )

  //def index = Action {
    //Redirect(routes.Application.tasks)
  //}

  //def tasks = Action {
    //Ok(views.html.index(Task.all(), tripForm))
  //}

  def newTrip = Action {
    Ok(views.html.trips.new_trip(tripForm))
    //Ok(views.html.trips.new_trip())
  }

  def createTrip = Action { implicit request =>
    tripForm.bindFromRequest.fold(
      errors => BadRequest(views.html.trips.new_trip(tripForm)),
      label => {
        //Trip.create(label)
        Redirect(routes.TripsController.newTrip)
      }
    )
  }

  //def deleteTask(id: Long) = Action {
    //Task.delete(id)
    //Redirect(routes.Application.tasks)
  //}
}

