#!/usr/bin/env python

import unittest

from src.main.Grid import Grid


class GridTest(unittest.TestCase):
    initialstate = [
        [0, 0, 0, 0, 0, 1, 1, 0, 0, 0],
        [0, 0, 0, 0, 0, 1, 1, 0, 0, 0],
        [1, 1, 0, 0, 0, 0, 0, 0, 0, 0],
        [1, 1, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 1, 1, 0, 0, 0],
        [0, 0, 0, 0, 0, 1, 1, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    ]

    def test_constructor(self):
        grid = Grid()
        self.assertEqual(10, grid.columns)
        self.assertEqual(10, grid.rows)

    def test_constructor_with_initialstate(self):
        grid = Grid(GridTest.initialstate)
        self.assertEqual(10, grid.columns)
        self.assertEqual(10, grid.rows)

    def test_constructor_with_jagged(self):
        jagged_grid = [
            [0, 0, 0, 0, 0, 1, 1, 0, 0, 0],
            [0, 0, 0, 0, 0, 1, 1, 0, 0, 0],
            [1, 1, 0, 0, 0, 0, 0, 0, 0, 0],
            [1, 1, 0, 0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 1],
            [0, 0, 0, 0, 0, 1, 1, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
            [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
        ]

        self.assertRaises(ValueError, Grid, jagged_grid)

    def test_next_state(self):
        grid = Grid(GridTest.initialstate)
        grid.next_state()
        results = grid.state

        self.assertEqual(results[0][0], 0)
        self.assertEqual(results[0][1], 0)
        self.assertEqual(results[0][2], 0)
        self.assertEqual(results[0][3], 0)
        self.assertEqual(results[0][4], 0)
        self.assertEqual(results[0][5], 1)
        self.assertEqual(results[0][6], 1)
        self.assertEqual(results[0][7], 0)
        self.assertEqual(results[0][8], 0)
        self.assertEqual(results[0][9], 0)

        self.assertEqual(results[1][0], 0)
        self.assertEqual(results[1][1], 0)
        self.assertEqual(results[1][2], 0)
        self.assertEqual(results[1][3], 0)
        self.assertEqual(results[1][4], 0)
        self.assertEqual(results[1][5], 1)
        self.assertEqual(results[1][6], 1)
        self.assertEqual(results[1][7], 0)
        self.assertEqual(results[1][8], 0)
        self.assertEqual(results[1][9], 0)

        self.assertEqual(results[2][0], 1)
        self.assertEqual(results[2][1], 1)
        self.assertEqual(results[2][2], 0)
        self.assertEqual(results[2][3], 0)
        self.assertEqual(results[2][4], 0)
        self.assertEqual(results[2][5], 0)
        self.assertEqual(results[2][6], 0)
        self.assertEqual(results[2][7], 0)
        self.assertEqual(results[2][8], 0)
        self.assertEqual(results[2][9], 0)

        self.assertEqual(results[3][0], 1)
        self.assertEqual(results[3][1], 1)
        self.assertEqual(results[3][2], 0)
        self.assertEqual(results[3][3], 0)
        self.assertEqual(results[3][4], 0)
        self.assertEqual(results[3][5], 0)
        self.assertEqual(results[3][6], 0)
        self.assertEqual(results[3][7], 0)
        self.assertEqual(results[3][8], 0)
        self.assertEqual(results[3][9], 0)

        self.assertEqual(results[4][0], 0)
        self.assertEqual(results[4][1], 0)
        self.assertEqual(results[4][2], 0)
        self.assertEqual(results[4][3], 0)
        self.assertEqual(results[4][4], 0)
        self.assertEqual(results[4][5], 0)
        self.assertEqual(results[4][6], 0)
        self.assertEqual(results[4][7], 0)
        self.assertEqual(results[4][8], 0)
        self.assertEqual(results[4][9], 0)

        self.assertEqual(results[5][0], 0)
        self.assertEqual(results[5][1], 0)
        self.assertEqual(results[5][2], 0)
        self.assertEqual(results[5][3], 0)
        self.assertEqual(results[5][4], 0)
        self.assertEqual(results[5][5], 1)
        self.assertEqual(results[5][6], 1)
        self.assertEqual(results[5][7], 0)
        self.assertEqual(results[5][8], 0)
        self.assertEqual(results[5][9], 0)

        self.assertEqual(results[6][0], 0)
        self.assertEqual(results[6][1], 0)
        self.assertEqual(results[6][2], 0)
        self.assertEqual(results[6][3], 0)
        self.assertEqual(results[6][4], 0)
        self.assertEqual(results[6][5], 1)
        self.assertEqual(results[6][6], 1)
        self.assertEqual(results[6][7], 0)
        self.assertEqual(results[6][8], 0)
        self.assertEqual(results[6][9], 0)

        self.assertEqual(results[7][0], 0)
        self.assertEqual(results[7][1], 0)
        self.assertEqual(results[7][2], 0)
        self.assertEqual(results[7][3], 0)
        self.assertEqual(results[7][4], 0)
        self.assertEqual(results[7][5], 0)
        self.assertEqual(results[7][6], 0)
        self.assertEqual(results[7][7], 0)
        self.assertEqual(results[7][8], 0)
        self.assertEqual(results[7][9], 0)

        self.assertEqual(results[8][0], 0)
        self.assertEqual(results[8][1], 0)
        self.assertEqual(results[8][2], 0)
        self.assertEqual(results[8][3], 0)
        self.assertEqual(results[8][4], 0)
        self.assertEqual(results[8][5], 0)
        self.assertEqual(results[8][6], 0)
        self.assertEqual(results[8][7], 0)
        self.assertEqual(results[8][8], 0)
        self.assertEqual(results[8][9], 0)

        self.assertEqual(results[9][0], 0)
        self.assertEqual(results[9][1], 0)
        self.assertEqual(results[9][2], 0)
        self.assertEqual(results[9][3], 0)
        self.assertEqual(results[9][4], 0)
        self.assertEqual(results[9][5], 0)
        self.assertEqual(results[9][6], 0)
        self.assertEqual(results[9][7], 0)
        self.assertEqual(results[9][8], 0)
        self.assertEqual(results[9][9], 0)
