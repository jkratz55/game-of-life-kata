package io.jkratz.katas.gameoflife

class Life {

    static void main(String[] args) {

        Board grid = new Board(10,10)
        println "State 0"
        print grid
        grid.evolve()
        println "State 1"
        print grid
    }
}
