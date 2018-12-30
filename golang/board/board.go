package board

import (
	"errors"
	"math/rand"
	"strconv"
	"time"
)

const CellDead = 0
const CellAlive = 1

type board struct {
	state [][]int
	rows int
	columns int
}

/* Creates a new Board with the given dimensions. The dimensions, rows and columns,
must be positive integers greater than 0.
Returns a board populated with a random state. */
func NewRandomBoard(rows, columns int) (board, error) {

	if rows < 1 || columns < 1 {
		return board{}, errors.New("rows and columns must be a positive integer greater than 0")
	}

	initState := make([][]int, rows)
	for i := range initState {
		initState[i] = make([]int, columns)
	}

	rand.Seed(time.Now().UnixNano())

	// Populate random state
	for i := range initState {
		for j := range initState[i] {
			initState[i][j] = rand.Intn((1 -0 + 1) + 0)
		}
	}

	return board{state: initState, rows:rows, columns:columns}, nil
}

func NewBoard(initialState [][]int) (board, error) {

	if initialState == nil {
		return board{}, errors.New("initialState cannot be nil")
	}

	if len(initialState) < 1 || len(initialState[0]) < 1 {
		return board{}, errors.New("initialState must contain at least 1 row and 1 column")
	}

	colSize := len(initialState[0])
	for i := 0; i < len(initialState); i++ {
		if colSize != len(initialState[i]) {
			return board{}, errors.New("initialState is a jagged 2D array, initialState cannot be jagged")
		}
		for j := 0; j < len(initialState[i]); j++ {
			cellValue := initialState[i][j]
			if cellValue < 0 || cellValue > 1 {
				return board{}, errors.New("initialState may only contain values 0 or 1")
			}
		}
	}

	return board{state:initialState, rows: len(initialState), columns: len(initialState[0])}, nil
}

func (b *board) Evolve() {
	newState := make([][]int, b.rows)
	for i := range newState {
		newState[i] = make([]int, b.columns)
		for j := range newState[i] {
			newState[i][j] = nextStateForCell(b,i,j)
		}
	}
	b.state = newState
}

func (b *board) State() [][]int {
	return b.state
}

func (b *board) Rows() int {
	return b.rows
}

func (b *board) Columns() int {
	return b.columns
}

func (b *board) PrettyPrint() {
	for i := range b.state {
		for j := range b.state[i] {
			print(" " + strconv.Itoa(b.state[i][j]) + "")
		}
		println()
	}
}

func nextStateForCell(b *board, i,j int) int {

	neighborsAlive := 0
	cellValue := b.state[i][j]
	for x := -1; x <= 1; x++ {
		for y := -1; y <= 1; y++ {
			if i + x < 0 || i + x > (b.rows - 1) || y + j < 0 || y + j > (b.columns - 1) {
				continue
			}
			neighborsAlive += b.state[i + x][y + j]
		}
	}
	neighborsAlive -= cellValue

	if cellValue == CellDead && neighborsAlive == 3 {
		return CellAlive
	} else if cellValue == CellAlive && (neighborsAlive < 2 || neighborsAlive > 3) {
		return CellDead
	} else {
		return cellValue
	}
}