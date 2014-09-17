package klaxon.akka.examples.scala.camel

import java.util.concurrent.TimeUnit

import akka.actor.{Actor, ActorSystem, Props}
import akka.camel.{CamelExtension, CamelMessage, Consumer, Producer}
import org.apache.camel.builder.RouteBuilder
import akka.camel._

/**
 * <p>date 9/17/14 </p>
 * @author klaxon
 */
object CamelIntegration {

  def main(args: Array[String]) {
    val actorSystem = ActorSystem("camel-system")
    actorSystem.actorOf(Props[ReverseBodyActor], "reverser")
    actorSystem.actorOf(Props[UpperCaseActor], "upperCase")
    actorSystem.actorOf(Props[EndLogConsumer], "consumer")
    val spaceChopper = actorSystem.actorOf(Props[SpaceChopperActor], "spaceChopper")

    val camel = CamelExtension(actorSystem)
    camel.context.addRoutes(new RouteBuilder() {
      override def configure(): Unit = {
        from("direct:start")
          .to("akka://camel-system/user/reverser")
          .to("akka://camel-system/user/upperCase")
          .to(spaceChopper)
          .to("direct:end")
      }
    })

    actorSystem.actorOf(Props[StartProducer], "start") ! "As I pee, sir, I see Pisa"

    TimeUnit.SECONDS.sleep(2) //Need to wait while everything processes. Actors send is async!
    actorSystem.shutdown()
  }

}

class StartProducer extends Producer {
  override def endpointUri: String = "direct:start"
}

class ReverseBodyActor extends Actor {
  override def receive: Receive = {
    case msg: CamelMessage => sender() ! msg.mapBody { x: String => x.reverse}
  }
}

class UpperCaseActor extends Actor {
  override def receive: Receive = {
    case msg: CamelMessage => sender() ! msg.mapBody { x: String => x.toUpperCase}
  }
}

class SpaceChopperActor extends Actor {
  override def receive: Actor.Receive = {
    case msg: CamelMessage => sender() ! msg.mapBody { x: String => x.replaceAll("[ ,]", "")}
  }
}

class EndLogConsumer extends Consumer {
  override def endpointUri: String = "direct:end"

  override def receive: Actor.Receive = {
    case msg: CamelMessage => {
      sender() ! msg

      println("-" * 30)
      println(s"Body: ${msg.body}")
    }
  }
}