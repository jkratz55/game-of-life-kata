package io.jkratz.katas.gameoflife

import org.junit.Test

import static org.junit.Assert.*

class GridTest {

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
        Grid grid = new Grid()
        assertEquals(10, grid.columns)
        assertEquals(10, grid.rows)
    }

    @Test
    void testConstructorWithInitialState() {
        Grid grid = new Grid(testData)
        assertEquals(10, grid.rows)
        assertEquals(10, grid.columns)

        int[][] state = grid.getState()
        for (int i=0; i < state.length; i++) {
            assertArrayEquals(testData[i], state[i])
        }
    }

    @Test(expected = IllegalArgumentException.class)
    void testConstructorWithInvalidInitialState() {
        grid = new Grid(null)
    }

    @Test(expected = IllegalArgumentException.class)
    void testConstructorWithNullInitialState() {
        Grid grid = new Grid(jaggedData)
    }

    @Test
    void testNextState() {
        Grid grid = new Grid(testData)
        grid.nextState()
        int[][] state = grid.getState()
        for (int i=0; i < state.length; i++) {
            assertArrayEquals(expectedData[i], state[i])
        }
    }
}
