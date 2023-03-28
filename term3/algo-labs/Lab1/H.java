import java.util.Arrays;
import java.util.Scanner;

public class H {

    static int[][] graph;
    static int n;
    static int r = 0;
    static int l = -1;
    static int weight;
    static boolean[] used1;
    static boolean[] used2;

    static boolean tr(boolean[] u) {
        for (Boolean t : u) {
            if (!t) {
                return false;
            }
        }
        return true;
    }


    static void bin_search() {
        while (l < r - 1) {
            int mid = (l + r) / 2;
            weight = mid;
            dfs1(0);
            dfs2(0);
            if (tr(used1) && tr(used2)) {
                r = mid;
            } else {
                l = mid;
            }
            Arrays.fill(used1, false);
            Arrays.fill(used2, false);
        }
        System.out.println(r);
    }

    private static void dfs1(int vertex) {
        used1[vertex] = true;
        for (int i = 0; i < n; i++) {
            if (graph[vertex][i] <= weight) {
                if (!used1[i])
                    dfs1(i);
            }
        }
    }

    private static void dfs2(int vertex) {
        used2[vertex] = true;
        for (int i = 0; i < n; i++) {
            if (graph[i][vertex] <= weight) {
                if (!used2[i])
                    dfs2(i);
            }
        }
    }


    static void read() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        used1 = new boolean[n];
        graph = new int[n][n];
        used2 = new boolean[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = sc.nextInt();
                if (r < graph[i][j]) {
                    r = graph[i][j];
                }
            }
        }
    }

    public static void main(String[] args) {
        read();
        bin_search();
    }
}
