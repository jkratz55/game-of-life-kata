#include "Board.h"
#include <iostream>
#include <stdexcept>
#include <stdlib.h>
#include <time.h>
#include <string>
#include <sstream>

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
	std::vector<std::vector<int>> next(this->rows, std::vector<int>(this->columns, 0));

	for (int i = 0; i < this->cells.size(); i++) {
		for (int j = 0; j < this->cells[i].size(); j++) {
			next[i][j] = getNextStateForCell(i, j);
		}
	}
	this->cells = next;
}

void Board::PrettyPrint() {
	std::ostringstream out;
	for (std::vector<std::vector<int>>::iterator it = this->cells.begin(); it != this->cells.end(); ++it) {
		for (std::vector<int>::iterator jt = it->begin(); jt != it->end(); ++jt) {
			out << " " << *jt;
		}
		out << "\n";
	}
	std::cout << out.str();
}

int Board::getNextStateForCell(int i, int j) {
	int alive = this->calculateLivingNeighbors(i, j);
	int cellValue = this->cells[i][j];
	int newValue = 0;

	if (cellValue == CELL_DEAD && alive == 3) {
		newValue = CELL_ALIVE;
	}
	else if (cellValue == CELL_ALIVE && (alive < 2 || alive > 3)) {
		newValue == CELL_DEAD;
	}
	else {
		newValue = cellValue;
	}
	return newValue;
}

int Board::calculateLivingNeighbors(int i, int j) {
	int liveCount = 0;
	for (int x = -1; x <= 1; x++) {
		for (int y = -1; y <= 1; y++) {
			// check for boundary conditions
			if (i + x < 0 || i + x >(this->rows - 1) || y + j < 0 || y + j >(this->columns - 1)) {
				continue;
			}
			liveCount += this->cells[i + x][y + j];
		}
	}

	// remove since we may have counted ourselves
	liveCount -= this->cells[i][j];
	return liveCount;
}