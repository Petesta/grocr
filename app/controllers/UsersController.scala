package controllers

import java.util.{Date}

import play.api._
import play.api.data._
import play.api.data.Forms._
import play.api.data.validation.Constraints._
import play.api.mvc._

import models.User

object UsersController extends Controller {
  val userForm = Form(
    tuple(
      "firstName" -> nonEmptyText,
      "lastName" -> nonEmptyText,
      "email" -> email,
      "password" -> nonEmptyText(minLength = 5)
    )
  )

  def newUser = Action { implicit request =>
    Ok(views.html.users.newUser(userForm))
  }

  def createUser = Action { implicit request =>
    userForm.bindFromRequest.fold(
      errors => BadRequest(views.html.users.newUser(errors)),
      {
        case (firstName, lastName, email, password) => {
          User.create(firstName, lastName, email, password)
          Ok(views.html.users.summary(email))
        }
      }
    )
  }
}

object Auth extends Controller {
  val loginForm = Form(
    tuple(
      "email" -> email,
      "password" -> text
    ) verifying ("Invalid email or password", result => result match {
      case (email, password) => User.authenticate(email, password).isDefined
    })
  )

  def login = Action { implicit request =>
    Ok(views.html.users.login(loginForm))
  }

  def authenticate = Action { implicit request =>
    loginForm.bindFromRequest.fold(
      errors => BadRequest(views.html.users.login(errors)),
        user => {
          println(s"Inside authenticate, Log in was successful!")
          //Redirect(routes.UsersController.newUser).withSession("email" -> user._1)
          Ok(views.html.users.summary(user._1)).withSession("email" -> user._1)
        }
    )
  }

  def logout = Action {
    Redirect(routes.Auth.login).withNewSession.flashing(
      "success" -> "You are now logged out!")
  }
}
