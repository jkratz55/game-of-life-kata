#!/usr/bin/env python

import unittest

from src.main.Grid import Grid

class GridTest(unittest.TestCase):

    def testSomething(self):
        self.assertEqual(1, 1)
        grid = Grid()
        grid.rows()
