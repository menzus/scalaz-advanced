package com

import com.Ch1_1.Lo

import scalaz.{Equal, Show}
import scalaz.std.anyVal._
import scalaz.std.option._
import scalaz.std.list._
import scalaz.syntax.show._
import scalaz.syntax.equal._

object Ch1_2 extends App {
  val intShow = Show.apply[Int]

  println(intShow.shows(123))

  println(123 shows)

  implicit val loShow = Show.shows((l: Lo) => s"lo ${l.nev} nagyon kedves es baratsagos")

  val janosLo = Lo("janos", 3)
  println(janosLo show)

  println(123 === 124)
  println(123 =/= 124)

  implicit val loEqual = Equal.equal[Lo](_ == _)

  println(some(janosLo) === none[Lo])
  println(List(janosLo) === nil[Lo])
}
