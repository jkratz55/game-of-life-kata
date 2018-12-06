package io.jkratz.katas.gameoflife

object GameOfLife {

    @JvmStatic
    fun main(args: Array<String>) {
        val grid = Grid()
        val current = grid.state
        grid.nextState()
        val nextState = grid.state

        println("Current Generation")
        printGrid(current!!)
        println("Next Generation")
        printGrid(nextState!!)
    }

    // TODO: This could be placed in a better location
    private fun printGrid(grid: Array<IntArray>) {
        for (columns in grid) {
            for (value in columns) {
                if (value == 0) {
                    print(" . ")
                } else {
                    print(" * ")
                }
            }
            println()
        }
    }
}