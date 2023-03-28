import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class A {

    static int n, s, m;
    static int[] WL, degree, count;
    static HashMap<Integer, ArrayList<Integer>> reversedGraph = new HashMap<>();

    private static void read() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        s = sc.nextInt();
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
        System.out.println(Arrays.toString(WL));
    }

    private static void startDFS() {
        for (int i = 1; i <= n; i++) {
            if (degree[i] == 0) {
                WL[i] = 0;
                findWhoWillWin(i, 1);
            }
        }
        if (WL[s] == 1) {
            System.out.println("First player wins");
        } else {
            System.out.println("Second player wins");
        }
    }

    public static void main(String[] args) {
        read();
        startDFS();
    }
}
