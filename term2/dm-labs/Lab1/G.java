import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class G {

    static boolean DKAisEquals(int[][] delta, int[][] delta1, int n, boolean[] dt, boolean[] dt1, HashMap<String, Integer> hm1, String s) {
        LinkedList<Integer> ll = new LinkedList<>();
        ll.add(1);
        ll.add(1);
        boolean[][] used = new boolean[1000][1000];
        while (ll.size() != 0) {
            int k1 = ll.pop();
            int k2 = ll.pop();
            if (dt[k1] != dt1[k2]) {
                return false;
            }
            used[k1][k2] = true;
            for (int i = 0; i < s.length(); i++) {
                if (!used[delta[k1][hm1.get(String.valueOf(s.charAt(i)))]][delta1[k2][hm1.get(String.valueOf(s.charAt(i)))]]) {
                    ll.add(delta[k1][hm1.get(String.valueOf(s.charAt(i)))]);
                    ll.add(delta1[k2][hm1.get(String.valueOf(s.charAt(i)))]);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {
        FileReader rd = new FileReader("minimization.in");
        Scanner sc = new Scanner(rd);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        boolean[] dt = new boolean[n + 1];
        for (int i = 0; i <= n; i++) {
            dt[i] = false;
        }
        for (int i = 0; i < k; i++) {
            dt[sc.nextInt()] = true;
        }
        HashMap<String, Integer> h = new HashMap<>(40);
        int count = 1;
        int[][] delta = new int[n + 1][40];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int tmp = sc.nextInt();
            int tmp1 = sc.nextInt();
            String ch = sc.next();
            if (!h.containsKey(ch)) {
                sb.append(ch);
                h.put(ch, count);
                count++;
            }
            delta[tmp][h.get(ch)] = tmp1;
        }
        int n1 = sc.nextInt();
        int m1 = sc.nextInt();
        int k1 = sc.nextInt();
        boolean[] dt1 = new boolean[n1 + 1];
        for (int i = 0; i <= n1; i++) {
            dt1[i] = false;
        }
        for (int i = 0; i < k1; i++) {
            dt1[sc.nextInt()] = true;
        }
        int[][] delta1 = new int[n1 + 1][40];
        for (int i = 0; i < m1; i++) {
            int tmp = sc.nextInt();
            int tmp1 = sc.nextInt();
            String ch = sc.next();
            if (!h.containsKey(ch)) {
                h.put(ch, count);
                sb.append(ch);
                count++;
            }
            delta1[tmp][h.get(ch)] = tmp1;
        }
        String q = sb.toString();
        int tmp = Math.max(n, n1);
        boolean tr = DKAisEquals(delta, delta1, tmp, dt, dt1, h, q);
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
