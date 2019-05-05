package io.jkratz.katas.gameoflife

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.util.*
import java.util.concurrent.CompletableFuture
import kotlin.collections.ArrayList

/**
 * Class to represent the state of the board for Conway's Game of Life.
 *
 * @author Joseph Kratz (joseph.kratz06@gmail.com)
 * @property state The state of the board
 * @property rows Count of rows on the board
 * @property columns Count of columns on the board
 */
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
     * @throws IllegalArgumentException If the initialState is not valid
     */
    constructor(initialState: Array<IntArray>) {
        when (val validationResult = validate(initialState)) {
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
     * Transitions to the next state of the board.
     */
    fun evolve() {
//        runBlocking {
//            val nextState = Array(rows) { IntArray(columns) }
//            for (i in 0 until rows) {
//                for (j in 0 until columns) {
//                    nextState[i][j] = withContext(Dispatchers.Default) {
//                        getNextStateForCell(i, j)
//                    }
//                }
//            }
//            state = nextState
//        }
        val nextState = Array(rows) { IntArray(columns) }
        val futures = ArrayList<CompletableFuture<Unit>>()
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                 futures.add(CompletableFuture.supplyAsync { getNextStateForCell(i,j) }
                    .thenApply {
                        nextState[i][j] = it
                    })
            }
        }
        futures.forEach { it.join() }
        state = nextState
    }

    /**
     * Gets next state for a particular cell
     *
     * @param i Row index of the grid
     * @param j Column index of the grid
     * @return 1 is cell alive, 0 if cell is dead
     */
    private fun getNextStateForCell(i: Int, j: Int): Int {

        val aliveNeighbors = calculateLivingNeighbors(i, j)
        val cellValue = state[i][j]

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
                if (i + x < 0 || i + x > rows - 1 || y + j < 0 || y + j > columns - 1) {
                    continue
                }
                liveCount += state[i + x][y + j]
            }
        }
        // remove since we may have counted ourselves
        liveCount -= state[i][j]
        return liveCount
    }

    /**
     * Validates the state of the board.
     *
     * @param state 2D state of the board
     */
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

    /* Represents the validation result of the initial state */
    private enum class ValidationResult(val message: String) {
        VALID(""),
        INVALID_BAD_VALUES("The state contains invalid values, only 0 or 1 is allowed"),
        INVALID_BAD_SIZE("The state must be at least 1X1 (1 row and 1 column)"),
        INVALID_JAGGED("The state cannot be jagged.")
    }
}

/**
 * Prints the state of the board to standard out
 */
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
