package game;

import java.util.Scanner;

public class WinCount {
    public int cnt;
    public WinCount() {
        Scanner sc = new Scanner(System.in);
        while (cnt <= 0) {
            try {
                System.out.println("Write a number, u need to win:");
                cnt = Integer.parseInt(sc.next());
                if (cnt <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Please, write the correct number");
            }
        }
    }

    public int getCnt() {
        return cnt;
    }
}

