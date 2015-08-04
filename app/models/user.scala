package models

import play.api.db.slick.Config.driver.simple._
import scala.slick.lifted.TableQuery

/**
 * Created by bunyod on 08/03/15.
 */

object UserStateEnum extends BaseEnum {

  override val enumPrefix = "userState"

  type UserState = Value
  val ACTIVE = Value(1)
  val INACTIVE = Value(0)
}

object UserRoleEnum extends BaseEnum {

  override val enumPrefix = "userRole"

  type UserRole = Value
  val ADMIN = Value(1)
  val USER = Value(2)
}

case class Credential(login: String,
                      password: String)

case class Account(id: Option[Int],
                firstName: String,
                lastName: String,
                address: String,
                email: String,
                password: String,
                role: Option[UserRoleEnum.UserRole],
                score: Option[Double])

class UsersTable(tag: Tag) extends Table[Account](tag, "ACCOUNT") {

  def id = column[Int]("ID", O.PrimaryKey, O.AutoInc)

  def firstName = column[String]("FIRST_NAME", O.NotNull)

  def lastName = column[String]("LAST_NAME", O.NotNull)

  def address = column[String]("ADDRESS", O.Default(""))

  def email = column[String]("EMAIL", O.Default(""))

  def password = column[String]("PASSWORD", O.NotNull)

  def role = column[UserRoleEnum.UserRole]("ROLE", O.Default(UserRoleEnum.USER))

  def score = column[Double]("SCORE", O.Default(0.0))

  def * = (id.?, firstName, lastName, address, email, password, role.?, score.?) <>(Account.tupled, Account.unapply _)

  val sorting = Map(
    "id" -> id, "firstName" -> firstName, "lastName" -> lastName,
    "email" -> email, "password" -> password, "role" -> role, "score" -> score)
}
