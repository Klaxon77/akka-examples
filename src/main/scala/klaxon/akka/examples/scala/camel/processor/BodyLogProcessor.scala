package klaxon.akka.examples.scala.camel.processor

import org.apache.camel.{Exchange, Processor}

/**
 * <p>date 9/17/14 </p>
 * @author klaxon
 */
class BodyLogProcessor(prefix: String) extends Processor {

  override def process(ex: Exchange): Unit = {
    println(prefix + ex.getIn.getBody)
  }
}
