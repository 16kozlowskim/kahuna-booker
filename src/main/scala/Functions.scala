import cats.Monad
import cats.effect.IO.sleep

import scala.annotation.tailrec
import scala.util.{Failure, Success, Try}
import java.time.Duration

object Functions {
  def rowMajorToMinor[A](rowMajorList: List[List[A]]): List[List[A]] = {
    rowMajorList
      .foldRight(rowMajorList.head.map(_ => List.empty[A]))(
        (elem, acc) => {
          @tailrec
          def go(l1: List[A], l2: List[List[A]], acc: List[List[A]]): List[List[A]] = l1 match {
            case Nil => acc.reverse
            case hd :: tail => go(tail, l2.tail, (hd :: l2.head) :: acc)
          }
          go(elem, acc, List.empty[List[A]])
        })
  }

  def slowly[A](a: => A): A = {
    val value = a
    sleep

  }
}





