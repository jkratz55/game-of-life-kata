package io.jkratz.katas.gameoflife

import java.lang.IllegalArgumentException
import java.util.*

class Board {

    var state: Array<IntArray>
        private set
    val rows: Int
    val columns: Int

    /**
     * Creates Board instance with a randomly generated state.
     */
    constructor(rows: Int, columns: Int) {
        this.state = Array(rows) { IntArray(columns) }
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                this.state[i][j] = Math.round(Math.random()).toInt()
            }
        }
        this.columns = columns
        this.rows = rows
    }

    /**
     * Creates Board instance with an initial state.
     *
     * @param initialState The initial state, this cannot be a jagged array.
     * @throws IllegalArgumentException If the `initialState` is a jagged array, or NULL
     */
    constructor(initialState: Array<IntArray>) {
        val validationResult = validate(initialState)
        when (validationResult) {
            ValidationResult.INVALID_BAD_SIZE,
            ValidationResult.INVALID_BAD_VALUES,
            ValidationResult.INVALID_JAGGED -> throw IllegalArgumentException(validationResult.message)
            else -> {
                this.state = initialState
                this.rows = this.state.size
                this.columns = this.state[0].size
            }
        }
    }

    /**
     * Transitions to the next state of the grid.
     */
    fun evolve() {
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

        val aliveNeighbors = this.calculateLivingNeighbors(i, j)
        val cellValue = this.state[i][j]

        return if (cellValue == CELL_ALIVE && (aliveNeighbors < 2 || aliveNeighbors > 3)) {
            CELL_DEAD
        } else if (cellValue == CELL_DEAD && aliveNeighbors == 3) {
            CELL_ALIVE
        } else {
            cellValue
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
                liveCount += this.state[i + x][y + j]
            }
        }

        // remove since we may have counted ourselves
        liveCount -= this.state[i][j]
        return liveCount
    }

    private fun validate(state: Array<IntArray>): ValidationResult {
        if (state.isEmpty() || state[0].isEmpty()) {
            return ValidationResult.INVALID_BAD_SIZE
        }

        val size = state[0].size
        for (i in 0 until state.size) {
            if (size != state[i].size) {
                return ValidationResult.INVALID_JAGGED
            }
            for (j in 0 until state[i].size) {
                if (state[i][j] < 0 || state[i][j] > 1) {
                    return ValidationResult.INVALID_BAD_VALUES
                }
            }
        }
        return ValidationResult.VALID
    }

    override fun toString(): String {
        return "Board(state=${Arrays.toString(state)}, rows=$rows, columns=$columns)"
    }

    companion object {

        const val CELL_ALIVE = 1
        const val CELL_DEAD = 0
    }

    private enum class ValidationResult(val message: String) {
        VALID(""),
        INVALID_BAD_VALUES(""),
        INVALID_BAD_SIZE(""),
        INVALID_JAGGED("")
    }
}

fun Board.prettyPrint() {
    for (columns in this.state) {
        for (value in columns) {
            if (value == 0) {
                print(" - ")
            } else {
                print(" * ")
            }
        }
        println()
    }
}
