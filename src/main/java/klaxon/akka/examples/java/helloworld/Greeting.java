package klaxon.akka.examples.java.helloworld;

import java.io.Serializable;

/**
 * <p>date 9/16/14 </p>
 * @author klaxon
 */
public class Greeting implements Serializable {
    public final String who;
    public Greeting(String who) {
        this.who = who;
    }
}
