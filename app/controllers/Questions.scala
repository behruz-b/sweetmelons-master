package controllers

import com.typesafe.scalalogging.slf4j.LazyLogging
import models._
import play.api.Logger
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._
import play.api.libs.json.Json._
import play.api.mvc._
import scala.slick.lifted.TableQuery

class Questions extends Controller with TestingAuth with LazyLogging {

  import models._
  import models.JsonFormats._

  val questions = TableQuery[QuestionsTable]

  def questionsList = DBAction { implicit rs =>
    Logger.info(s"Questions = ${questions.list}")
    Ok(toJson(questions.list))
  }

  def addQuestion = DBAction(parse.json) { implicit rs =>
    rs.request.body.validate[Question].map { question =>
      val questionId = (questions returning questions.map(_.id)) += question
      Ok(toJson(questionId))
    }.recoverTotal { errors =>
      BadRequest(errors.toString)
    }
  }

}
