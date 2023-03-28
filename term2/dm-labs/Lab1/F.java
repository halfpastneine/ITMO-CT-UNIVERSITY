import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class F {

    static int[] associations;
    static boolean[] visited;
    static int[][] d1;
    static int[][] d2;
    static boolean[] dt1;
    static boolean[] dt2;
    static HashMap<String, Integer> h;

    static boolean dfs(int u, int v) {
        visited[u] = true;
        if (dt1[u] != dt2[v]) {
            return false;
        }
        associations[u] = v;
        boolean result = true;
        for (int i = 0; i < h.size(); i++) {
            int tmp1 = d1[u][i];
            int tmp2 = d2[v][i];
            boolean tr = false;
            boolean tr1 = false;
            for (int j = 0; j < h.size(); j++) {
                if (d1[u][j] != 0) {
                    break;
                }
                if (j == h.size() - 1) {
                    tr = true;
                }
            }
            for (int j = 0; j < h.size(); j++) {
                if (d2[v][j] != 0) {
                    break;
                }
                if (j == h.size() - 1) {
                    tr1 = true;
                }
            }
            if ((tr && !tr1) || (!tr && tr1)) {
                return false;
            }
            if (visited[tmp1]) {
                result = result && (tmp2 == associations[tmp1]);
            } else {
                result = result && dfs(tmp1, tmp2);
            }
        }
        return result;
    }


    public static void main(String[] args) throws IOException {
        FileReader rd = new FileReader("minimization.in");
        Scanner sc = new Scanner(rd);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        dt1 = new boolean[n + 1];
        for (int i = 0; i < k; i++) {
            dt1[sc.nextInt()] = true;
        }
        h = new HashMap<>(29);
        int count = 1;
        d1 = new int[n + 1][29];
        for (int i = 0; i < m; i++) {
            int tmp = sc.nextInt();
            int tmp1 = sc.nextInt();
            String ch = sc.next();
            if (!h.containsKey(ch)) {
                h.put(ch, count);
                count++;
            }
            d1[tmp][h.get(ch)] = tmp1;
        }
        int n1 = sc.nextInt();
        int m1 = sc.nextInt();
        int k1 = sc.nextInt();
        dt2 = new boolean[n1 + 1];
        for (int i = 0; i <= n1; i++) {
            dt2[i] = false;
        }
        for (int i = 0; i < k1; i++) {
            dt2[sc.nextInt()] = true;
        }
        d2 = new int[n1 + 1][29];
        for (int i = 0; i < m1; i++) {
            int tmp = sc.nextInt();
            int tmp1 = sc.nextInt();
            String ch = sc.next();
            if (!h.containsKey(ch)) {
                h.put(ch, count);
                count++;
            }
            d2[tmp][h.get(ch)] = tmp1;
        }
        associations = new int[n1 + 1];
        visited = new boolean[Math.max(n, n1) + 1];
        boolean tr = dfs(1, 1);
        FileWriter wr = new FileWriter("minimization.out");
        if (tr) {
            wr.write("YES");
        } else {
            wr.write("NO");
        }
        wr.close();
        sc.close();
    }
}