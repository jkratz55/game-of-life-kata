package io.jkratz.katas.gameoflife;

/**
 * The {@code Board} class represents a two dimensional grid holding the
 * state of cells for Conway's Game of Life. The board is represent with
 * integers 0 representing a dead cell, and 1 representing a live cell.
 *
 * @author Joseph Kratz (joseph.kratz06@gmail.com)
 */
public class Board {

    public static int CELL_DEAD = 0;
    public static int CELL_ALIVE = 1;

    private int[][] state;
    private int rows;
    private int columns;

    /**
     * Creates a board instance with a random state for the size provided.
     *
     * @param rows The amount of rows on the board
     * @param columns The amount of columns on the board
     */
    public Board(int rows, int columns) {
        this.state = new int[rows][columns];
        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                this.state[i][j] = (int) Math.round(Math.random());
            }
        }
        this.rows = this.state.length;
        this.columns = this.state[0].length;
    }

    /**
     * Creates Board instance with a provided initial state. The initial state
     * must not be NULL, jagged, contain values other than 0 or 1, and must be
     * at least 1 row and 1 column.
     *
     * @param initialState The initial state of the board
     * @throws IllegalArgumentException If the {@code initialState} is not valid
     */
    public Board(int[][] initialState) {

        ValidationResult validationResult = validate(initialState);
        switch (validationResult) {
            case INVALID_BAD_VALUES:
            case INVALID_BAD_SIZE:
            case INVALID_NULL:
            case INVALID_JAGGED:
                throw new IllegalArgumentException(validationResult.message);
            default:
                this.state = initialState;
                this.rows = this.state.length;
                this.columns = this.state[0].length;
        }
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
     * @return Rows on the board
     */
    public int rows() {
        return this.rows;
    }

    /**
     * Returns the count of columns
     *
     * @return Columns of on the board
     */
    public int columns() {
        return this.columns;
    }

    /**
     * Transitions to the next state of the Board.
     */
    public void evolve() {
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
     * @return Value for the next state (0 if dead, 1 if alive)
     */
    private int getNextStateForCell(int i, int j) {

        // Get living neighbors
        int aliveNeighbors = this.calculateLivingNeighbors(i,j);
        int cellValue = this.state[i][j];
        int newCellValue;

        if (cellValue == CELL_DEAD && aliveNeighbors == 3) {
            newCellValue = CELL_ALIVE;
        } else if (cellValue == CELL_ALIVE && (aliveNeighbors < 2 || aliveNeighbors > 3)) {
            newCellValue = CELL_DEAD;
        } else {
            newCellValue = cellValue;
        }

        return newCellValue;
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
     * Validates the state of the Board.
     *
     * @param state The state of the board
     * @return The result of the validation
     */
    private ValidationResult validate(int[][] state) {

        // If state is NULL return early as invalid, there is nothing else to check
        if (state == null) {
            return ValidationResult.INVALID_NULL;
        }

        if (state.length < 1 || state[0].length < 1) {
            return ValidationResult.INVALID_BAD_SIZE;
        }

        int size = state[0].length;
        for (int i=0; i<state.length; i++) {
            if (size != state[i].length) {
                return ValidationResult.INVALID_JAGGED;
            }
            for (int j=0; j<state[i].length; j++) {
                if (state[i][j] < 0 || state[i][j] > 1) {
                    return ValidationResult.INVALID_BAD_VALUES;
                }
            }
        }
        return ValidationResult.VALID;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int[] columns : this.state) {
            for (int value : columns) {
                if (value == 0) {
                    stringBuilder.append(" - ");
                } else {
                    stringBuilder.append(" * ");
                }
            }
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    /* Enum representing the possible validation results of the state of the board. */
    private enum ValidationResult {

        VALID(""),
        INVALID_JAGGED("Invalid input, state cannot be jagged"),
        INVALID_BAD_VALUES("Invalid values, only 0 and 1 are valid values"),
        INVALID_BAD_SIZE("There must be at least one row and one column"),
        INVALID_NULL("Input cannot be NULL");

        private String message;

        ValidationResult(String message) {
            this.message = message;
        }
    }
}
