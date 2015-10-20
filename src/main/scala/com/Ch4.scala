package com

import scalaz.{\/-, Monad}
import scalaz.syntax.either._
import scalaz.std.list._
import scalaz.std.option._

object Ch4 extends App {
  val l = List(some(1), some(2), some(3), none)
  println(l)
  println(Monad[Option].sequence(l))

  val r = "shit".right[Int]
  println(r.flatMap(x => \/-(x.toUpperCase)))
}
