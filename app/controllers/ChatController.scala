package controllers

import javax.inject.{Inject, Singleton}
import play.api.mvc.{AbstractController, ControllerComponents}

@Singleton
class ChatController @Inject()(cc : ControllerComponents) extends AbstractController(cc) {
  def index = Action(Ok(views.html.chat.render()))
}
