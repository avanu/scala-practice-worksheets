// Collections
// url: https://docs.scala-lang.org/overviews/collections/introduction.html

/* It is convenient to treat all collections as either a
scala.collection.Traversable or scala.collection.Iterable,
as these traits define the vast majority of operations on a collection. */

// https://www.scala-lang.org/api/current/scala/collection/Traversable.html
// https://www.scala-lang.org/api/current/scala/collection/Iterable.html

/* The three most commonly used collections are
 - scala.collection.Seq,
 - scala.collection.immutable.Set, and
 - scala.collection.immutable.Map. */

// ========================================================================= //

// Map
Map("one" -> 1, "two" -> 2, "three" -> 3) // simpler way to write Maps

val m = Map(("one", 1), ("two", 2), ("three", 3), ("four", 4))

(m get "one").getOrElse("none") // can be written better (see row below)
m.getOrElse("one", "none")

// ========================================================================= //

// Array
// url: https://www.scala-lang.org/api/current/scala/Array.html
val myArray = Array(1, 2, 3, 4, 5)

// ========================================================================= //

// List
// url: https://www.scala-lang.org/api/2.12.3/scala/collection/immutable/List.html
val myList = List(1, 2, 3, 4, 5)

Seq.empty // the empty sequence
val fillList = List.fill(5){"a"} // fillList: List[String] = List(a, a, a, a, a)
// val fillList2 = List.fill(3){ TODO: some kind of expression?
val consSyntaxList = 1 :: 2 :: 3 :: Nil // consSyntaxList: List[Int] = List(1, 2, 3)

// ========================================================================= //

// Sequence
// url: https://www.scala-lang.org/api/2.12.0/scala/collection/Seq.html
// - sequences always have a defined order of elements
val mySeq = Seq(1, 2, 3, 4, 5) // mySeq: Seq[Int] = List(1, 2, 3, 4, 5)
// Seq is an "abstract base class" so I guess this is why it's created as a List
// -> "calling apply will produce some default implementation of the abstract base class"
// -> see https://docs.scala-lang.org/overviews/collections/creating-collections-from-scratch.html
val sequenceLength = mySeq.length // sequenceLength: Int = 5
val prefixLength = mySeq.prefixLength(x => x % 2 == 0) // what does this actually do?
val firstElement = mySeq.head // firstElement: Int = 1
val indexOfThree = mySeq.indexOf(3) // indexOfThree: Int = 2
val reversedSequence = mySeq.reverse // reversedSequence: Seq[Int] = List(5, 4, 3, 2, 1)

val myOtherSeq = Seq(1, 2, 3, 4, 3, 2, 1) // myOtherSeq: Seq[Int] = List(1, 2, 3, 4, 3, 2, 1)

// ========================================================================= //

// Sets
// url: https://docs.scala-lang.org/overviews/collections/sets.html
// - Sets are Iterables that contain no duplicate elements.

val mySet = Set(1, 2, 3, 4, 5, 4, 3, 2, 1)
// mySet: scala.collection.immutable.Set[Int] = Set(5, 1, 2, 3, 4)

val fruits = Set("banana", "peach", "lemon", "kitkat")
// fruits: scala.collection.immutable.Set[String] = Set(banana, peach, lemon, kitkat)

val teas = Set("green", "black", "white", "red", "green")
// teas: scala.collection.immutable.Set[String] = Set(green, black, white, red)

val chocolates = Set("cadbury", "milka", "kitkat")
val diffSet = chocolates diff fruits // chocolates &~ fruits
// diffSet: scala.collection.immutable.Set[String] = Set(cadbury, milka)

fruits.contains("peach") // res3: Boolean = true
fruits.contains("apple") // res4: Boolean = false

/* The apply method for a set is the same as contains,
so set(elem) is the same as set contains elem */
val hasPeach = fruits contains "peach" // hasPeach: Boolean = true

/*  That means sets can also be used as test functions that
return true for the elements they contain. */
val hasApple = fruits("apple") // hasApple: Boolean = false

val chocolatesAndFruits = chocolates ++ fruits
// chocolatesAndFruits: scala.collection.immutable.Set[String] = Set(peach, kitkat, banana, cadbury, lemon, milka)


