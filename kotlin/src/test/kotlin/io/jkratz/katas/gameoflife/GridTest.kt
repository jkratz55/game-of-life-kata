package io.jkratz.katas.gameoflife

import org.junit.Test
import org.junit.Assert.*

class GridTest {

    @Test
    fun testDefaultConstructor() {
        val grid = Grid()
        assertEquals(8, grid.columns)
        assertEquals(8, grid.rows)
    }

    @Test
    fun testConstructorWithInitialState() {

        val grid = Grid(initialState)
        assertEquals(10, grid.columns)
        assertEquals(10, grid.rows)

        val state = grid.state
        for (i in state!!.indices) {
            assertArrayEquals(initialState[i], state[i])
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun testConstructorWithInitialStateJagged() {
        val jaggedInitialState = arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 1, 1, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 1, 1, 0, 0, 0),
            intArrayOf(1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 1, 1, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 1, 1, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        )

        val grid = Grid(jaggedInitialState)
    }

    @Test
    fun testNextState() {
        val grid = Grid(initialState)
        grid.nextState()

        val results = grid.state

        // Check row 0
        assertEquals(results!![0][0], 0)
        assertEquals(results[0][1], 0)
        assertEquals(results[0][2], 0)
        assertEquals(results[0][3], 0)
        assertEquals(results[0][4], 0)
        assertEquals(results[0][5], 1)
        assertEquals(results[0][6], 1)
        assertEquals(results[0][7], 0)
        assertEquals(results[0][8], 0)
        assertEquals(results[0][9], 0)

        // Check row 1
        assertEquals(results[1][0], 0)
        assertEquals(results[1][1], 0)
        assertEquals(results[1][2], 0)
        assertEquals(results[1][3], 0)
        assertEquals(results[1][4], 0)
        assertEquals(results[1][5], 1)
        assertEquals(results[1][6], 1)
        assertEquals(results[1][7], 0)
        assertEquals(results[1][8], 0)
        assertEquals(results[1][9], 0)

        // Check row 2
        assertEquals(results[2][0], 1)
        assertEquals(results[2][1], 1)
        assertEquals(results[2][2], 0)
        assertEquals(results[2][3], 0)
        assertEquals(results[2][4], 0)
        assertEquals(results[2][5], 0)
        assertEquals(results[2][6], 0)
        assertEquals(results[2][7], 0)
        assertEquals(results[2][8], 0)
        assertEquals(results[2][9], 0)

        // Check row 3
        assertEquals(results[3][0], 1)
        assertEquals(results[3][1], 1)
        assertEquals(results[3][2], 0)
        assertEquals(results[3][3], 0)
        assertEquals(results[3][4], 0)
        assertEquals(results[3][5], 0)
        assertEquals(results[3][6], 0)
        assertEquals(results[3][7], 0)
        assertEquals(results[3][8], 0)
        assertEquals(results[3][9], 0)

        // Check row 4
        assertEquals(results[4][0], 0)
        assertEquals(results[4][1], 0)
        assertEquals(results[4][2], 0)
        assertEquals(results[4][3], 0)
        assertEquals(results[4][4], 0)
        assertEquals(results[4][5], 0)
        assertEquals(results[4][6], 0)
        assertEquals(results[4][7], 0)
        assertEquals(results[4][8], 0)
        assertEquals(results[4][9], 0)

        // Check row 5
        assertEquals(results[5][0], 0)
        assertEquals(results[5][1], 0)
        assertEquals(results[5][2], 0)
        assertEquals(results[5][3], 0)
        assertEquals(results[5][4], 0)
        assertEquals(results[5][5], 1)
        assertEquals(results[5][6], 1)
        assertEquals(results[5][7], 0)
        assertEquals(results[5][8], 0)
        assertEquals(results[5][9], 0)

        // Check row 6
        assertEquals(results[6][0], 0)
        assertEquals(results[6][1], 0)
        assertEquals(results[6][2], 0)
        assertEquals(results[6][3], 0)
        assertEquals(results[6][4], 0)
        assertEquals(results[6][5], 1)
        assertEquals(results[6][6], 1)
        assertEquals(results[6][7], 0)
        assertEquals(results[6][8], 0)
        assertEquals(results[6][9], 0)

        // Check row 7
        assertEquals(results[7][0], 0)
        assertEquals(results[7][1], 0)
        assertEquals(results[7][2], 0)
        assertEquals(results[7][3], 0)
        assertEquals(results[7][4], 0)
        assertEquals(results[7][5], 0)
        assertEquals(results[7][6], 0)
        assertEquals(results[7][7], 0)
        assertEquals(results[7][8], 0)
        assertEquals(results[7][9], 0)

        // Check row 8
        assertEquals(results[8][0], 0)
        assertEquals(results[8][1], 0)
        assertEquals(results[8][2], 0)
        assertEquals(results[8][3], 0)
        assertEquals(results[8][4], 0)
        assertEquals(results[8][5], 0)
        assertEquals(results[8][6], 0)
        assertEquals(results[8][7], 0)
        assertEquals(results[8][8], 0)
        assertEquals(results[8][9], 0)

        // Check row 9
        assertEquals(results[9][0], 0)
        assertEquals(results[9][1], 0)
        assertEquals(results[9][2], 0)
        assertEquals(results[9][3], 0)
        assertEquals(results[9][4], 0)
        assertEquals(results[9][5], 0)
        assertEquals(results[9][6], 0)
        assertEquals(results[9][7], 0)
        assertEquals(results[9][8], 0)
        assertEquals(results[9][9], 0)
    }

    @Test
    fun testNextStateWithEdgeCells() {
        val initData = arrayOf(
            intArrayOf(1, 0, 0, 0, 0, 1, 1, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 1, 1, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 1, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 1, 1, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 1, 1, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 1, 0, 0, 0, 0, 0, 0, 1, 1),
            intArrayOf(1, 1, 0, 0, 0, 0, 0, 0, 1, 1)
        )

        val grid = Grid(initData)
        grid.nextState()
        val results = grid.state

        assertEquals(results!![0][0], 0)
        assertEquals(results[9][0], 1)
        assertEquals(results[9][9], 1)
        assertEquals(results[0][9], 0)
    }

    @Test
    fun testNextStateWithMultipleIterations() {
        val grid = Grid(initialState)
        for (i in 0..9999) {
            grid.nextState()
        }
    }

    companion object {

        private val initialState = arrayOf(
            intArrayOf(0, 0, 0, 0, 0, 1, 1, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 1, 1, 0, 0, 0),
            intArrayOf(1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(1, 1, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 1, 1, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 1, 1, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
            intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        )
    }
}
