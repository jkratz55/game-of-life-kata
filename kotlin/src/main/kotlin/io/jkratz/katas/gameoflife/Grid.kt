package io.jkratz.katas.gameoflife

class Grid {

    var state: Array<IntArray>? = null
        private set
    var rows: Int = 0
        private set
    var columns: Int = 0
        private set

    /**
     * Creates Grid instance with a randomly generated state.
     */
    constructor() {
        this.state = Array(DEFAULT_WIDTH) { IntArray(DEFAULT_HEIGHT) }
        for (i in 0 until DEFAULT_WIDTH) {
            for (j in 0 until DEFAULT_HEIGHT) {
                this.state!![i][j] = Math.round(Math.random()).toInt()
            }
        }
        this.columns = this.state!!.size
        this.rows = this.state!![0].size
    }

    /**
     * Creates Grid instance with an initial state.
     *
     * @param initialState The initial state, this cannot be a jagged array.
     * @throws IllegalArgumentException If the `initialState` is a jagged array, or NULL
     */
    constructor(initialState: Array<IntArray>) {
        if (isJagged(initialState)) {
            throw IllegalArgumentException("initialState cannot be a jagged array")
        }

        this.state = initialState
        this.columns = this.state!!.size
        this.rows = this.state!![0].size
    }

    /**
     * Transitions to the next state of the grid.
     */
    fun nextState() {
        val nextState = Array(this.rows) { IntArray(this.columns) }

        // iterate through two dimensional array
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                nextState[i][j] = this.getNextStateForCell(i, j)
            }
        }
        this.state = nextState
    }

    /**
     * Gets next state for a particular cell
     *
     * @param i Row index of the grid
     * @param j Column index of the grid
     * @return 1 is cell alive, 0 if cell is dead
     */
    private fun getNextStateForCell(i: Int, j: Int): Int {

        // Get living neighbors
        val aliveNeighbors = this.calculateLivingNeighbors(i, j)

        return if (this.state!![i][j] == 1 && aliveNeighbors < 2) {
            CELL_DEAD
        } else if (this.state!![i][j] == 1 && aliveNeighbors > 3) {
            CELL_DEAD
        } else if (this.state!![i][j] == 0 && aliveNeighbors == 3) {
            CELL_ALIVE
        } else {
            this.state!![i][j]
        }
    }

    /**
     * Returns the count of living neighbors for a particular cell in the grid
     *
     * @param i Row index of the grid
     * @param j Column index of the grid
     * @return Count of living neighbors for particular cell
     */
    private fun calculateLivingNeighbors(i: Int, j: Int): Int {
        var liveCount = 0
        for (x in -1..1) {
            for (y in -1..1) {
                // check for boundary conditions
                if (i + x < 0 || i + x > this.rows - 1 || y + j < 0 || y + j > this.columns - 1) {
                    continue
                }
                liveCount += this.state!![i + x][y + j]
            }
        }

        // remove since we may have counted ourselves
        liveCount -= this.state!![i][j]
        return liveCount
    }

    /**
     * Determine if a given array is jagged.
     *
     * @param array The array to test
     * @return True if array is jagged, otherwise false
     */
    private fun isJagged(array: Array<IntArray>?): Boolean {
        var isJagged = false

        if (array != null) {
            var length: Int? = null
            for (i in array.indices) {
                if (length == null) {
                    length = array[i].size
                }
                if (length != array[i].size) {
                    isJagged = true
                    break
                }
            }
        }
        return isJagged
    }

    companion object {

        var DEFAULT_WIDTH = 8
        var DEFAULT_HEIGHT = 8
        var CELL_ALIVE = 1
        var CELL_DEAD = 0
    }
}