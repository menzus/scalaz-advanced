package com

object Ch1_1 extends App {

  trait Printable[A] {
    def format(a: A): String
  }

  object Printable {
    def apply[A](f: A => String) = new Printable[A] {
      override def format(a: A) = f(a)
    }
  }

  object PrintableDefaults {
    implicit val intPrintable = Printable.apply[Int](i => s"a int: $i")
    implicit val stringPrintable = Printable.apply[String](s => s"az string: $s")
  }

  object Print {
    def format[A](a: A)(implicit printable: Printable[A]): String = printable format(a)
    def print[A](a: A)(implicit printable: Printable[A]): Unit = println(s"console: ${format(a)}")
  }

  val int = 12345
  val string = "blah blah"

  import Print._
  import PrintableDefaults._

  println(format(int))
  println(format(string))

  print(int)
  print(string)

  println("-------------------------------------------------------")

  case class Lo(nev: String, labSzam: Int)
  object Lo {
    implicit val loPrintable = Printable.apply((l: Lo) => s"${l.nev} nagyon kedves, es ${l.labSzam} laba van")
  }

  print(Lo("karoly", 3))

  println("-------------------------------------------------------")

  object PrintSyntax {
    implicit class PrintOps[A](a: A) {
      def format(implicit p: Printable[A]) = p.format(a)
      def print(implicit p: Printable[A]) = println(p.format(a))
    }
  }

  import PrintSyntax._

  Lo("karoly-ketto", 3).print
}
