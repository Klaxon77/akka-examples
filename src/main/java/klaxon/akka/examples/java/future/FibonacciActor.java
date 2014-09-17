package klaxon.akka.examples.java.future;

import akka.actor.UntypedActor;

/**
 * <p>date 9/17/14 </p>
 *
 * @author klaxon
 */
public class FibonacciActor extends UntypedActor {

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Integer) {
            int calculated = calculateFibonacci((Integer) message);
            sender().tell(calculated, self());
        }
    }

    private int calculateFibonacci(int number) {
        if (number <= 0) return 0;
        if (number == 1) return 1;
        return calculateFibonacci(number - 1) + calculateFibonacci(number - 2);
    }

}
