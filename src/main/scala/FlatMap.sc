// map and flatMap

// http://www.brunton-spall.co.uk/post/2011/12/02/map-map-and-flatmap-in-scala/

val myList = List(1, 2, 3, 4, 5, 6, 7)

// duplicate each element
val doubleValsList = myList.map(x => x * 2)
// doubleValsList: List[Int] = List(2, 4, 6, 8, 10, 12, 14)

// ========================================================================= //

// flatMap returns a sequence for each element and then flattens the result into the original list
def makeList(number: Int) =
  List(number - 1, number, number + 1) // make a list from a number

val listOfLists = myList.map(x => makeList(x))
// listOfLists: List[List[Int]] = List(List(0, 1, 2), List(1, 2, 3), List(2, 3, 4), List(3, 4, 5), List(4, 5, 6), List(5, 6, 7), List(6, 7, 8))

val flattenedList = myList.flatMap(x => makeList(x))
// flattenedList: List[Int] = List(0, 1, 2, 1, 2, 3, 2, 3, 4, 3, 4, 5, 4, 5, 6, 5, 6, 7, 6, 7, 8)

// ========================================================================= //

// returning a sequence or list from the function (in this case, an Option)
def getAsOption(x: Int): Option[Int] = if (x > 2) Some(x) else None
val greaterThanTwo = myList.map(x => getAsOption(x))
// greaterThanTwo: List[Option[Int]] = List(None, None, Some(3), Some(4), Some(5), Some(6), Some(7))

// an option can be considered a sequence that is either empty or has 1 item
val greaterThanTwoFlattened = myList.flatMap(x => getAsOption(x))
// greaterThanTwoFlattened: List[Int] = List(3, 4, 5, 6, 7)
// we only get those elements back that are not empty

val myMap = Map(1 -> 10, 2 -> 20, 3 -> 30, 4 -> 40)
// myMap: scala.collection.immutable.Map[Int,Int] = Map(1 -> 10, 2 -> 20, 3 -> 30, 4 -> 40)

// internally a map is a sequence of tuples (I think…)
val listFromMap = myMap.toList
// listFromMap: List[(Int, Int)] = List((1,10), (2,20), (3,30), (4,40))

// this only applies the function to the Map values
val listFromMapValues = myMap.mapValues(x => x * 10)
// listFromMapValues: scala.collection.immutable.Map[Int,Int] = Map(1 -> 100, 2 -> 200, 3 -> 300, 4 -> 400)

// Access the inner variables of a tuple using _1 and _2
val myMapValues = myMap.map(x => x._2) // myMapValues: scala.collection.immutable.Iterable[Int] = List(10, 20, 30, 40)
val myMapValuesImproved = myMap.values // myMapValuesImproved: Iterable[Int] = MapLike.DefaultValuesIterable(10, 20, 30, 40)
val myMapKeys = myMap.map(x => x._1) // myMapKeys: scala.collection.immutable.Iterable[Int] = List(1, 2, 3, 4)
val myMapKeysImproved = myMap.keys // myMapKeysImproved: Iterable[Int] = Set(1, 2, 3, 4)

// here we return the values, looks similar to myMap.values
// the function returns a list but we're using flatmap
val myMapFlattened = myMap.flatMap(x => List(x._2))
// myMapFlattened: scala.collection.immutable.Iterable[Int] = List(10, 20, 30, 40)
// here we get a list back. why?

/**
  * Map(1 -> 10, 2 -> 20, 3 -> 30, 4 -> 40)
  *
  * List((1, 10), (2, 20), (3, 30), (4, 40))
  *
  * //  flatMap(x => List(x._2))
  * List(10, 20, 30, 40)
  */
// here we're returning the whole tuple and the result is a Map
val myMapFlattened2 = myMap.flatMap(x => List(x))
// myMapFlattened2: scala.collection.immutable.Map[Int,Int] = Map(1 -> 10, 2 -> 20, 3 -> 30, 4 -> 40)
// here we get a map back. why? maybe because a map is a list of tuples?

/**
  *
  * Map(1 -> 10, 2 -> 20, 3 -> 30, 4 -> 40)
  *
  * List((1, 10), (2, 20), (3, 30), (4, 40))
  *
  * //  flatMap(x => List(x.))
  *
  * ???
  */
// ========================================================================= //
// url: https://alvinalexander.com/scala/collection-scala-flatmap-examples-map-flatten

val fruits = Seq("apple", "banana", "melon", "peach", "orange")

val mapFruits = fruits.map(_.toUpperCase)
// mapFruits: Seq[String] = List(APPLE, BANANA, MELON, PEACH, ORANGE)

// flatMap flattens the list of strings to a sequence of characters
val flatMapFruits = fruits.flatMap(_.toUpperCase)
// flatMapFruits: Seq[Char] = List(A, P, P, L, E, B, A, N, A, N, A, M, E, L, O, N, P, E, A, C, H, O, R, A, N, G, E)

// flatmap is equivalent to running map and then flatten
val mapFruitsFlattened = mapFruits.flatten
// mapFruitsFlattened: Seq[Char] = List(A, P, P, L, E, B, A, N, A, N, A, M, E, L, O, N, P, E, A, C, H, O, R, A, N, G, E)

// ========================================================================= //
// url: https://alvinalexander.com/scala/collection-scala-flatmap-examples-map-flatten

def toInt(s: String): Option[Int] = {
  try {
    Some(Integer.parseInt(s.trim))
  } catch {
    case e: Exception => None
  }
}

val strings = Seq("1", "2", "three", "4", "five")
// strings: Seq[String] = List(1, 2, three, 4, five)

// apply the toInt function on every element in the sequence
val mapStrings = strings.map(toInt)
// mapStrings: Seq[Option[Int]] = List(Some(1), Some(2), None, Some(4), None)

/**
  * apply the toInt function to every element and then flatten the resulting collections
  *
  *   // initial list
  *   List(1, 2, three, 4, five)
  *
  *   // after mapping the toInt function to every element
  *   List(Some(1), Some(2), None, Some(4), None)
  *
  *   // None is ()
  *   List(Some(1), Some(2), (), Some(4), ())
  *
  *   // after flattening
  *   List(1, 2, 4)
  *
   */
val flatMapStrings = strings.flatMap(toInt)
// flatMapStrings: Seq[Int] = List(1, 2, 4)

// map and then flatten (the same as flatMap)
val mapAndFlattenStrings = strings.map(toInt).flatten
// mapAndFlattenStrings: Seq[Int] = List(1, 2, 4)

val flatMapAndSumStrings = strings.flatMap(toInt).sum
// flatMapAndSumStrings: Int = 7

Seq(1, 2, 3, 4) flatMap { x => Seq(x, x * x)
}
// res0: Seq[Int] = List(1, 1, 2, 4, 3, 9, 4, 16)

// get only the even numbers
Seq(1, 2, 3, 4, 5) flatMap { x =>
  if (x % 2 == 0) Seq(x)
  else Seq()
}
// res1: Seq[Int] = List(2, 4)

// ========================================================================= //
// url: https://alvinalexander.com/scala/collection-scala-flatmap-examples-map-flatten

// in the functional programming world, flatMap is used to work with
// monads — Scala data types that implement map and flatMap — such as
// Option, List, Future, etc

val o1 = Option(1)
// o1: Option[Int] = Some(1)

val oo1 = Option(o1)
// oo1: Option[Option[Int]] = Some(Some(1))

val ooo1 = Option(oo1)
// ooo1: Option[Option[Option[Int]]] = Some(Some(Some(1)))

ooo1 flatMap { oo1 =>
  oo1 flatMap { o1 => o1
  }
}
// res2: Option[Int] = Some(1)

// see: https://alvinalexander.com/scala/handling-nested-options-with-flatmap-for

// this is equivalent…
val x1 = Option(3) flatMap { i =>
  Option(4) map { j => i * j
  }
}
// x1: Option[Int] = Some(12)

// …to this
val x2 = for {
  i <- Option(3)
  j <- Option(4)
} yield i * j
// x2: Option[Int] = Some(12)

/**
  * The first example (x1) first applies the function (i => Option(4) map {j => i * j} on
  * Option(3).
  *
  * Option(4) map {j => Option(3) * j}
  *
  * Option(3) * (Option 4)
  *
  * // -> ???
  */













