package klaxon.akka.examples.java.routing;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>date 9/16/14 </p>
 * @author klaxon
 */
public class SumActor extends UntypedActor {

    private LoggingAdapter log = Logging.getLogger(getContext().system(), this);

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof NumericList) {
            List<Integer> numbers = ((NumericList) message).numbers;

            log.info("Calculating: " + numbers);
            TimeUnit.SECONDS.sleep(3);

            log.info("Sum: " + sum(numbers));
        }
    }

    private static long sum(List<Integer> numbers) {
        int sum = 0;
        for (Integer number : numbers) {
            sum += number;
        }
        return sum;
    }

}
