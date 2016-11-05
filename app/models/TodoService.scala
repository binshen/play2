package models

import java.util.Date
import javax.inject.Inject

import anorm.SqlParser._
import anorm._

import play.api.db.DBApi

/**
  * Created by bin.shen on 05/11/2016.
  */

case class Todo(id: Option[Long],title: String, finished: Long, post_date: Option[Date])

@javax.inject.Singleton
class TodoService @Inject() (dbapi: DBApi) {

  private val db = dbapi.database("default")

  val simple = {
    get[Option[Long]]("todo.id") ~
      get[String]("todo.title") ~
      get[Int]("todo.finished") ~
      get[Option[Date]]("todo.post_date")  map {
      case id~title~finished~post_date =>
        Todo(id, title, finished, post_date)
    }
  }

  def selectTodo(id: Long): Option[Todo] = {
    db.withConnection { implicit connection =>
      SQL("select * from todo where id = {id}").on('id -> id).as(simple.singleOpt)
    }
  }

  def selectTodoList(): List[Todo] = {
    db.withConnection(implicit connection =>
      SQL("select * from todo").as(simple *)
    )
  }

  def insertTodo(todo: Todo) = {
    db.withConnection { implicit connection =>
      SQL(
        """
          insert into todo (title, finished, post_date) values (
            {title}, {finished}, {post_date}
          )
        """
      ).on(
        'title -> todo.title,
        'finished -> todo.finished,
        'post_date -> todo.post_date
      ).executeUpdate()
    }
  }
}
