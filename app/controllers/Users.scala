package controllers

import com.typesafe.scalalogging.slf4j.LazyLogging
import models._
import play.api.Logger
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._
import play.api.libs.json.Json._
import play.api.mvc._

import scala.slick.lifted.TableQuery

class Users extends Controller with TestingAuth  with LazyLogging {

  import models._
  import models.JsonFormats._

  val users = TableQuery[UsersTable]

  def usersList = DBAction { implicit rs =>
    Logger.info(s"Users = ${users.list}")
    Ok(toJson(users.list))
  }

  def getUser = DBAction(parse.json) { implicit rs =>
    rs.request.body.validate[Credential].map { user =>
      val found = users.filter { r =>
        r.email === user.login && r.password === user.password
      }

      Ok(toJson(found.firstOption.get))
    }.recoverTotal { errors =>
      BadRequest(errors.toString)
    }
  }

  def signUp = DBAction(parse.json) { implicit rs =>
    rs.request.body.validate[Account].map { user =>
      val userId = (users returning users.map(_.id)) += Account(None, user.firstName, user.lastName, user.address, user.email, user.password, Some(UserRoleEnum.USER), user.currentScore, 0, 0)
      val addedUser = users.findBy(_.id).applied(userId.toInt).first
      Ok(toJson(addedUser))
    }.recoverTotal { errors =>
      BadRequest(errors.toString)
    }
  }

  def deleteUser(id: Int) = AuthAction(AuthorityKey -> hasRole(UserRoleEnum.ADMIN)) { implicit rs =>
    DBAction { implicit req =>
      findById(id) match {
        case Some(entity) => {
          users.filter(_.id === id).delete;
          Ok("User deleted")
        }
        case None => Ok("")
      }
    }
  }

  def findById(id: Int)(implicit session: play.api.db.slick.Config.driver.simple.Session): Option[Account] = {
    val byId = users.findBy(_.id)
    byId(id).list.headOption
  }

  def updateUser(id: Int) = DBAction(parse.json) { implicit rs =>
    rs.request.body.validate[Account].map { user =>
      users.filter(_.id === id).update(user)
      Ok("User successfully updated")
    }.recoverTotal { errors =>
      BadRequest(errors.toString)
    }
  }

}
