// Life.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#include <iostream>
#include "Board.h"

int main()
{
	Board board = Board(10, 10);
	std::cout << "State 0 \n";
	board.PrettyPrint();
	std::cout << "\n";
	std::cout << "State 1 \n";
	board.Evolve();
	board.PrettyPrint();
	return 0;
}
