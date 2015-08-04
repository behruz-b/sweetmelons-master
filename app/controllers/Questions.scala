package controllers
import models._
import play.api.Logger
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick._
import play.api.mvc._
import scala.slick.lifted.TableQuery
class Questions extends Controller{
  val questionData = TableQuery[QuestionDataTable]

  def questionsList = DBAction { implicit rs =>
    Logger.info(s"SHOW_ALL = ${questionData.list}")
    Ok(views.html.questionList(questionData.list))
  }

  def showQuestionForm = DBAction { implicit rs =>
    Ok(views.html.addQuestion())
  }

  def addQuestions = DBAction { implicit request =>
    val formParams = request.body.asFormUrlEncoded
    val question = formParams.get("question")(0)
    val aVariant = formParams.get("aVariant")(0)
    val bVariant = formParams.get("bVariant")(0)
    val cVariant = formParams.get("cVariant")(0)
    val dVariant = formParams.get("dVariant")(0)

    val questionId = (questionData returning questionData.map(_.id)) += QuestionData(None, question, aVariant, bVariant, cVariant, dVariant)
    Redirect(routes.Questions.questionsList())
  }


//  def remove(id: Int) = DBAction { implicit request =>
//    personalDataS.filter(_.id === id).delete
//    Redirect(routes.Students.studentsList())
//  }
//  def updateStudents(id: Int) = DBAction { implicit request =>
//    val formParams = request.body.asFormUrlEncoded
//    val ismi = formParams.get("ismi")(0)
//    val familiyasi = formParams.get("familiyasi")(0)
//    val otasining_ismi = formParams.get("otasining_ismi")(0)
//    val tugulgan_sana = formParams.get("tugulgan_sana")(0)
//    val guruhi = formParams.get("guruhi")(0)
//    val elektron_pochtasi = formParams.get("elektron_pochtasi")(0)
//    val tel = formParams.get("tel")(0)
//
//    val student = PersonalDataS(Some(id), ismi, familiyasi, otasining_ismi, tugulgan_sana, guruhi, elektron_pochtasi, tel)
//    personalDataS.filter(_.id === id).update(student)
//
//    Redirect(routes.Students.studentsList())
//  }
//
//  def editFormStudentsList(studentId: Int) = DBAction { implicit request =>
//    val byId = personalDataS.findBy(_.id)
//    val student = byId(studentId).list.head
//
//    Ok(views.html.editStudent(student))
//  }

}
