package controllers

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.Constraints._

case class UsersController(email: String, password: String)

object UsersController extends Controller {
  val userForm = Form(
    mapping(
      "email" -> email,
      "password" -> nonEmptyText(minLength = 5)
    )(UsersController.apply)(UsersController.unapply)
  )

  def newUser = Action {
    Ok(views.html.users.new_user(userForm))
  }

  def createUser = Action { implicit request =>
    userForm.bindFromRequest.fold(
      error => BadRequest(views.html.users.new_user(userForm)),
      label => {
        Redirect(routes.UsersController.newUser)
      }
    )
  }

  def createUsers = TODO
}
