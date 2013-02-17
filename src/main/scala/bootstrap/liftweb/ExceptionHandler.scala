package bootstrap.liftweb

import net.liftweb.common.Loggable
import net.liftweb.http._
import net.liftweb.http.js.JsCmds.Alert
import net.liftweb.http.InternalServerErrorResponse

object ExceptionHandler extends Loggable {

  def init() {

    LiftRules.exceptionHandler.prepend {
      case (runMode, req, exception) =>
        logger.error("Failed at: "+req.uri)

        val content = S.render(<lift:embed what="500" />, req.request)
        XmlResponse(
          content.head,
          500,
          "text/html",
          req.cookies)

    }

    LiftRules.exceptionHandler.prepend {
      case (mode, Req(ajax :: _, _, PostRequest), ex) =>
        logger.error("Error handing ajax")
        JavaScriptResponse(Alert("Boom!"))
    }

  }

}
