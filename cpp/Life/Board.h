#pragma once

#include <vector>

class Board {

private:
	const int CELL_DEAD = 0;
	const int CELL_ALIVE = 1;
	int rows, columns;
	std::vector<std::vector<int>> cells;
	int getNextStateForCell(int i, int j);
	int calculateLivingNeighbors(int i, int j);

public:
	Board(int rows, int columns);
	~Board();
	void Evolve();
	void PrettyPrint();
	int Rows();
	int Columns();
	std::vector<std::vector<int>> State();
};