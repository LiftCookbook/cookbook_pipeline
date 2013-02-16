package bootstrap.liftweb

import net.liftweb.http.LiftRules
import net.liftweb.common.{Loggable, Full}

object RequestLogging extends Loggable  {

  def init() {

    LiftRules.early.append {
      case r => logger.info("Early: "+r)
    }

    LiftRules.onBeginServicing.append {
      case r => logger.info("On Begin: "+r)
    }

    LiftRules.earlyInStateless.append {
      case Full(r) => logger.info("Early in stateless: "+r)
      case _ =>
    }

    LiftRules.earlyInStateful.append {
      case Full(r) if LiftRules.getLiftSession(r).running_? => logger.info("Early in stateful: "+r)
      case _ =>
    }

  }

}
