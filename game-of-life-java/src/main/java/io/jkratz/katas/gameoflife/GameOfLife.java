package io.jkratz.katas.gameoflife;

public class GameOfLife {

    public static void main(String[] args) {
        final Grid grid = new Grid();
        int[][] current = grid.getState();
        grid.nextState();
        int[][] nextState = grid.getState();

        System.out.println("Current Generation");
        printGrid(current);
        System.out.println("Next Generation");
        printGrid(nextState);
    }

    // TODO: This could be placed in a better location
    private static void printGrid(int[][] grid) {
        for (int[] columns : grid) {
            for (int value : columns) {
                if (value == 0) {
                    System.out.print(" . ");
                } else {
                    System.out.print(" * ");
                }
            }
            System.out.println();
        }
    }
}
