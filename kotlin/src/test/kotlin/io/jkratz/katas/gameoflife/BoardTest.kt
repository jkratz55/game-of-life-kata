package io.jkratz.katas.gameoflife

import org.junit.Test
import org.junit.Assert.*

class BoardTest {

    @Test
    fun testDefaultConstructor() {
        val board = Board(8,8)
        assertEquals(8, board.columns)
        assertEquals(8, board.rows)
    }

    @Test
    fun testConstructorWithInitialState() {

        val board = Board(initialState)
        assertEquals(10, board.columns)
        assertEquals(10, board.rows)

        val state = board.state
        for (i in state.indices) {
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

        val board = Board(jaggedInitialState)
    }

    @Test
    fun testNextState() {
        val board = Board(initialState)
        board.evolve()

        for (i in board.state.indices) {
            assertArrayEquals(board.state[i], expectedState[i])
        }
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

        val grid = Board(initData)
        grid.evolve()
        val results = grid.state

        assertEquals(results[0][0], 0)
        assertEquals(results[9][0], 1)
        assertEquals(results[9][9], 1)
        assertEquals(results[0][9], 0)
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

        private val expectedState = arrayOf(
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
