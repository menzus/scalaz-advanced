package com

import scalaz.Applicative

object Ch6 extends App {
  sealed trait Res[+A]
  case class Suc[A](a: A) extends Res[A]
  case class Fa[A](s: List[String]) extends Res[A]

  implicit object ResApplicative extends Applicative[Res] {
    override def point[A](a: => A): Res[A] = Suc(a)

    override def ap[A, B](fa: => Res[A])(f: => Res[A => B]): Res[B] = {
      println(fa)
      println(f)
      (fa, f) match {
        case (Suc(a), Suc(f)) => Suc(f(a))
      }
    }
  }

  println(ResApplicative.ap2(Suc(1), Suc(2))(Suc((x: Int, y: Int) => x + y)))

}
