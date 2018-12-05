package io.jkratz.katas.gameoflife;

/**
 * The {@code Grid} class represents a two dimensional grid holding the
 * state of cells for Conway's Game of Life.
 *
 * @author Joseph Kratz (joseph.kratz06@gmail.com)
 */
public class Grid {

    public static int DEFAULT_WIDTH = 8;
    public static int DEFAULT_HEIGHT = 8;
    public static int CELL_ALIVE = 1;
    public static int CELL_DEAD = 0;

    private int[][] state;
    private int rows;
    private int columns;

    /**
     * Creates Grid instance with a randomly generated state.
     */
    public Grid() {
        this.state = new int[DEFAULT_WIDTH][DEFAULT_HEIGHT];
        for (int i=0; i<DEFAULT_WIDTH; i++) {
            for (int j=0; j<DEFAULT_HEIGHT; j++) {
                this.state[i][j] = (int) Math.round(Math.random());
            }
        }
        this.columns = this.state.length;
        this.rows = this.state[0].length;
    }

    /**
     * Creates Grid instance with an initial state.
     *
     * @param initialState The initial state, this cannot be a jagged array.
     * @throws IllegalArgumentException If the {@code initialState} is a jagged array, or NULL
     */
    public Grid(int[][] initialState) {
        if (initialState == null) {
            throw new IllegalArgumentException("initialState cannot be NULL");
        }
        if (isJagged(initialState)) {
            throw new IllegalArgumentException("initialState cannot be a jagged array");
        }

        this.state = initialState;
        this.columns = this.state.length;
        this.rows = this.state[0].length;
    }

    /**
     * Returns the current state of the grid
     *
     * @return The current state
     */
    public int[][] getState() {
        return this.state;
    }

    /**
     * Returns the count of rows
     *
     * @return
     */
    public int rows() {
        return this.rows;
    }

    /**
     * Returns the count of columns
     *
     * @return
     */
    public int columns() {
        return this.columns;
    }

    /**
     * Transitions to the next state of the grid.
     */
    public void nextState() {
        int[][] nextState = new int[this.rows][this.columns];

        // iterate through two dimensional array
        for (int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                nextState[i][j] = this.getNextStateForCell(i,j);
            }
        }

        this.state = nextState;
    }

    /**
     * Gets next state for a particular cell
     *
     * @param i Row index of the grid
     * @param j Column index of the grid
     * @return 1 is cell alive, 0 if cell is dead
     */
    private int getNextStateForCell(int i, int j) {

        // Get living neighbors
        int aliveNeighbors = this.calculateLivingNeighbors(i,j);

        // Cell is lonely with less than two live neighbors and dies
        if ((this.state[i][j] == 1) && (aliveNeighbors < 2)) {
            return CELL_DEAD;
        }

        // Cell is overcrowded and dies
        else if ((this.state[i][j] == 1) && aliveNeighbors > 3) {
            return CELL_DEAD;
        }

        // Cell is dead but 3 lives neighbors causes it to be born
        else if (this.state[i][j] == 0 && aliveNeighbors == 3) {
            return CELL_ALIVE;
        }

        // Nothing changes so copy that state
        else {
            return this.state[i][j];
        }
    }

    /**
     * Returns the count of living neighbors for a particular cell in the grid
     *
     * @param i Row index of the grid
     * @param j Column index of the grid
     * @return Count of living neighbors for particular cell
     */
    private int calculateLivingNeighbors(int i, int j) {
        int liveCount = 0;
        for (int x=-1; x<=1; x++) {
            for (int y = -1; y <= 1; y++) {
                // check for boundary conditions
                if (i + x < 0 || i + x > (this.rows - 1) || y + j < 0 || y + j > (this.columns - 1)) {
                    continue;
                }
                liveCount += this.state[i + x][y + j];
            }
        }

        // remove since we may have counted ourselves
        liveCount -= this.state[i][j];
        return liveCount;
    }

    /**
     * Determine if a given array is jagged.
     *
     * @param array The array to test
     * @return True if array is jagged, otherwise false
     */
    private boolean isJagged(int[][] array) {
        boolean isJagged = false;

        if (array != null) {
            Integer length = null;
            for (int i=0; i<array.length; i++) {
                if (length == null) {
                    length = array[i].length;
                }
                if (!length.equals(array[i].length)) {
                    isJagged = true;
                    break;
                }
            }
        }
        return isJagged;
    }
}
