package game;

import java.util.Scanner;

public class HumanPlayer implements Player {
    private final Scanner in;
    private int row, col;
    public HumanPlayer(Scanner in) {
        this.in = in;
    }

    @Override
    public Move makeMove(Position position) {
        System.out.println();
        System.out.println("Current position");
        System.out.println(position);
        System.out.println("Enter you move for " + position.getTurn());
        while (true) {
            try {
                row = Integer.parseInt(in.next());
                col = Integer.parseInt(in.next());
                Move move = new Move(row - 1, col - 1, position.getTurn());
                if (!position.isValid(move)) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Wrong Number, please, try again:");
            }
        }
        return new Move(row - 1, col - 1, position.getTurn());
    }
}
