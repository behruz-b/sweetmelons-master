package controllers

import com.typesafe.scalalogging.slf4j.LazyLogging
import play.api.Logger
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._
import play.api.libs.json.Json._
import play.api.mvc._

import scala.slick.lifted.TableQuery

class UserAnswers extends Controller with TestingAuth with LazyLogging {

  import models.JsonFormats._
  import models._

  val userAnswers = TableQuery[UserAnswersTable]

  def addUserAnswer = DBAction(parse.json) { implicit rs =>
    rs.request.body.validate[UserAnswer].map { answer =>
      val answerId = (userAnswers returning userAnswers.map(_.id)) += answer
      Ok(toJson(answerId))
    }.recoverTotal { errors =>
      BadRequest(errors.toString)
    }
  }

}
