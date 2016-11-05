package controllers

import javax.inject._
import play.api._
import play.api.mvc._

/**
  * Created by bin.shen on 05/11/2016.
  */
class HomeController extends Controller {

  def index = Action {
    Ok("123asd+++")
  }

  def test(name: String) = Action {
    //Ok("Test " + name)
    Ok(views.html.test(name))
  }
}
