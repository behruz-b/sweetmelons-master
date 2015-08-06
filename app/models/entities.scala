package models

import play.api.db.slick.Config.driver.simple._
import play.api.i18n.{Lang, Messages}
import play.api.libs.functional.syntax._
import play.api.libs.json._
import java.util.Date

/**
 * Created by bunyod on 08/03/15.
 */

trait BaseEnum extends Enumeration {
  implicit val enumMapper = MappedColumnType.base[Value, Int](_.id, this.apply)

  val enumPrefix = ""

  def getI18eName(name: String): String = {
    Messages(s"${enumPrefix}.${name}")(Lang("en"))
  }

  def getEnumList(): Seq[(Int, String)] = {
    values.toSeq map { name =>
      (name.id, Messages(s"${enumPrefix}.${name.toString}")(Lang("en")))
    }
  }
}
trait Date2SqlDate {
  implicit val date2SqlDate = MappedColumnType.base[Date, java.sql.Timestamp](
    d => new java.sql.Timestamp(d.getTime),
    d => new java.util.Date(d.getTime)
  )
}

case class PermUser(View: Boolean, Modify: Boolean)
case class PermTest(View: Boolean, Modify: Boolean)

case class Permission (Test: PermTest,
                       User: PermUser) {
}

case class ResultUser(firstName: String,
                      lastName: String,
                      address: String,
                      remaining: Int,
                      currentScore: Int,
                      testScore: Int,
                      totalScore: Int)

case class UserAnswer(id: Option[Int],
                      userId: Int,
                      userName: String,
                      questionId: Int,
                      isRight: Int,
                      remaining: Int,
                      tAns: String)

class UserAnswersTable(tag: Tag) extends Table[UserAnswer](tag, "USER_ANSWER") {

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

  def userId = column[Int]("USER_ID", O.NotNull)

  def userName = column[String]("USER_NAME", O.NotNull)

  def questionId = column[Int]("QUESTION_ID", O.NotNull)

  def isRight = column[Int]("IS_RIGHT", O.NotNull)

  def remaining = column[Int]("REMAINING", O.NotNull)

  def tAns = column[String]("T_ANS", O.NotNull)

  def * = (id.?, userId, userName, questionId, isRight, remaining, tAns) <> (UserAnswer.tupled, UserAnswer.unapply _)

}

case class Admin(id: Option[Int],
                      firstName: String,
                      lastName: String,
                      login: String,
                      password: String)

class AdminsTable(tag: Tag) extends Table[Admin](tag, "ADMIN") {

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

  def firstName = column[String]("FIRST_NAME", O.NotNull)

  def lastName = column[String]("LAST_NAME", O.NotNull)

  def login = column[String]("LOGIN", O.NotNull)

  def password = column[String]("PASSWORD", O.NotNull)

  def * = (id.?, firstName, lastName, login, password) <> (Admin.tupled, Admin.unapply _)

}

case class Question(id: Option[Int],
                    question: String,
                    aAns: String,
                    bAns: String,
                    cAns: String,
                    dAns: String,
                    rAns: String)

class QuestionsTable(tag: Tag) extends Table[Question](tag, "Questions") {

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

  def question = column[String]("question", O.Default(""))

  def aAns = column[String]("aAns", O.Default(""))

  def bAns = column[String]("bAns", O.Default(""))

  def cAns = column[String]("cAns", O.Default(""))

  def dAns = column[String]("dAns", O.Default(""))

  def rAns = column[String]("rAns", O.Default(""))

  def * = (id.?, question, aAns, bAns, cAns, dAns, rAns) <>(Question.tupled,Question.unapply _)

}

object JsonFormats {

  import play.api.libs.json.Json

  implicit val userRoleFormat = EnumUtils.enumFormat(UserRoleEnum)
  implicit val userStateFormat = EnumUtils.enumFormat(UserStateEnum)
  implicit val credentialFormat = Json.format[Credential]

  implicit val permUserWrites = Json.writes[PermUser]
  implicit val permTestWrites = Json.writes[PermTest]

  implicit val permissionWrites = Json.writes[Permission]

  implicit val userFormat = Json.format[Account]
  implicit val resultFormat = Json.format[ResultUser]

  implicit val questionFormat = Json.format[Question]

  implicit val userAnswerFormat = Json.format[UserAnswer]
}
