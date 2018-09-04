// Variance
// see: https://docs.scala-lang.org/tour/variances.html

// There are three types of variance: covariance, contravariance & invariance

// Variance represents the correlation between complex types (e.g. List[A] and List[B]) and
// their component types (e.g. A and B)

// 1. Covariance
class Foo[+A] // A covariant class

// -> in case of covariance the correlation between two complex types corresponds
//    to the correlation of their component types
/*
  Example:

  Assuming we have two types A and B, where A is a subtype of B, covariance
  would mean that List[A] is also a subtype of List[B]. To achieve this you
  need to declare List[A] as covariant: `class List[+A]`

  !! remember that List[+A] is declared as covariant, not List[B].
  // TODO: does this make a difference? do you always specify the variance on the extending class?
 */

abstract class Animal {
  def name: String
}

case class Cat(name: String) extends Animal
case class Dog(name: String) extends Animal

object CovarianceTest extends App {
  def printAnimalNames(animals: List[Animal]): Unit = {
    animals.foreach { animal =>
      println(animal.name)
    }
  }

  val cats: List[Cat] = List(Cat("Whiskers"), Cat("Tom"))
  val dogs: List[Dog] = List(Dog("Fido"), Dog("Rex"))

  printAnimalNames(cats)

  printAnimalNames(dogs)

  // This example only works because the Scala standard library
  // has a generic immutable `sealed abstract class List[+A]` class,
  // where the type parameter A is covariant.

  // Were it only `sealed abstract class List[A]` this example
  // would not compile
}


// 2. Contravariance
class Bar[-A] // A contravariant class

// -> a contravariance relationship is similar to the covariant
//    one, only opposite

/*
  Example:

  For two types A and B where A is a subtype of B, marking a class
  such as `class Writer[A]` as contravariant (i.e. `class Writer [-A]`)
  would imply that Writer[B] is a subtype of Writer[A]

 */

// notice how the abstract class is declared as contravariant
// TODO: in this case we're declaring the class to be extended as contravariant; is this always the case?
// -> this is the generic class
abstract class Printer[-A] {
  def print(value: A): Unit
}

// specific class for the `Animal` type
// -> it's interesting that
class AnimalPrinter extends Printer[Animal] {
  def print(animal: Animal): Unit = println("The animal's name is: " + animal.name)
}

// remember that `Cat` is a subtype of `Animal`
class CatPrinter extends Printer[Cat] {
  def print(cat: Cat): Unit = println("The cat's name is: " + cat.name)
}


// 3. Invariance
class Baz[A]  // An invariant class