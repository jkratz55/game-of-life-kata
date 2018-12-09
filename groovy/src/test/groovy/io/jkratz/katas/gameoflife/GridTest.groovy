package io.jkratz.katas.gameoflife

import org.junit.Test

import static org.junit.Assert.*

class GridTest {

    @Test
    void testDefaultConstructor() {
        Grid grid = new Grid()
        assertEquals(10, grid.columns)
        assertEquals(10, grid.rows)
    }

    @Test
    void testConstructorWithInitialState() {

    }

    @Test(expected = IllegalArgumentException.class)
    void testConstructorWithInvalidInitialState() {
        grid = new Grid(null)
    }

    @Test(expected = IllegalArgumentException.class)
    void testConstructorWithNullInitialState() {
        int[][] badData = null
        Grid grid = new Grid(badData)
    }

    @Test
    void testNextState() {

    }
}
