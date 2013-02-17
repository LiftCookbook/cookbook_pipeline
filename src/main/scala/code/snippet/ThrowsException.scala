package code.snippet

import net.liftweb.util.Helpers._
import net.liftweb.http.SHtml

class ThrowsException {

  private def fail = throw new Error("not implemented")

  def render = fail

  // A button that causes an exception which we catch
  // in the rules set up in ExceptionHandler
  def ajax = "*" #> SHtml.ajaxButton("Press Me", () => fail)

}
