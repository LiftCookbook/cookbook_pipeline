package bootstrap.liftweb

import net.liftweb.common.{Full, Box, Loggable}
import net.liftweb.http.{LiftRules, LiftResponse, Req, LiftSession}

object SessionLogging extends Loggable  {

  def init() {

    // LiftRules.sessionInactivityTimeout.default.set(Full(1000L * 30))

    LiftSession.onSetupSession ::=
      ( (s:LiftSession) => logger.info("onSetupSession") )

    LiftSession.afterSessionCreate ::=
      ( (s:LiftSession, r:Req) => logger.info("afterSessionCreate") )

    LiftSession.onBeginServicing ::=
      ( (s:LiftSession, r:Req) => logger.info("onBeginServicing") )

    LiftSession.onEndServicing ::=
      ( (s:LiftSession, r:Req, o:Box[LiftResponse]) => logger.info("onEndServicing") )

    LiftSession.onAboutToShutdownSession ::=
      ( (s:LiftSession) => logger.info("onAboutToShutdownSession") )

    LiftSession.onShutdownSession ::=
      ( (s:LiftSession) => logger.info("onShutdownSession") )

    LiftSession.onSessionActivate ::=
      ( (s:LiftSession) => logger.info("onSessionActivate"))

    LiftSession.onSessionPassivate ::=
      ( (s:LiftSession) => logger.info("onSessionPassivate"))



  }
}
