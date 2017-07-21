package com.redbubble.util.spec

import com.twitter.util.Duration.fromMilliseconds
import com.twitter.util.{Await, Future}
import org.specs2.execute.Result
import org.specs2.mutable.Specification

trait FutureResultChecks { self: Specification =>
  def checkResult[A](result: Future[A]): Result = {
    try {
      // Note. The `get` here forces the evaluation of the future, which will throw an exception if there is one (and thus fail the spec).
      Await.result(result.liftToTry, fromMilliseconds(10)).get()
      success("ok")
    } catch {
      case e: Throwable => failure(e.getMessage)
    }
  }
}
