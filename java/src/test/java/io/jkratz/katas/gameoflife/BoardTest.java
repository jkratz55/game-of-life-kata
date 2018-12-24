package io.jkratz.katas.gameoflife;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {
    
    private static int[][] initialState = {
            { 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 },
            { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 1, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 1, 1, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }
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
    public void testNextState() {
        final Board board = new Board(initialState);
        board.evolve();
        
        int[][] results = board.getState();

        // Check row 0
        assertEquals(results[0][0], 0);
        assertEquals(results[0][1], 0);
        assertEquals(results[0][2], 0);
        assertEquals(results[0][3], 0);
        assertEquals(results[0][4], 0);
        assertEquals(results[0][5], 1);
        assertEquals(results[0][6], 1);
        assertEquals(results[0][7], 0);
        assertEquals(results[0][8], 0);
        assertEquals(results[0][9], 0);

        // Check row 1
        assertEquals(results[1][0], 0);
        assertEquals(results[1][1], 0);
        assertEquals(results[1][2], 0);
        assertEquals(results[1][3], 0);
        assertEquals(results[1][4], 0);
        assertEquals(results[1][5], 1);
        assertEquals(results[1][6], 1);
        assertEquals(results[1][7], 0);
        assertEquals(results[1][8], 0);
        assertEquals(results[1][9], 0);

        // Check row 2
        assertEquals(results[2][0], 1);
        assertEquals(results[2][1], 1);
        assertEquals(results[2][2], 0);
        assertEquals(results[2][3], 0);
        assertEquals(results[2][4], 0);
        assertEquals(results[2][5], 0);
        assertEquals(results[2][6], 0);
        assertEquals(results[2][7], 0);
        assertEquals(results[2][8], 0);
        assertEquals(results[2][9], 0);

        // Check row 3
        assertEquals(results[3][0], 1);
        assertEquals(results[3][1], 1);
        assertEquals(results[3][2], 0);
        assertEquals(results[3][3], 0);
        assertEquals(results[3][4], 0);
        assertEquals(results[3][5], 0);
        assertEquals(results[3][6], 0);
        assertEquals(results[3][7], 0);
        assertEquals(results[3][8], 0);
        assertEquals(results[3][9], 0);

        // Check row 4
        assertEquals(results[4][0], 0);
        assertEquals(results[4][1], 0);
        assertEquals(results[4][2], 0);
        assertEquals(results[4][3], 0);
        assertEquals(results[4][4], 0);
        assertEquals(results[4][5], 0);
        assertEquals(results[4][6], 0);
        assertEquals(results[4][7], 0);
        assertEquals(results[4][8], 0);
        assertEquals(results[4][9], 0);

        // Check row 5
        assertEquals(results[5][0], 0);
        assertEquals(results[5][1], 0);
        assertEquals(results[5][2], 0);
        assertEquals(results[5][3], 0);
        assertEquals(results[5][4], 0);
        assertEquals(results[5][5], 1);
        assertEquals(results[5][6], 1);
        assertEquals(results[5][7], 0);
        assertEquals(results[5][8], 0);
        assertEquals(results[5][9], 0);

        // Check row 6
        assertEquals(results[6][0], 0);
        assertEquals(results[6][1], 0);
        assertEquals(results[6][2], 0);
        assertEquals(results[6][3], 0);
        assertEquals(results[6][4], 0);
        assertEquals(results[6][5], 1);
        assertEquals(results[6][6], 1);
        assertEquals(results[6][7], 0);
        assertEquals(results[6][8], 0);
        assertEquals(results[6][9], 0);

        // Check row 7
        assertEquals(results[7][0], 0);
        assertEquals(results[7][1], 0);
        assertEquals(results[7][2], 0);
        assertEquals(results[7][3], 0);
        assertEquals(results[7][4], 0);
        assertEquals(results[7][5], 0);
        assertEquals(results[7][6], 0);
        assertEquals(results[7][7], 0);
        assertEquals(results[7][8], 0);
        assertEquals(results[7][9], 0);

        // Check row 8
        assertEquals(results[8][0], 0);
        assertEquals(results[8][1], 0);
        assertEquals(results[8][2], 0);
        assertEquals(results[8][3], 0);
        assertEquals(results[8][4], 0);
        assertEquals(results[8][5], 0);
        assertEquals(results[8][6], 0);
        assertEquals(results[8][7], 0);
        assertEquals(results[8][8], 0);
        assertEquals(results[8][9], 0);

        // Check row 9
        assertEquals(results[9][0], 0);
        assertEquals(results[9][1], 0);
        assertEquals(results[9][2], 0);
        assertEquals(results[9][3], 0);
        assertEquals(results[9][4], 0);
        assertEquals(results[9][5], 0);
        assertEquals(results[9][6], 0);
        assertEquals(results[9][7], 0);
        assertEquals(results[9][8], 0);
        assertEquals(results[9][9], 0);
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

    @Test
    public void testNextStateWithMultipleIterations() {
        Board board = new Board(initialState);
        for (int i=0; i<10000; i++) {
            board.evolve();
        }
    }
}
