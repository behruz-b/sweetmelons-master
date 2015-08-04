package models

import play.api.db.slick.Config.driver.simple._
import play.api.i18n.{Lang, Messages}
import play.api.libs.functional.syntax._
import play.api.libs.json._
import java.util.Date

/**
 * Created by bunyod on 1/19/15.
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

object JsonFormats {

  import play.api.libs.json.Json

  implicit val userRoleFormat = EnumUtils.enumFormat(UserRoleEnum)
  implicit val userStateFormat = EnumUtils.enumFormat(UserStateEnum)
  implicit val credentialFormat = Json.format[Credential]

  implicit val permUserWrites = Json.writes[PermUser]
  implicit val permTestWrites = Json.writes[PermTest]

  implicit val permissionWrites = Json.writes[Permission]

  implicit val userFormat = Json.format[Account]
}
