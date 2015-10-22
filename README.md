** Chapter 1

*Type classes* are a programming pattern originating from Haskell. They allow us to extend existing libraries with
new functionality, without using traditional inheritance, and without altering the original library source code.
 - interface objects: singleton object
 - interface syntax: implicit class

** Chapter 2, Monoids
Monoid syntax:
 - |+| for append operation
 - mzero for unit
Type class variance
 - invariant, Foo[A]: no matter what's the relation between B and C Foo[B] and Foo[C] has no relationship
 - Foo[+A] covariant: B is super type of C then Foo[B] is super type of Foo[C]
 - Foo[-A] covariant: B is super type of C then Foo[B] is sub type of Foo[C]
Smart constructors in scalaz
 - in scala we have Some(1), or None. They will have type of Some[Int] and None.type
 - in scalaz we have some(1) or none[Int], they will be Option[Int]
Identically typed typeclass instances
 - we can wrap the type in some class and define the typeclass on it; like monoid for Multiplication(i: Int)
 - we can use tags from scalaz Int @@ Multiplication, and use Multiplication(i) values instead of i.

** Chapter 3, Functors
 - we have map on the construction
 - mapping functions is composing them;
   we have F[A]: R => A, f: A => B, the mapping the F[A] with the f will give F[B]: R => B

 - type constructor: like functions: List is a type constructor, List[Int] is the type.
   We declare type constructor like: def method[F[_]] = { val functor = Functor.apply[F] }
 - we create functor for list like: Functor[List].apply . Note: we dont need List[_] or anything because it's using type
   constructor.
 - lift method: we have a function A => B, on a option functor we can lift it to option; so we'll get Option[A] => Option[B].

** Chapter 4, Monad
 - a monad for a type F[A] has:
    - an operation bind with type (F[A], A => B) => F[B]
    - an operation return with type A => F[A]
 - A monad must obey three laws:
   1. Left identity: (return(a) bind f) == f(a)
   2. Right identity: (m bind return) == m
   3. Associativity: (m bind f bind g) == (m bind (x => f(x) bind g))
 - The tupleN methods convert a tuple of monads into a monad of tuples
 - The sequence converts F[G[A]] to G[F[A]], where F is traversable
 - Id monad, scalaz.Id
 - scalaz.\/ is disjunction, alternative for Either. It's right biased

** Chapter 5, monad transformers
 - combining monads can lead to terrible code
 - it's impossible to combine generally different monads
 - in scalaz there are few monad transformers, ListT, OptionT
 - check stack of monads

** Chapter 6, applicatives
 - definition of F[A] applicative:
   - an operation append with type (F[A], F[A => B]) => F[B]
   - an operation pure with type A => F[A], same as return with monad
 - the intuitive difference here is that monads are context sensitive while applicatives are context free
 - rules for applicatives:
   - identity: append(a)(pure(x => x)) == a
   - homomorphism: append(pure(a))(pure(b)) == pure(b(a))
   - interchange: append(pure(a))(fb) == append(fb)(pure(x => x(a)))
   - map-like: map(fa)(fb) == append(fa)(pure(fb))