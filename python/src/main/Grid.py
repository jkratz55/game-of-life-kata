#!/usr/bin/env python

import random


def is_jagged(grid):
    jagged = False
    length = 0
    for column in grid:
        if length == 0:
            length = len(column)
            continue

        if length != len(column):
            jagged = True
            break

    return jagged


class Grid:

    DEFAULT_COLUMNS = 10
    DEFAULT_ROWS = 10
    CELL_ALIVE = 1
    CELL_DEAD = 0

    def __init__(self, initial_state=None):

        self._state = []
        self._columns = 0
        self._rows = 0

        if initial_state is None:
            self._generate_random_grid()
            self._columns = Grid.DEFAULT_COLUMNS
            self._rows = Grid.DEFAULT_ROWS
        else:
            if is_jagged(initial_state):
                raise ValueError("initial_state cannot be jagged")
            self._state = initial_state
            self._columns = len(self._state)
            self._rows = len(self._state[0])

    def _generate_random_grid(self):
        self._state = [[0 for x in range(Grid.DEFAULT_COLUMNS)] for y in range(Grid.DEFAULT_ROWS)]
        for i in range(Grid.DEFAULT_COLUMNS):
            for j in range(Grid.DEFAULT_ROWS):
                self._state[i][j] = random.randint(0, 1)

    @property
    def state(self):
        return self._state

    @property
    def columns(self):
        return self._columns

    @property
    def rows(self):
        return self._rows

    def next_state(self):
        new_state = [[0 for x in range(self._columns)] for y in range(self._rows)]
        for i in range(len(self._state)):
            for j in range(len(self._state[i])):
                living_neighbors = self._calculate_living_neighbors(i, j)
                cell_value = self._state[i][j]

                if cell_value == 1 and living_neighbors < 2:
                    new_state[i][j] = Grid.CELL_DEAD
                elif cell_value == 1 and living_neighbors > 3:
                    new_state[i][j] = Grid.CELL_DEAD
                elif cell_value == 0 and living_neighbors == 3:
                    new_state[i][j] = Grid.CELL_ALIVE
                else:
                    new_state[i][j] = cell_value

        self._state = new_state

    def _calculate_living_neighbors(self, i, j):
        live_count = 0
        for x in range(-1, 1, 1):
            for y in range(-1, 1, 1):
                if i + x < 0 or i + x > (self._rows - 1) or y + j < 0 or y + j > (self._columns - 1):
                    continue
                live_count += self._state[i + x][y + j]

        live_count -= self._state[i][j]
        return live_count