package io.jkratz.katas.gameoflife;

public class Life {

    public static void main(String[] args) {
        final Board board = new Board(10, 10);
        System.out.println("State 0");
        System.out.println(board.toString());
        board.evolve();
        System.out.println("State 1");
        System.out.println(board.toString());
    }
}
