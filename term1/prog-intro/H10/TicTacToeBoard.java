package game;

import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;

public class TicTacToeBoard implements Board, Position {
    public int n, m ,k;
    private static final Map<Cell, String> CELL_TO_STRING = Map.of(
            Cell.E, ".",
            Cell.X, "X",
            Cell.O, "0"
    );

    private final Cell[][] field;
    private Cell turn;


    public TicTacToeBoard(int m, int n, int k) {
        this.m = m;
        this.n = n;
        this.k = k;
//        Scanner sc = new Scanner(System.in);
//        while (m <= 0) {
//            try {
//                System.out.println("Enter number of rows:");
//                m = Integer.parseInt(sc.next());
//                if (m <= 0) {
//                    throw new NumberFormatException();
//                }
//            } catch (NumberFormatException e) {
//                System.out.println("Please, write the correct number");
//            }
//        }
//        while (n <= 0) {
//            try {
//                System.out.println("Enter number of columns:");
//                n = Integer.parseInt(sc.next());
//                if (n <= 0) {
//                    throw new NumberFormatException();
//                }
//            } catch (NumberFormatException e) {
//                System.out.println("Please, write the correct number");
//            }
//        }
//        while (true) {
//            try {
//                System.out.println("Enter number of win length:");
//                k = Integer.parseInt(sc.next());
//                if (k < 0 || (k > m && k > n)) {
//                    throw new NumberFormatException();
//                }
//                break;
//            } catch (NumberFormatException e) {
//                System.out.println("Please, write the correct number");
//            }
//        }
        this.field = new Cell[m][n];
        for (Cell[] row : field) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
    }

    @Override
    public Cell getTurn() {
        return turn;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public GameResult makeMove(Move move) {
        if (!isValid(move)) {
            return GameResult.LOOSE;
        }

        field[move.getRow()][move.getCol()] = move.getValue();
        if (checkWin(move)) {
            return GameResult.WIN;
        }

        if (checkDraw()) {
            return GameResult.DRAW;
        }

        turn = turn == Cell.X ? Cell.O : Cell.X;
        return GameResult.UNKNOWN;
    }

    private boolean checkDraw() {
        int count = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (field[r][c] == Cell.E) {
                    count++;
                }
            }
        }
        return count == 0;
    }

    private boolean checkWin(final Move move) {
        return checkRow(move) || checkCol(move) || checkLeftDiag(move) || checkRightDiag(move);
    }

    public boolean checkRow(final Move move) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (field[move.getRow()][i] == turn) {
                count++;
                if (count == k) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }

    public boolean checkCol(final Move move) {
        int count = 0;
        for (int i = 0; i < m; i++) {
            if (field[i][move.getCol()] == turn) {
                count++;
                if (count == k) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }

    public boolean checkLeftDiag(final Move move) {
        int count = 0;
        int posRow;
        int posCol;
        if (move.getRow() == 0 || move.getCol() == 0) {
            posRow = move.getRow();
            posCol = move.getCol();
        } else {
            posRow = move.getRow() - Math.min(move.getCol(), move.getRow());
            posCol = move.getCol() - Math.min(move.getCol(), move.getRow());
        }
        int y = Math.min(n - posCol, m - posRow);
        for (int i = 0; i < y; i++) {
            if (field[posRow][posCol] == turn) {
                count++;
                if (count == k) {
                    return true;
                }
            } else {
                count = 0;
            }
            posRow++;
            posCol++;
        }
        return false;
    }

    public boolean checkRightDiag(final Move move) {
        int count = 0;
        int posRow;
        int posCol;
        if (move.getRow() == 0 || move.getCol() == n - 1) {
            posRow = move.getRow();
            posCol = move.getCol();
        } else {
            posRow = move.getRow();
            posCol = move.getCol();
            while (n - 1 != posCol && posRow != 0) {
                posCol++;
                posRow--;
            }
        }
        while (posCol != -1 && posRow != m) {
            if (field[posRow][posCol] == turn) {
                count++;
                if (count == k) {
                    return true;
                }
            } else {
                count = 0;
            }
            posRow++;
            posCol--;
        }
        return false;
    }


    public boolean isValid(final Move move) {
        return 0 <= move.getRow() && move.getRow() < m
                && 0 <= move.getCol() && move.getCol() < n
                && field[move.getRow()][move.getCol()] == Cell.E
                && turn == move.getValue();
    }

    @Override
    public Cell getCell(int row, int column) {
        return field[row][column];
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(" ");
        for (int r = 1; r < n + 1; r++) {
            sb.append(r);
        }
        sb.append(System.lineSeparator());
        for (int r = 0; r < m; r++) {
            sb.append(r + 1);
            for (Cell cell : field[r]) {
                sb.append(CELL_TO_STRING.get(cell));
            }
            sb.append(System.lineSeparator());
        }
        sb.setLength(sb.length() - System.lineSeparator().length());
        return sb.toString();
    }
}
