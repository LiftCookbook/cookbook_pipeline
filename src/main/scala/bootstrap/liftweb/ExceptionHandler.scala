package bootstrap.liftweb

import net.liftweb.common.Loggable
import net.liftweb.http._
import net.liftweb.http.js.JsCmds.Alert
import net.liftweb.http.InternalServerErrorResponse

class ExceptionHandler extends Loggable {

  def init() {

    LiftRules.exceptionHandler.prepend {
      case (runMode, request, exception) =>
        logger.error("Failed at: "+request.uri)
        InternalServerErrorResponse()
    }

    LiftRules.exceptionHandler.prepend {
      case (mode, Req(ajax :: _, _, PostRequest), ex) =>
        logger.error("Error handing ajax")
        JavaScriptResponse(Alert("Boom!"))
    }

  }

}
