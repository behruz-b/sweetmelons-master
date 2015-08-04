package models

import play.api.db.slick.Config.driver.simple._

case class QuestionData(id: Option[Int],
                         question: String,
                         aVariant: String,
                         bVariant: String,
                         cVariant: String,
                         dVariant: String)
class QuestionDataTable(tag: Tag) extends Table[QuestionData](tag, "Questions") {

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

  def question = column[String]("question", O.Default(""))

  def aVariant = column[String]("aVariant", O.Default(""))

  def bVariant = column[String]("bVariant", O.Default(""))

  def cVariant = column[String]("cVariant", O.Default(""))

  def dVariant = column[String]("dVariant", O.Default(""))

  def * = (id.?, question, aVariant, bVariant, cVariant, dVariant) <>(QuestionData.tupled,QuestionData.unapply _)

}

