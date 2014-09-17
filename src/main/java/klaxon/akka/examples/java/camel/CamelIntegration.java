package klaxon.akka.examples.java.camel;

import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.camel.Camel;
import akka.camel.CamelExtension;
import klaxon.akka.examples.java.future.FibonacciActor;
import klaxon.akka.examples.scala.camel.processor.BodyLogProcessor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;

/**
 * <p>date 9/17/14 </p>
 *
 * @author klaxon
 */
public class CamelIntegration {

    public static void main(String[] args) throws Exception {
        ActorSystem actorSystem = ActorSystem.create("camel-system");
        actorSystem.actorOf(Props.create(FibonacciActor.class), "fibonacciActor");

        Camel camel = CamelExtension.get(actorSystem);
        camel.context().addRoutes(new RouteBuilder() {
            @Override
            public void configure() throws Exception {
                from("direct:start")
                        .to("akka://camel-system/user/fibonacciActor")
                        .process(new BodyLogProcessor("body: "));
            }
        });

        ProducerTemplate producer = camel.context().createProducerTemplate();
        producer.sendBody("direct:start", 12);

        actorSystem.shutdown();
    }


}
