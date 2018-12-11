#!/usr/bin/env python

from Grid import Grid


def main():

    grid = Grid()
    start_state = grid.state
    grid.next_state()
    next_state = grid.state

    print("Current State")
    print_grid(start_state)

    print("Next State")
    print_grid(next_state)


def print_grid(grid):

    for columns in grid:
        for value in columns:
            if value == 0:
                print(" . ", end="")
            else:
                print(" * ", end="")
        print()


if __name__ == '__main__':
    main()
