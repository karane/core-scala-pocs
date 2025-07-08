# Basic Scala POC

This project is a beginner-friendly Scala 3 project to help you 
learn the fundamental features of the language step by step.

---

## Features Covered

- Variables and Values (`val`, `var`)
- Type Inference and Primitive Types
- Functions (with default and named parameters)
- Control Structures (`if`, `match`, loops)
- Classes, Case Classes, and Traits
- Collections (`List`, `Map`, `Option`)
- Pattern Matching
- Tuples and For-Comprehensions
- Lazy Evaluation
- Error Handling with `Try`
- Context Parameters (`given`/`using` - Scala 3 feature)
- Implicits

---

## Prerequisites

- [Java 17+](https://adoptium.net/)
- [SBT](https://www.scala-sbt.org/download.html) (or install with [SDKMAN](https://sdkman.io/): `sdk install sbt`)

---

## How to Run

```bash
sbt run
```

This will run `Main.scala` and call each module's `run()` function in order, printing output and examples to the console.
