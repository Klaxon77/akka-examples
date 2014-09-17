package klaxon.akka.examples.java.future;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

/**
 * <p>date 9/17/14 </p>
 *
 * @author klaxon
 */
public class FutureAkka {

    public static void main(String[] args) throws Exception {
        ActorSystem actorSystem = ActorSystem.create("future");
        ActorRef fibonacciActor = actorSystem.actorOf(Props.create(FibonacciActor.class), "fibonacciActor");

        Future<Object> futureFibonacci = Patterns.ask(fibonacciActor, 12, TimeUnit.SECONDS.toMillis(5));

        System.out.println("Working on some time-consuming task. Tick tick.");
        System.out.println("Calculated fibonacci for 12: " + Await.result(futureFibonacci, Duration.create(5, TimeUnit.SECONDS)));

        actorSystem.shutdown();

    }

}
