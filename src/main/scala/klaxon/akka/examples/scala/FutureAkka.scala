package klaxon.akka.examples.scala

import java.util.concurrent.TimeUnit

import akka.actor.{ActorLogging, Actor, ActorSystem, Props}
import akka.pattern.ask
import akka.util.Timeout

import scala.concurrent.Await
import scala.concurrent.duration._


/**
 * <p>date 9/17/14 </p>
 * @author klaxon
 */
object Future {

  implicit val timeout = Timeout(5 seconds)

  def main(args: Array[String]) {
    val actorSystem = ActorSystem("future")
    val fibonacciActor = actorSystem.actorOf(Props[FibonacciActor], "fibonacciActor")

    val futureFibonacci = fibonacciActor ? 12

    println("Working on some time-consuming task. Tick tick.")
    println(s"Calculated fibonacci for 12: " + Await.result(futureFibonacci, 5 seconds))

    actorSystem.shutdown()
  }

}

class FibonacciActor extends Actor {

  val fibs: Stream[Int] = 0 #:: fibs.scanLeft(1)(_ + _)

  override def receive: Receive = {
    case number: Int =>
      TimeUnit.SECONDS.sleep(1)
      sender() ! fibs(number)
  }

}
