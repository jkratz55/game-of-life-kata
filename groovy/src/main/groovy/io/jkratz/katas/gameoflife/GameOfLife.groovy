package io.jkratz.katas.gameoflife

class GameOfLife {

    static void main(String[] args) {

        Grid grid = new Grid()
        int[][] initState = grid.state
        grid.nextState()
        int[][] nextState = grid.state

        println "Starting State"
        printGrid(initState)

        println "Next State"
        printGrid(nextState)
    }

    static void printGrid(int[][] grid) {
        grid.each { col ->
            col.each { x ->
                if (x == Grid.CELL_DEAD) {
                    print " . "
                } else {
                    print " * "
                }
            }
            println ""
        }
    }
}
