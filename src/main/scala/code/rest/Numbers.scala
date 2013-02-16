package code.rest

import net.liftweb.http.{InMemoryResponse, LiftRules, Req, OutputStreamResponse}
import net.liftweb.http.rest._
import net.liftweb.util.Helpers.AsInt

object Numbers extends RestHelper {

  def init() {
    LiftRules.dispatch.append(Numbers)
  }

  // Convert a number to a String and then to bytes we can send
  def num2bytes(x: Int) = (x + "\n") getBytes("utf-8")

  // Generate numbers using a Scala stream:
  def infinite = Stream.from(1).map(num2bytes)

  serve {
    case Req("numbers" :: Nil, _, _) =>
      OutputStreamResponse( out => infinite.foreach(out.write) )

    case Req( AsInt(n) :: Nil, _, _) =>
      InMemoryResponse(infinite.take(n).toArray.flatten, Nil, Nil, 200)
  }


}