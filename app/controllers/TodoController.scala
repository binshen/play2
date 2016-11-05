package controllers

import models._
import play.api.data.Forms._
import play.api.data._
import play.api.mvc._
import com.google.inject.Inject

/**
  * Created by bin.shen on 05/11/2016.
  */
class TodoController @Inject() (todoService: TodoService) extends Controller {

//  val todoForm = Form(
//    mapping(
//      "id" -> ignored(None:Option[Long]),
//      "title" -> nonEmptyText,
//      "finished" -> longNumber,
//      "post_date" -> optional(date("yyyy-MM-dd"))
//    )(Todo.apply)(Todo.unapply)
//  )

  def index = Action {
    Ok(views.html.index(todoService.selectTodoList(), "TODO List"))
  }

  def add = Action {
    Redirect("/todo")
  }

  def edit(id: Long) = Action {
    Ok(views.html.edit(todoService.selectTodo(id), "TODO List"))
  }

  def save(id: Long) = Action {
    //todoService.insertTodo(new Todo(todoForm))
    Redirect("/todo")
  }

  def delete(id: Long) = Action {
    Redirect("/todo")
  }

  def finish(id: Long, key: Int) = Action {
    Redirect("/todo")
  }
}
