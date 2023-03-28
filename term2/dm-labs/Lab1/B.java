import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class B {
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
        ArrayList<Integer>[][] delta = new ArrayList[n + 1][30];
        HashMap<String, Integer> h = new HashMap<>(29);
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < 28; j++) {
                delta[i][j] = new ArrayList<>();
            }
        }
        int count = 1;
        for (int i = 0; i < m; i++) {
            int tmp = sc.nextInt();
            int tmp1 = sc.nextInt();
            String ch = sc.next();
            if (!h.containsKey(ch)) {
                h.put(ch, count);
                count++;
            }
            delta[tmp][h.get(ch)].add(tmp1);
        }
            boolean[][] can = new boolean[str.length() + 1][n + 1];
            can[0][1] = true;
        boolean r = false;
        FileWriter wr = new FileWriter("minimization.out");
        for (int i = 0; i < str.length(); i++) {
            if (!h.containsKey(String.valueOf(str.charAt(i)))) {
                wr.write("Rejects");
                r = true;
                break;
            }
            for (int q = 0; q <= n; q++) {
                if (can[i][q]) {
                    for (int z = 0; z < delta[q][h.get(String.valueOf(str.charAt(i)))].size(); z++) {
                        can[i + 1][delta[q][h.get(String.valueOf(str.charAt(i)))].get(z)] = true;
                    }
                }
            }
        }
        for (int j = 0; j < can.length; j++) {
            System.out.println(Arrays.toString(can[j]));
        }
        boolean tr = false;
        for (int q = 0; q <= n; q++) {
            if (can[str.length()][q] && dt[q]) {
                tr = true;
                break;
            }
        }
        if (!r) {
            if (tr) {
                wr.write("Accepts");
            } else
                wr.write("Rejects");
        }
        wr.close();
        sc.close();
    }
}

