package io.jkratz.katas.gameoflife

object Life {

  def main(args: Array[String]): Unit = {

    val state0 = Board.random(5,5)
    println("State 0")
    Board.prettyPrint(state0)

    val state1 = state0.evolve()
    println("State 1")
    Board.prettyPrint(state1)
  }
}
