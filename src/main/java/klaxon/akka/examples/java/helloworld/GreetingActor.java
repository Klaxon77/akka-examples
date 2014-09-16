package klaxon.akka.examples.java.helloworld;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * <p>date 9/16/14 </p>
 *
 * @author klaxon
 */
public class GreetingActor extends UntypedActor {

    LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    public void onReceive(Object message) throws Exception {
        if (message instanceof Greeting) {
            String who = ((Greeting) message).who;
            log.info("Hello " + who);
        }

    }

}
