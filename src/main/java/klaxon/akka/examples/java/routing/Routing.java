package klaxon.akka.examples.java.routing;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.routing.RoundRobinPool;

import java.util.Arrays;

/**
 * <p>date 9/16/14 </p>
 * @author klaxon
 */
public class Routing {

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("routing");
        ActorRef sumActor = actorSystem.actorOf(Props.create(SumActor.class).withRouter(new RoundRobinPool(3)), "sumActor");

        sumActor.tell(new NumericList(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)), ActorRef.noSender());
        sumActor.tell(new NumericList(Arrays.asList(1, 1, 2, 3, 5, 8, 13, 21, 34, 55)), ActorRef.noSender());
        sumActor.tell(new NumericList(Arrays.asList(2, 4, 6)), ActorRef.noSender());

        actorSystem.shutdown();
    }

}
