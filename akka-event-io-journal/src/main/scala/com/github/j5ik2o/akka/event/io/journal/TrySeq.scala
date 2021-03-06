package com.github.j5ik2o.akka.event.io.journal

import scala.collection.immutable.{ Seq, Vector }
import scala.util.{ Failure, Success, Try }

object TrySeq {

  def sequence[A](seq: Seq[Try[A]]): Try[Seq[A]] = {
    def recurse(remaining: Seq[Try[A]], processed: Seq[A]): Try[Seq[A]] = remaining match {
      case Seq()                 => Success(processed)
      case Success(head) +: tail => recurse(remaining = tail, processed :+ head)
      case Failure(t) +: _       => Failure(t)
    }
    recurse(seq, Vector.empty)
  }
}
