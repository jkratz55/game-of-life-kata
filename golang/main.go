package main

import (
	"fmt"
	"io.jkratz/katas/life/board"
	"log"
)

func main() {

	myBoard, err := board.NewRandomBoard(10, 10)
	if err != nil {
		log.Fatalf("Failed to instantiate board: %s", err)
	}

	fmt.Println("State 0")
	myBoard.PrettyPrint()
	fmt.Println("State 1")
	myBoard.Evolve()
	myBoard.PrettyPrint()
}