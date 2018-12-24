package io.jkratz.katas.gameoflife

object Life {

    @JvmStatic
    fun main(args: Array<String>) {
        val board = Board(10,10)
        println("State 0")
        board.prettyPrint()
        board.evolve()
        println("State 1")
        board.prettyPrint()
    }
}