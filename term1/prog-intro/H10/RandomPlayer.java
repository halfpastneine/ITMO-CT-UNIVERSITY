package game;

import java.util.Random;

public class RandomPlayer implements Player {
    private final Random random = new Random();

    int m;
    int n;

    public RandomPlayer(int m, int n) {
        this.m = m;
        this.n = n;
    }

    @Override
    public Move makeMove(Position position) {
        final Move move = new Move(
                random.nextInt(m),
                random.nextInt(n),
                position.getTurn()
        );
            return move;
    }
}
