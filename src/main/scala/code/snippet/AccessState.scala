package code.snippet

import net.liftweb.util.Helpers._
import net.liftweb.http.SHtml
import net.liftweb.http.js.JsCmds

class AccessState {

  def render = "*" #> SHtml.ajaxText("boom", s => JsCmds.Noop)

}
