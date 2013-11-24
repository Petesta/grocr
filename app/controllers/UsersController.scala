package controllers

import java.util.{Date}

import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.Constraints._

import models.User

object UsersController extends Controller {
  val userForm = Form(
    tuple(
      "email" -> email,
      "password" -> nonEmptyText(minLength = 5),
      "created" -> date("MM/dd/yy")
    )
  )

  def newUser = Action {
    Ok(views.html.users.new_user(userForm))
  }

  def createUser = Action { implicit request =>
    userForm.bindFromRequest.fold(
      error => BadRequest(views.html.users.new_user(userForm)),
      {
        case (email, password, created) => {
          User.create(email, password, created)
          Redirect(routes.UsersController.newUser)
        }
      }
    )
  }
}
