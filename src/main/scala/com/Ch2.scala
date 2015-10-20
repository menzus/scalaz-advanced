package com

import scalaz.@@
import scalaz.Monoid
import scalaz.Tags.Multiplication
import scalaz.std.anyVal.intInstance
import scalaz.std.anyVal.intMultiplicationNewType
import scalaz.std.option._
import scalaz.std.list._
import scalaz.syntax.foldable._

object Ch2 extends App {
  object BooleanAndMonoid extends Monoid[Boolean] {
    override def zero: Boolean = true
    override def append(f1: Boolean, f2: => Boolean): Boolean = f1 && f2
  }

  object SuperAdder {
    def add[A](items: List[A])(implicit m: Monoid[A]): A = {
      items.suml
    }

    case class Hangya(ero: Int, rang: Int)

    implicit val hangyaMonoid = Monoid.instance[Hangya]((a, b) => Hangya(a.ero + b.ero, a.rang + b.rang), Hangya(0, 0))
  }

  println(SuperAdder.add(List(1, 2, 3, 4, 5)))

  import SuperAdder._

  println(SuperAdder.add(List(Hangya(1,2), Hangya(1,2))))
  println(SuperAdder.add(List(some(Hangya(1,2)), none, none, none, some(Hangya(1,2)))))

  val multiplication = Monoid.apply[Int @@ Multiplication]
  println(SuperAdder.add(List(Multiplication(1), Multiplication(2), Multiplication(3), Multiplication(4), Multiplication(5)))(multiplication))
}
