package com.github.j5ik2o.akka.event.io.journal

import akka.persistence.{ AtomicWrite, PersistentRepr }
import akka.persistence.journal.AsyncWriteJournal
import com.typesafe.config.Config

import scala.concurrent.Future
import scala.util.Try

class DynamoDBJournal(config: Config) extends AsyncWriteJournal {

  override def asyncWriteMessages(messages: Seq[AtomicWrite]): Future[Seq[Try[Unit]]] = ???

  override def asyncDeleteMessagesTo(persistenceId: String, toSequenceNr: Long): Future[Unit] = ???

  override def asyncReplayMessages(persistenceId: String, fromSequenceNr: Long, toSequenceNr: Long, max: Long)(
      recoveryCallback: PersistentRepr => Unit
  ): Future[Unit] = ???

  override def asyncReadHighestSequenceNr(persistenceId: String, fromSequenceNr: Long): Future[Long] = ???
}

object DynamoDBJournal {}
