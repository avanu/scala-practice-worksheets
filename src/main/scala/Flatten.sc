// flatten
// url: https://www.scala-lang.org/api/2.12.6/scala/collection/Traversable.html#flatten[B]:Traversable[B]

// Converts a collection of traversable collections into a collection formed by the elements of these traversable collections.

/**
* this basically concatenates all the element collections
  */
val xs = List(Set(1, 2, 3), Set(1, 2, 3)).flatten
// xs: List[Int] = List(1, 2, 3, 1, 2, 3)

/**
* this also concatenates all element collections but
  * a set cannot have duplicate elements;
  *
  * The flatten method returns Set(1, 2, 3, 3, 2, 1) but
  * ignoring the duplicate elements produces Set(1, 2, 3)
  */
val ys = Set(List(1, 2, 3), List(3, 2, 1)).flatten
// ys: scala.collection.immutable.Set[Int] = Set(1, 2, 3)

val duplicateSet = Set(1, 2, 3, 3, 2, 1)
// duplicateSet: scala.collection.immutable.Set[Int] = Set(1, 2, 3)

// see also: https://alvinalexander.com/scala/how-to-extract-unique-elements-scala-sequences-cookbook