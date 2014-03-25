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
      "firstName" -> nonEmptyText(minLength = 3),
      "lastName" -> nonEmptyText(minLength = 3),
      "email" -> email,
      "password" -> nonEmptyText(minLength = 5),
    )
  )

  def newUser = Action {
    Ok(views.html.users.new_user(userForm)).withCookies("id", User.findUserID())
  }

  def createUser = Action { implicit request =>
    userForm.bindFromRequest.fold(
      error => BadRequest(views.html.users.new_user(userForm)),
      {
        case (firstName, lastName, email, password) => {
          User.create(firstName, lastName, email, password)
          Redirect(routes.UsersController.newUser)
        }
      }
    )
  }
}

object Auth extends Controller {
  val loginForm = Form(
    tuple(
      'email -> email,
      'password -> text
    ) verifying ("Invalid email or password", result => result match {
      case (email, password) => check(email, password)
    })
  )



}
