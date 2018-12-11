package io.jkratz.katas.gameoflife

class Grid {

    static int DEFAULT_ROWS = 10
    static int DEFAULT_COLUMNS = 10
    static int CELL_DEAD = 0
    static int CELL_ALIVE = 1

    private int[][] state
    final int rows = 0
    final int columns = 0

    Grid() {
        this.state = new int[DEFAULT_COLUMNS][DEFAULT_ROWS]
        for (int i=0; i<this.state.length; i++) {
            for (int j=0; j<this.state[i].length; j++) {
                this.state[i][j] = Math.round(Math.random()).toInteger()
            }
        }
        this.columns = DEFAULT_COLUMNS
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
        int[][] nextState = new int[this.columns][this.rows]
        for (int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                nextState[i][j] = this.getNextStateForCell(i,j)
            }
        }

        this.state = nextState
    }

    private int getNextStateForCell(int i, int j) {

        int liveCount = 0
        int cellValue = this.state[i][j]
        for (int x=-1; x<=1; x++) {
            for (int y=-1; y<=1; y++) {
                if (i + x < 0 || i + x > (this.rows - 1) || y + j < 0 || y + j > (this.columns - 1)) {
                    continue
                }
                liveCount += this.state[i + x][y + j]
            }
        }

        // remove since we may have counted ourselves
        liveCount -= cellValue

        if (cellValue == CELL_ALIVE && liveCount > 3) {
            return CELL_DEAD
        }
        else if (cellValue == CELL_ALIVE && liveCount < 2) {
            return CELL_DEAD
        }
        else if (cellValue == CELL_DEAD && liveCount == 3) {
            return CELL_ALIVE
        } else {
            return cellValue
        }

    }

    private boolean isJagged(int[][] grid) {
        boolean isJagged = false
        int baseLength = grid[0].length
        for (int[] arr: grid) {
            if (arr.length != baseLength) {
                isJagged = true
                break
            }
        }
        return isJagged
    }
}
