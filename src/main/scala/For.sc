// For Comprehensions
// https://docs.scala-lang.org/tour/for-comprehensions.html

// for (enumerators) yield e

import scala.util.Try
import java.time

val list = 1 to 10

for (i <- list) yield 2 * i

list.map { x => 2 * x
}

for (i <- list if i % 2 == 0) yield i

// TODO: what's the difference between `for (...) yield` and `for {...} yield`

val result = for {
  number <- Option(2)
  string <- Try(number.toLong).toOption
} yield string