import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class B {

    static int n, s, m;
    static int[] WL, degree, count;
    static HashMap<Integer, ArrayList<Integer>> reversedGraph = new HashMap<>();

    private static void read() {
        Scanner sc = new Scanner(System.in);
        s = sc.nextInt();
        for (int j = 0; j < s; j++) {
            n = sc.nextInt();
            m = sc.nextInt();
            WL = new int[n + 1];
            degree = new int[n + 1];
            count = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                reversedGraph.put(i, new ArrayList<>());
                WL[i] = -1;
            }
            for (int i = 0; i < m; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                reversedGraph.get(b).add(a);
                degree[a]++;
            }
            startDFS();
        }
    }

    private static void findWhoWillWin(int top, int wl) {
        for (Integer integer : reversedGraph.get(top)) {
            if (WL[integer] == -1 && wl == 1) {
                WL[integer] = 1;
                findWhoWillWin(integer, 0);
            }
            if (WL[integer] == -1 && wl == 0) {
                count[integer]++;
                if (count[integer] == degree[integer]) {
                    WL[integer] = wl;
                    findWhoWillWin(integer, 1);
                }
            }
        }
    }

    private static void startDFS() {
        for (int i = 1; i <= n; i++) {
            if (degree[i] == 0) {
                WL[i] = 0;
                findWhoWillWin(i, 1);
            }
        }
        for (int i = 1; i <= n; i++) {
            if (WL[i] == 1) {
                System.out.println("FIRST");
            } else if (WL[i] == 0) {
                System.out.println("SECOND");
            } else {
                System.out.println("DRAW");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        read();
    }
}
