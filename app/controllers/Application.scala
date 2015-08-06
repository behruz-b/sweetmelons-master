package controllers

import com.typesafe.scalalogging.slf4j.LazyLogging
import jp.t2v.lab.play2.auth.{OptionalAuthElement, LoginLogout}
import models.UserRoleEnum._
import models._
import play.api.libs.json.Json._

import play.api.mvc._
import play.api.libs.json._
import play.api.Play.current
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import models.JsonFormats._
import views.html

import scala.concurrent.Future
import scala.slick.lifted.TableQuery


object Application extends Controller with LoginLogout with OptionalAuthElement with TestingAuthConfig with LazyLogging {
  val users = TableQuery[UsersTable]

  def _getUserPermission(userRole: UserRole) = {
    val isAdmin = userRole == UserRoleEnum.ADMIN
    val permission = Permission(
      Test = PermTest(isAdmin, isAdmin),
      User = PermUser(isAdmin, isAdmin)
    )

    Json.toJson(permission)
  }
  def index = Action {
    Ok(views.html.index("bbn"))
  }

  def signIn = Action.async(parse.json) { implicit request =>
    DB.withSession { implicit session =>
      logger.info(s"authenticate $request")

      request.body.validate[Credential].map { credential =>
        val found = users.filter { r =>
          r.email === credential.login && r.password === credential.password
        }

        found.list.headOption match {
          case Some(user) =>
            Ok(toJson(user))
            gotoLoginSucceeded(user.id.get)
          case _ =>
            Future.successful(BadRequest("Incorrect login or password"))
        }
      }.recoverTotal { errors =>
        Future.successful(BadRequest(errors.toString))
      }
    }
  }

  def signOut() = Action.async { implicit request =>
    gotoLogoutSucceeded.map(_.flashing(
      "success" -> "You've been logged out"
    ))
  }

}