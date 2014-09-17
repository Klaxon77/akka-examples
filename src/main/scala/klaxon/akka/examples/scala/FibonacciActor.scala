package klaxon.akka.examples.scala

import java.util.concurrent.TimeUnit

import akka.actor.Actor

/**
 * <p>date 9/17/14 </p>
 * @author klaxon
 */
class FibonacciActor extends Actor {

  val fibs: Stream[Int] = 0 #:: fibs.scanLeft(1)(_ + _)

  override def receive: Receive = {
    case number: Int =>
      TimeUnit.SECONDS.sleep(1)
      sender() ! fibs(number)
  }

}