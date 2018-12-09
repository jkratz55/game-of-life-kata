package io.jkratz.katas.gameoflife

class Grid {

    static int DEFAULT_ROWS = 10
    static int DEFAULT_COLUMMS = 10
    static int CELL_DEAD = 0
    static int CELL_ALIVE = 1

    private int[][] state
    final int rows = 0
    final int columns = 0

    Grid() {
        this.state = new int[DEFAULT_COLUMMS][DEFAULT_ROWS]
        Random random = new Random()
        for (int i=0; i<this.state.length; i++) {
            for (int j=0; j<this.state[i].length; j++) {
                this.state[i][j] = Math.round(Math.random()).toInteger()
            }
        }
        this.columns = DEFAULT_COLUMMS
        this.rows = DEFAULT_ROWS
    }

    Grid(int[][] initialState) {
        if (initialState == null) {
            throw new IllegalArgumentException("initialState cannot be NULL")
        }
        if (isJagged(initialState)) {
            throw new IllegalArgumentException("initialState cannot be jagged")
        }

        this.state = initialState
        this.columns = initialState.length
        this.rows = initialState[0].length
    }

    int[][] getState() {
        return this.state
    }

    void nextState() {

    }

    private int getNextStateForCell(int i, int j) {
        return 0
    }

    private boolean isJagged(int[][] grid) {
        return false
    }
}
