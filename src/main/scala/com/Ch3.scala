package com

import scalaz.Functor
import scalaz.std.function._
import scalaz.std.option._
import scalaz.syntax.functor._

object Ch3 extends App {
  val func1 = (x: Int) => x.toDouble
  val func2 = (y: Double) => y * 2
  val func3 = func1 map func2

  println(func3(3))

  val lifted = Functor[Option].lift((x: Int) => x + 1)

  println(lifted(some(1)))

  object crap {
    sealed trait Result[+A]
    final case class Success[A](value: A) extends Result[A]
    final case class Warning[A](value: A, message: String) extends Result[A]
    final case class Failure(message: String) extends Result[Nothing]

    object crapFunctor extends Functor[Result] {
      override def map[A, B](fa: Result[A])(f: A => B): Result[B] = fa match {
        case Success(a) => Success(f(a))
        case Warning(a, w) => Warning(f(a), w)
        case Failure(m) => Failure(m)
      }
    }
  }
}
