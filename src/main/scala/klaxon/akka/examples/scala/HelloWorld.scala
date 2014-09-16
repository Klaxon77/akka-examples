package klaxon.akka.examples.scala

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}

/**
 * <p>date 9/16/14 </p>
 * @author klaxon
 */
object HelloWorld {

  def main(args: Array[String]) {
    val actorSystem = ActorSystem("helloWorld")
    val greeter = actorSystem.actorOf(Props[GreetingActor], "greeter")

    greeter ! Greeting("World")
    greeter ! Greeting("Sun")
    greeter ! Greeting("Moon")

    actorSystem.shutdown()
  }

}

case class Greeting(who: String)

class GreetingActor extends Actor with ActorLogging {

  override def receive: Receive = {
    case Greeting(who) => log.info("Hello " + who)
  }
}
