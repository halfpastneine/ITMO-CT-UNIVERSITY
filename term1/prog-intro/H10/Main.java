package game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final int count;
        int m = 0;
        int n = 0;
        int k = 0;
        boolean tr;
        int wcp1 = 0;
        int wcp2 = 0;
        int dr = 0;
        Player f;
        Player s;
        WinCount winCount = new WinCount();
        count = winCount.getCnt();
        Scanner sc = new Scanner(System.in);
        while (m <= 0) {
            try {
                System.out.println("Enter number of rows:");
                m = Integer.parseInt(sc.next());
                if (m <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Please, write the correct number");
            }
        }
        while (n <= 0) {
            try {
                System.out.println("Enter number of columns:");
                n = Integer.parseInt(sc.next());
                if (n <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Please, write the correct number");
            }
        }
        while (true) {
            try {
                System.out.println("Enter number of win length:");
                k = Integer.parseInt(sc.next());
                if (k < 0 || (k > m && k > n)) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please, write the correct number");
            }
        }
        System.out.println("Human or robot:");
        String str1 = sc.next();
        if (str1.equals("human")) {
            f = new HumanPlayer(new Scanner(System.in));
        } else {
            f = new RandomPlayer(m, n);
        }
        System.out.println("Human or robot:");
        String str2 = sc.next();
        if (str2.equals("human")) {
            s = new HumanPlayer(new Scanner(System.in));
        } else {
            s = new RandomPlayer(m, n);
        }
        while (wcp1 != count && wcp2 != count) {
            final int result;
            result = new TwoPlayerGame(
                    new TicTacToeBoard(m, n, k),
                    f, s
//                new RandomPlayer(),
//                new RandomPlayer()
//                new CheatingPlayer()
//                    new HumanPlayer(new Scanner(System.in)),
//                    new HumanPlayer(new Scanner(System.in))
            ).play(true);
            tr = (wcp1 + wcp2 + dr) % 2 == 0;
            switch (result) {
                case 1:
                    if (tr) {
                        wcp1++;
                        System.out.println("Player A won " + wcp1 + " games of " + count);
                    } else {
                        wcp2++;
                        System.out.println("Player B won " + wcp2 + " games of " + count);
                    }
                    break;
                case 2:
                    if (tr) {
                        wcp2++;
                        System.out.println("Player B won " + wcp2 + " games of " + count);
                    } else {
                        wcp1++;
                        System.out.println("Player A won " + wcp1 + " games of " + count);
                    }
                    break;
                case 0:
                    dr++;
                    System.out.println("Draw");
                    break;
                default:
                    throw new AssertionError("Unknown result " + result);
            }

        }
    }
}
