package klaxon.akka.examples.java.helloworld;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * <p>date 9/16/14 </p>
 * @author klaxon
 */
public class HelloWorld {

    public static void main(String[] args) {
        ActorSystem actorSystem = ActorSystem.create("helloWorld");
        ActorRef actorRef = actorSystem.actorOf(Props.create(GreetingActor.class));

        actorRef.tell(new Greeting("World"), ActorRef.noSender());
        actorRef.tell(new Greeting("Sun"), ActorRef.noSender());
        actorRef.tell(new Greeting("Moon"), ActorRef.noSender());

        actorSystem.shutdown();
    }

}


