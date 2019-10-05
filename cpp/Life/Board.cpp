#include "Board.h"
#include <iostream>
#include <stdexcept>
#include <stdlib.h>
#include <time.h>

Board::Board(int rows, int columns) {
	// If the rows and columns are not valid raise exception
	if (rows <= 0 || columns <= 0) {
		throw std::invalid_argument("dimensions of the board must be larger than 0");
	}
	this->rows = rows;
	this->columns = columns;

	// Seed for random number generation
	srand(time(NULL));

	// Build the board with random values
	for (int i = 0; i < rows; i++) {
		std::vector<int> col;
		for (int j = 0; j < rows; j++) {
			col.push_back(rand() % 2);
		}
		this->cells.push_back(col);
	}
}

Board::~Board() {
	
}

int Board::Rows() {
	return this->rows;
}

int Board::Columns() {
	return this->columns;
}

std::vector<std::vector<int>> Board::State() {
	return this->cells;
}

void Board::Evolve() {

}

void Board::PrettyPrint() {

}