package klaxon.akka.examples.scala

import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import akka.routing.RoundRobinPool

/**
 * <p>date 9/16/14 </p>
 * @author klaxon
 */
object Routing {

  def main(args: Array[String]) {
    val actorSystem = ActorSystem("routing")
    val sumActor = actorSystem.actorOf(Props[SlowSumActor].withRouter(new RoundRobinPool(3)), "sumActor")

    sumActor ! NumericList(List(1, 2, 3, 4, 5, 6, 7, 8))
    sumActor ! NumericList(1 to 100 toList)
    sumActor ! NumericList(List(2, 4, 6))

    actorSystem.shutdown()
  }

}

case class NumericList(numbers: List[Int])

class SlowSumActor extends Actor with ActorLogging {
  override def receive: Receive = {
    case NumericList(numbers) => {
      log.info("Calculating: " + numbers)
      TimeUnit.SECONDS.sleep(3)

      log.info("Sum: " + numbers.sum)
    }
  }
}