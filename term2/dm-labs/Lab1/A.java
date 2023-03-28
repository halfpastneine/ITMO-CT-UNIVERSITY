import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class A {
    public static void main(String[] args) throws IOException {
        FileReader rd = new FileReader("minimization.in");
        Scanner sc = new Scanner(rd);
        String str = sc.next();
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
        for (int i = 0; i < m; i++) {
            int tmp = sc.nextInt();
            int tmp1 = sc.nextInt();
            String ch = sc.next();
            if (!h.containsKey(ch)) {
                h.put(ch, count);
                count++;
            }
            delta[tmp][h.get(ch)] = tmp1;
        }
        int cur = 1;
        FileWriter wr = new FileWriter("minimization.out");
        boolean tr = true;
        for (int i = 0; i < str.length(); i++) {
            String s = String.valueOf(str.charAt(i));
            if (!h.containsKey(s)) {
                wr.write("Rejects");
                tr = false;
                break;
            }
            cur = delta[cur][h.get(s)];
        }
        if (tr) {
            if (dt[cur])
                wr.write("Accepts");
            else
                wr.write("Rejects");
        }
        wr.close();
        sc.close();
    }
}
