package controllers

import models.UserRoleEnum.UserRole
import models._
import play.api.libs.json.JsValue
import play.api.mvc._
import play.api.mvc.Results._
import jp.t2v.lab.play2.auth._
import scala.concurrent.{ExecutionContext, Future}
import reflect.{ClassTag, classTag}
import scala.slick.lifted.TableQuery
import play.api.db.slick.Config.driver.simple._
import play.api.db.slick.DB
import jp.t2v.lab.play2.stackc.{RequestWithAttributes, RequestAttributeKey, StackableController}

trait TestingAuth extends AuthElement with TestingAuthConfig {
  self: Controller with StackableController =>

  def AuthAction[A](p: BodyParser[A], params: (RequestAttributeKey[_], Any)*)(f: RequestWithAttributes[A] => Action[A]): Action[A] = {
    AsyncStack(p, params:_*) { implicit rs =>
      f(rs).apply(rs)
    }
  }

  def AuthAction(params: (RequestAttributeKey[_], Any)*)(f: RequestWithAttributes[AnyContent] => Action[AnyContent]): Action[AnyContent] = {
    AsyncStack(params:_*) { implicit rs =>
      f(rs).apply(rs)
    }
  }

  def AuthJsAction(params: (RequestAttributeKey[_], Any)*
                    )(f: RequestWithAttributes[JsValue] => Action[JsValue]): Action[JsValue] = {
    AsyncStack(parse.json, params:_*) { implicit rs =>
      f(rs).apply(rs)
    }
  }

}

trait TestingAuthConfig extends AuthConfig {

  import play.api.Play.current

  private val users = TableQuery[UsersTable]
  private val admins = TableQuery[AdminsTable]

  def hasRole(requiredRole: UserRole): Authority = { user =>
    Future.successful {
      hasRole(requiredRole, user)
    }
  }

  private def hasRole(requiredRole: UserRole, user: User) = {
    user.role match {
      case UserRoleEnum.ADMIN => true
      case UserRoleEnum.USER if requiredRole == UserRoleEnum.USER => true
      case _ => false
    }
  }

  private def isAdmin(user: User) = {
    user.role match {
      case UserRoleEnum.ADMIN => true
      case _ => false
    }
  }

  /**
   * A type that is used to identify a user.
   * `String`, `Int`, `Long` and so on.
   */
  type Id = Int

  /**
   * A type that represents a user in your application.
   * `User`, `Account` and so on.
   */
  type User = Account

  /**
   * A type that is defined by every action for authorization.
   * This sample uses the following trait:
   *
   * sealed trait Permission
   * case object Administrator extends Permission
   * case object NormalUser extends Permission
   */
  type Authority = User => Future[Boolean]

  /**
   * A `ClassTag` is used to retrieve an id from the Cache API.
   * Use something like this:
   */
  val idTag: ClassTag[Id] = classTag[Id]

  /**
   * The session timeout in seconds
   */
  val sessionTimeoutInSeconds: Int = 3600

  /**
   * A function that returns a `User` object from an `Id`.
   * You can alter the procedure to suit your application.
   */
  def resolveUser(id: Id)(implicit ctx: ExecutionContext): Future[Option[User]] =
    play.api.db.slick.DB.withSession { implicit session => Future.successful(
      users.filter(_.id === id).list.headOption
    )}

  /**
   * Where to redirect the user after a successful login.
   */
  def loginSucceeded(request: RequestHeader)(implicit ctx: ExecutionContext): Future[Result] =
    Future successful Ok("Successfully logged in")

  /**
   * Where to redirect the user after logging out
   */
  def logoutSucceeded(request: RequestHeader)(implicit ctx: ExecutionContext): Future[Result] =
    Future successful Redirect(routes.Application.index)

  /**
   * If the user is not logged in and tries to access a protected resource then redirct them as follows:
   */
  def authenticationFailed(request: RequestHeader)(implicit ctx: ExecutionContext): Future[Result] =
    Future successful BadRequest("Retry: Incorrect login or password")

  /**
   * If authorization failed (usually incorrect password) redirect the user as follows:
   */
  def authorizationFailed(request: RequestHeader)(implicit ctx: ExecutionContext): Future[Result] =
    Future successful Forbidden("no permission")

  /**
   * A function that determines what `Authority` a user has.
   * You should alter this procedure to suit your application.
   */
  def authorize(user: User, authority: Authority)(implicit ctx: ExecutionContext): Future[Boolean] =
    authority(user)

  /**
   * Whether use the secure option or not use it in the cookie.
   * However default is false, I strongly recommend using true in a production.
   */
  override lazy val cookieSecureOption: Boolean = play.api.Play.isProd(play.api.Play.current)

}
