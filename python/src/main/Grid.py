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

    def _generate_random_grid(self):
        for i in range(Grid.DEFAULT_COLUMNS):
            for j in range(Grid.DEFAULT_ROWS):
                self._state[i][j] = random.uniform(0, 1)

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

        pass

