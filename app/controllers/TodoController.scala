package controllers

import com.google.inject.Inject
import models.TodoService
import play.api.mvc._

/**
  * Created by bin.shen on 05/11/2016.
  */
class TodoController @Inject() (todoService: TodoService) extends Controller {

  def index = Action {
    Ok(views.html.index(todoService.selectTodoList(), "TODO List"))
  }
}
