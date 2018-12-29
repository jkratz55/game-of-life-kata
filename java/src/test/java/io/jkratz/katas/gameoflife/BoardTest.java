package io.jkratz.katas.gameoflife;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {
    
    private static int[][] initialState = {
            {1, 1, 1, 1, 0, 1, 1, 0, 0, 0},
            {1, 1, 1, 1, 0, 1, 1, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 0, 0, 0, 0, 1, 1},
            {1, 1, 0, 0, 0, 0, 0, 0, 1, 1}
    };

    private static int[][] expectedState = {
            {1, 0, 0, 1, 0, 1, 1, 0, 0, 0},
            {1, 0, 0, 0, 0, 1, 1, 0, 0, 0},
            {1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {1, 1, 0, 0, 0, 0, 0, 0, 1, 1},
            {1, 1, 0, 0, 0, 0, 0, 0, 1, 1}
    };

    @Test
    public void testDefaultConstructor() {
        final Board board = new Board(8, 8);
        assertEquals(8, board.columns());
        assertEquals(8, board.rows());
    }

    @Test
    public void testConstructorWithInitialState() {

        final Board board = new Board(initialState);
        assertEquals(10, board.columns());
        assertEquals(10, board.rows());

        int[][] state = board.getState();
        for (int i=0; i < state.length; i++) {
            assertArrayEquals(initialState[i], state[i]);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInitialStateNull() {
        final Board board = new Board(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidValues() {
        int[][] initialState = {
                { 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 },
                { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, -1, 4, 3, 2 }
        };
        final Board board = new Board(initialState);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInvalidSize() {
        final Board board = new Board(new int[0][0]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithInitialStateJagged() {
        int[][] jaggedInitialState = {
                { 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 },
                { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0 }
        };

        final Board board = new Board(jaggedInitialState);
    }

    @Test
    public void testEvolve() {
        final Board board = new Board(initialState);
        board.evolve();
        
        int[][] results = board.getState();
        for (int i=0; i<results.length; i++) {
            assertArrayEquals(expectedState[i], results[i]);
        }
    }

    @Test
    public void testNextStateWithEdgeCells() {
        int[][] initData = {
                { 1, 0, 0, 0, 0, 1, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
                { 0, 1, 0, 0, 0, 0, 0, 0, 1, 1 },
                { 1, 1, 0, 0, 0, 0, 0, 0, 1, 1 }
        };

        Board board = new Board(initData);
        board.evolve();
        int[][] results = board.getState();

        assertEquals(results[0][0], 0);
        assertEquals(results[9][0], 1);
        assertEquals(results[9][9], 1);
        assertEquals(results[0][9], 0);
    }
}
