package code.rest

import net.liftweb.util.Helpers._
import net.liftweb.http.rest.RestHelper
import net.liftweb.http.{LiftRules, StreamingResponse, LiftResponse, RedirectResponse}
import net.liftweb.common.{Box, Full}
import java.io.{FileOutputStream, FileInputStream, File}

object DownloadService extends RestHelper {

  var knownFiles : Map[String,File] = Map.empty

  serve {
    case "download" :: Known(fileId) :: Nil Get req =>
      if (permitted) fileResponse(fileId)
      else Full(RedirectResponse("/not-permitted"))
  }


  object Known {
    def unapply(fileId: String): Option[String] = knownFiles.keySet.find(_ == fileId)
  }

  private def permitted = scala.math.random < 0.5d

  private def fileResponse(fileId: String): Box[LiftResponse] = for {
    file <- knownFiles.get(fileId)
    input <- tryo(new FileInputStream(file))
  } yield StreamingResponse(input,
      () => input.close,
      file.length,
      headers=Nil,
      cookies=Nil,
      200)

  def init() {
    LiftRules.dispatch.append(DownloadService)

    // Create an example file to stream:
    val example = File.createTempFile("cookbook", "txt")
    example.deleteOnExit()
    val out = new FileOutputStream(example)
    out.write("Important content here".getBytes("UTF-8"))
    out.close()

    knownFiles = Map("important" -> example)
  }

}
