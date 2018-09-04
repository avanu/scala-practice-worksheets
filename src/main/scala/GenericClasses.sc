// Generic Classes
// see: https://docs.scala-lang.org/tour/generic-classes.html

// Generic classes are classes which take a type as a parameter.
// They are particularly useful for collection classes.

/**
  *
  * @tparam A
  *
  * The implementation of this class takes any type `A` as
  * parameter. The underlying List can only store elements
  * of type `A`.
  */
class Stack[A] {
  // Define an empty List
  // -> in Scala Nil is an object representing an empty list
  // -> see: https://www.scala-lang.org/api/2.5.1/scala/Nil$object.html
  private var elements: List[A] = Nil

  // The `push` method adds an element of type `A` to the beginning of the list
  // -> actually a new list is created and elements is assigned to this new list
  def push(x: A) { elements = x :: elements }

  // The `peek` method returns the first (newest) element in the List
  def peek: A = elements.head

  // The `pop` method removes the first (newest) element from the list and returns it
  // -> tail returns the list without its first element.
  def pop(): A = {
    val currentTop = peek
    elements = elements.tail
    currentTop
  }

}

// To use a generic class, put the type in the square brackets in place of `A`
val myStack = new Stack[Int]


myStack.push(1)

myStack.push(2)

myStack.push(3)

val firstPoppedElement = myStack.pop()
// firstPoppedElement: Int = 3

val secondPoppedElement = myStack.pop()
// secondPoppedElement: Int = 2

val thirdPoppedElement = myStack.pop()
// thirdPoppedElement: Int = 1

// val fourthPoppedElement = myStack.pop()
// this throws an exception: java.util.NoSuchElementException: head of empty list

// If you specify a type in a generic class, it also accepts subtypes
class Fruit
class Apple extends Fruit
class Banana extends Fruit

val fruitStack = new Stack[Fruit]
val apple = new Apple
val banana = new Banana

fruitStack.push(apple)
fruitStack.peek
// res4: Fruit = Apple@1ea2329a
// -> the stack contains 1 Apple

fruitStack.push(banana)
// res5: Unit = ()
// -> `push` returns the empty list

fruitStack.peek
// res6: Fruit = Banana@57009a1
// the stack contains two Fruit objects, the Banana is on top

fruitStack.pop()
// res7: Fruit = Banana@57009a1
// -> pop and return the element on top (Banana)

fruitStack.peek
// res8: Fruit = Apple@1ea2329a
// -> again, the stack contains one element (Apple)

