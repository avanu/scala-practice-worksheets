// For Comprehensions
// https://docs.scala-lang.org/tour/for-comprehensions.html

// for (enumerators) yield e

import scala.util.Try

val list = 1 to 10

for (i <- list) yield 2 * i

list.map { x => 2 * x
}

for (i <- list if i % 2 == 0) yield i


val result = for {
  number <- Option(2)
  string <- Try(number.toLong).toOption
} yield string

// TODO: what's the difference between `for (...) yield` and `for {...} yield`

val combinations1 = for (i <- 1 to 3; j <- 4 to 6) yield i * 10 + j
// combinations1: scala.collection.immutable.IndexedSeq[Int] = Vector(14, 15, 16, 24, 25, 26, 34, 35, 36)

val combinations2 = for {
  i <- 1 to 3
  j <- 4 to 6
} yield i * 10 + j
// combinations2: scala.collection.immutable.IndexedSeq[Int] = Vector(14, 15, 16, 24, 25, 26, 34, 35, 36)

/*
  Note:

  You get the same result in `combinations1` and `combinations2`, it's just the syntax
  that is different. Both versions have two generators and yield a result for every
  combination of the two generators.
 */