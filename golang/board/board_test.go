package board

import "testing"

var InitialState = [][]int {
	{1, 1, 1, 1, 0, 1, 1, 0, 0, 0},
	{1, 1, 1, 1, 0, 1, 1, 0, 0, 0},
	{0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
	{0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	{0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
	{0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	{0, 1, 0, 0, 0, 0, 0, 0, 1, 1},
	{1, 1, 0, 0, 0, 0, 0, 0, 1, 1},
}

var ExpectedState = [][]int {
	{1, 0, 0, 1, 0, 1, 1, 0, 0, 0},
	{1, 0, 0, 0, 0, 1, 1, 0, 0, 0},
	{1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	{0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
	{0, 0, 0, 0, 0, 1, 1, 0, 0, 0},
	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	{1, 1, 0, 0, 0, 0, 0, 0, 1, 1},
	{1, 1, 0, 0, 0, 0, 0, 0, 1, 1},
}

func TestNewRandomBoard(t *testing.T) {
	b, err := NewRandomBoard(5 ,5)
	if err != nil {
		t.Errorf(err.Error())
	}

	if b.columns != 5 {
		t.Errorf("Expected %d columns, actual %d", 5, b.columns)
	}

	if b.rows != 5 {
		t.Errorf("Expected %d columns, actual %d", 5, b.rows)
	}
}

func TestNewRandomBoard_InvalidDimensions(t *testing.T) {
	b, err := NewRandomBoard(5, 0)
	if err == nil {
		t.Error("Should have returned error")
	}
	b.State()
}

func TestNewBoard(t *testing.T) {

	b, err := NewBoard(InitialState)
	if err != nil {
		t.Error(err.Error())
	} else {
		if b.rows != 10 {
			t.Errorf("Expected 10 rows, got %d rows", b.rows)
		}
		if b.columns != 10 {
			t.Errorf("Expected 10 columns, got %d columns", b.columns)
		}

		for i := range b.state {
			for j := range b.state[i] {
				if b.state[i][j] != InitialState[i][j] {
					t.Errorf("Expected %d for Row %d Column %d, got %d", InitialState[i][j], i, j, b.state[i][j])
				}
			}
		}
	}
}

func TestBoard_Evolve(t *testing.T) {

	b, err := NewBoard(InitialState)
	if err != nil {
		t.Error(err.Error())
	} else {
		b.Evolve()
		for i := range b.state {
			for j := range b.state[i] {
				if b.state[i][j] != ExpectedState[i][j] {
					t.Errorf("Expected %d for Row %d Column %d, got %d", ExpectedState[i][j], i, j, b.state[i][j])
				}
			}
		}
	}
}