package main

import (
	"io.jkratz/katas/life/board"
)

func main() {

	myBoard, err := board.NewRandomBoard(10, 10)
	if err != nil {
		panic("Failed to instantiate board")
	}

	myBoard.PrettyPrint()
	println()
	myBoard.Evolve()
	myBoard.PrettyPrint()
}