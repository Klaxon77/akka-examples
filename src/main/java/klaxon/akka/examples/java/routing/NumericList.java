package klaxon.akka.examples.java.routing;

import java.util.Collections;
import java.util.List;

/**
 * <p>date 9/16/14 </p>
 * @author klaxon
 */
public class NumericList {

    public final List<Integer> numbers;

    public NumericList(List<Integer> numbers) {
        this.numbers = Collections.unmodifiableList(numbers);
    }

}
