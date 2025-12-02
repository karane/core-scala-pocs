object VarianceExample {

  // Base class hierarchy
  abstract class Animal { def name: String }
  case class Cat(name: String) extends Animal
  case class Dog(name: String) extends Animal

  // 1. Covariance (+A)
  // Cage[Cat] can be used where Cage[Animal] is expected
  class Cage[+A](val occupant: A)

  def acceptAnimalCage(cage: Cage[Animal]): Unit =
    println(s"This cage holds: ${cage.occupant.name}")

  // 2. Contravariance (-A)
  // Trainer[Animal] can be used where Trainer[Cat] is expected
  class Trainer[-A] {
    def train(animal: A): Unit = println(s"Training: ${animal}")
  }

  def trainCat(trainer: Trainer[Cat]): Unit =
    trainer.train(Cat("Mittens"))

  // 3. Invariance (default)
  // CageInvariant[Cat] and CageInvariant[Animal] are unrelated
  class CageInvariant[A](val occupant: A)

  def main(args: Array[String]): Unit = {

    // Covariant example
    val catCage: Cage[Cat] = new Cage(Cat("Whiskers"))
    val dogCage: Cage[Dog] = new Cage(Dog("Buddy"))

    acceptAnimalCage(catCage)
    acceptAnimalCage(dogCage)

    // Contravariant example
    val animalTrainer: Trainer[Animal] = new Trainer[Animal]
    val catTrainer: Trainer[Cat] = new Trainer[Cat]

    trainCat(animalTrainer)
    trainCat(catTrainer)

    // Invariant example
    val invariantCatCage = new CageInvariant(Cat("Felix"))
//     val invariantAnimalCage: CageInvariant[Animal] = invariantCatCage // Compilation error

    println("Done.")
  }
}
