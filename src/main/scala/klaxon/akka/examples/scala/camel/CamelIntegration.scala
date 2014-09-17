package klaxon.akka.examples.scala.camel

import akka.actor.{ActorSystem, Props}
import klaxon.akka.examples.scala.FibonacciActor
import klaxon.akka.examples.scala.camel.processor.BodyLogProcessor
import org.apache.camel.builder.RouteBuilder

/**
 * <p>date 9/17/14 </p>
 * @author klaxon
 */
object CamelIntegration {

  def main(args: Array[String]) {
    val actorSystem = ActorSystem("camel-system")
    val fibonacciActor = actorSystem.actorOf(Props[FibonacciActor], "fibonacciActor")

    val camel = CamelExtension(actorSystem)
    camel.context.addRoutes(new RouteBuilder() {
      override def configure(): Unit = {
        from("direct:start")
          .to(fibonacciActor)
          .process(new BodyLogProcessor)
      }
    })

    val producer = camel.context.createProducerTemplate()
    producer.sendBody("direct:start", 12)

    actorSystem.shutdown()
  }

}
