package controllers

import models._
import play.api.mvc._
import com.google.inject.Inject

/**
  * Created by bin.shen on 05/11/2016.
  */
class TodoController @Inject() (todoService: TodoService) extends Controller {

  def index = Action {
    Ok(views.html.index(todoService.selectTodoList(), "TODO List"))
  }

  def add = Action { request =>
    val body = request.body.asFormUrlEncoded
    val todo = new Todo(null, body.get("title").mkString, 0, null);
    todoService.insertTodo(todo)
    Redirect("/")
  }

  def edit(id: Long) = Action {
    Ok(views.html.edit(todoService.selectTodo(id), "TODO List"))
  }

  def save(id: Long) = Action { request =>
    val todo = todoService.selectTodo(id).get
    val body = request.body.asFormUrlEncoded
    todoService.updateTodo(id, new Todo(null, body.get("title").mkString, todo.finished, todo.post_date))
    Redirect("/")
  }

  def delete(id: Long) = Action {
    todoService.deleteTodo(id)
    Redirect("/")
  }

  def finish(id: Long, finished: Int) = Action {
    val todo = todoService.selectTodo(id).get
    todoService.updateTodo(id, new Todo(null, todo.title, finished, todo.post_date))
    Redirect("/")
  }
}
