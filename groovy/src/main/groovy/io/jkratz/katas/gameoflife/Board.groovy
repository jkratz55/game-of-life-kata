package io.jkratz.katas.gameoflife

class Board {

    static int CELL_DEAD = 0
    static int CELL_ALIVE = 1

    private int[][] state
    final int rows
    final int columns

    Board(int rows, int columns) {
        this.state = new int[rows][columns]
        for (int i=0; i<rows; i++) {
            for (int j=0; j<columns; j++) {
                this.state[i][j] = Math.round(Math.random()).toInteger()
            }
        }
        this.rows = rows
        this.columns = columns
    }

    Board(int[][] initialState) {
        ValidationResult validationResult = validate(initialState);
        switch (validationResult) {
            case ValidationResult.INVALID_JAGGED:
            case ValidationResult.INVALID_NULL:
            case ValidationResult.INVALID_BAD_SIZE:
            case ValidationResult.INVALID_BAD_VALUES:
                throw new IllegalArgumentException(validationResult.message);
            default:
                this.state = initialState
                this.rows = this.state.length
                this.columns = this.state[0].length
        }
    }

    int[][] getState() {
        return this.state
    }

    void evolve() {
        int[][] nextState = new int[this.columns][this.rows]
        for (int i=0; i<rows; i++) {
            for(int j=0; j<columns; j++) {
                nextState[i][j] = this.getNextStateForCell(i,j)
            }
        }
        this.state = nextState
    }

    private int getNextStateForCell(int i, int j) {

        int liveCount = 0
        int cellValue = this.state[i][j]
        for (int x=-1; x<=1; x++) {
            for (int y=-1; y<=1; y++) {
                if (i + x < 0 || i + x > (this.rows - 1) || y + j < 0 || y + j > (this.columns - 1)) {
                    continue
                }
                liveCount += this.state[i + x][y + j]
            }
        }

        // remove since we may have counted ourselves
        liveCount -= cellValue

        if (cellValue == CELL_ALIVE && liveCount > 3) {
            return CELL_DEAD
        } else if (cellValue == CELL_ALIVE && liveCount < 2) {
            return CELL_DEAD
        } else if (cellValue == CELL_DEAD && liveCount == 3) {
            return CELL_ALIVE
        } else {
            return cellValue
        }
    }

    /**
     * Validates the state of the Board.
     *
     * @param state The state of the board
     * @return The result of the validation
     */
    ValidationResult validate(int[][] state) {

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
    String toString() {
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

        private String message

        ValidationResult(String message) {
            this.message = message
        }
    }
}
