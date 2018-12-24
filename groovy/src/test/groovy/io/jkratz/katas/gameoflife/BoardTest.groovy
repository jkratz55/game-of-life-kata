package io.jkratz.katas.gameoflife

import org.junit.Test

import static org.junit.Assert.*

class BoardTest {

    static final int[][] testData = [
            [ 1, 1, 1, 1, 0, 1, 1, 0, 0, 0 ],
            [ 1, 1, 1, 1, 0, 1, 1, 0, 0, 0 ],
            [ 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 ],
            [ 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 ],
            [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ],
            [ 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 ],
            [ 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 ],
            [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ],
            [ 0, 1, 0, 0, 0, 0, 0, 0, 1, 1 ],
            [ 1, 1, 0, 0, 0, 0, 0, 0, 1, 1 ]
    ]

    static final int[][] expectedData = [
            [ 1, 0, 0, 1, 0, 1, 1, 0, 0, 0 ],
            [ 1, 0, 0, 0, 0, 1, 1, 0, 0, 0 ],
            [ 1, 0, 0, 1, 0, 0, 0, 0, 0, 0 ],
            [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ],
            [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ],
            [ 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 ],
            [ 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 ],
            [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ],
            [ 1, 1, 0, 0, 0, 0, 0, 0, 1, 1 ],
            [ 1, 1, 0, 0, 0, 0, 0, 0, 1, 1 ]
    ]

    static final int[][] jaggedData = [
            [ 1, 0, 0, 0, 0, 1, 1, 0, 0, 0 ],
            [ 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 ],
            [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ],
            [ 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 ],
            [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ],
            [ 0, 0, 0, 0, 0, 1, 1 ],
            [ 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 ],
            [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ],
            [ 0, 1, 0, 0, 0, 0, 0, 0, 1, 1 ],
            [ 1, 1, 0, 0, 0, 0, 0, 0, 1, 1 ]
    ]

    @Test
    void testDefaultConstructor() {
        Board grid = new Board(10,10)
        assertEquals(10, grid.columns)
        assertEquals(10, grid.rows)
    }

    @Test
    void testConstructorWithInitialState() {
        Board grid = new Board(testData)
        assertEquals(10, grid.rows)
        assertEquals(10, grid.columns)

        int[][] state = grid.getState()
        for (int i=0; i < state.length; i++) {
            assertArrayEquals(testData[i], state[i])
        }
    }

    @Test(expected = IllegalArgumentException.class)
    void testConstructorWithInvalidInitialState() {
        grid = new Board(null)
    }

    @Test(expected = IllegalArgumentException.class)
    void testConstructorWithNullInitialState() {
        Board grid = new Board(jaggedData)
    }

    @Test
    void testNextState() {
        Board grid = new Board(testData)
        grid.evolve()
        int[][] state = grid.getState()
        for (int i=0; i < state.length; i++) {
            assertArrayEquals(expectedData[i], state[i])
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidValues() {
        int[][] initialState = [
            [ 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 ],
            [ 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 ],
            [ 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 ],
            [ 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 ],
            [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ],
            [ 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 ],
            [ 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 ],
            [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ],
            [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ],
            [ 0, 0, 0, 0, 0, 0, -1, 4, 3, 2 ]
        ]
        final Board board = new Board(initialState)
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidSize() {
        final Board board = new Board(new int[0][0])
    }
}
