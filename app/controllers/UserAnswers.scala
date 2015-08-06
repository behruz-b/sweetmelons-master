package controllers

import com.typesafe.scalalogging.slf4j.LazyLogging
import play.api.Logger
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._
import play.api.libs.json.Json._
import play.api.mvc._

import scala.slick.lifted.TableQuery

class UserAnswers extends Controller with LazyLogging {

  import models.JsonFormats._
  import models._

  val userAnswers = TableQuery[UserAnswersTable]
  val users = TableQuery[UsersTable]

  def getUserAnswers = DBAction { implicit rs =>
    val ss = userAnswers.groupBy(_.userId)
      .map {
      case (usr, answer) =>
        (usr, answer.map(_.isRight).sum, answer.map(_.remaining).sum)
    }

    val testResult = (for {
      (user, currentScore) <- users leftJoin ss on (_.id===_._1)
    } yield (user, currentScore)).list
      .map {
      case (user, currentScore) =>
        val currScore = user.currentScore
        val testScore = currentScore._2.get * 2
        val ttScore = currScore + testScore
        ResultUser(user.firstName, user.lastName, user.address, currentScore._3.get, currScore, testScore, ttScore)
    }

    Ok(toJson(testResult))
  }

  def addUserAnswer = DBAction(parse.json) { implicit rs =>
    rs.request.body.validate[UserAnswer].map { answer =>
      val answerId = (userAnswers returning userAnswers.map(_.id)) += answer
      Ok(toJson(answerId))
    }.recoverTotal { errors =>
      BadRequest(errors.toString)
    }
  }

}
