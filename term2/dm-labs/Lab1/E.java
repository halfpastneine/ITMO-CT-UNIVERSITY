import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class E {

    static void print(long[][] matrix, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static long[][] matrixPow(long[][] matrix, long[][] matrix1, int n) {
        long[][] mat = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    mat[i][j] += matrix[i][k] * matrix1[k][j];
                    mat[i][j] = (mat[i][j] % 1000000007);
                }
            }
        }
        return mat;
    }

    public static void main(String[] args) throws IOException {
        FileReader rd = new FileReader("minimization.in");
        Scanner sc = new Scanner(rd);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int l = sc.nextInt();
        HashSet<Integer> jk = new HashSet<>();
        for (int i = 0; i < k; i++) {
            jk.add(sc.nextInt());
        }
        HashSet<Integer>[][] delta = new HashSet[n + 1][30];
        HashMap<String, Integer> h = new HashMap<>(29);
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < 28; j++) {
                delta[i][j] = new HashSet<>();
            }
        }
        int count = 0;
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
        LinkedList<ArrayList<Integer>> ll = new LinkedList<>();
        HashMap<ArrayList, Integer> map = new HashMap<>();
        ArrayList<Integer> q1 = new ArrayList<>();
        ArrayList<ArrayList<Integer>> dfa1 = new ArrayList<>();
        ArrayList<ArrayList<ArrayList<Integer>>> dfa2 = new ArrayList<>();
        q1.add(1);
        map.put(q1, 0);
        ll.add(q1);
        int cc = 1;
        HashSet<Integer> jjk = new HashSet<>();
        dfa1.add(q1);
        while (ll.size() != 0) {
            ArrayList<Integer> a = ll.pop();
            ArrayList<ArrayList<Integer>> dd = new ArrayList<>();
            for (int i = 0; i < h.size(); i++) {
                ArrayList<Integer> d = new ArrayList<>();
                for (Integer value : a) {
                    HashSet<Integer> ad = delta[value][i];
                    for (Integer integer : ad) {
                        if (!d.contains(integer)) {
                            d.add(integer);
                        }
                    }
                }
                Collections.sort(d);
                if (!dfa1.contains(d)) {
                    ll.add(d);
                    dfa1.add(d);
                    map.put(d, cc);
                    cc++;
                }
                dd.add(d);
            }
            dfa2.add(dd);
        }

        for (int i = 0; i < dfa1.size(); i++) {
            for (int j = 0; j < dfa1.get(i).size(); j++) {
                if (jk.contains(dfa1.get(i).get(j))) {
                    jjk.add(i);
                }
            }
        }
        long[][] ans = new long[map.size()][map.size()];
        for (int i = 0; i < dfa1.size(); i++) {
            for (int j = 0; j < dfa2.get(i).size(); j++) {
                ans[map.get(dfa1.get(i))][map.get(dfa2.get(i).get(j))]++;            }
        }

        long[][] ans1 = new long[map.size()][map.size()];

        for (int i = 0; i < map.size(); i++) {
            ans1[i][i] = 1;
        }


        while (l > 0) {
            if (l % 2 == 1) {
                ans1 = matrixPow(ans1, ans, map.size());
            }
            ans = matrixPow(ans, ans, map.size());
            l /= 2;
        }


        long sum = 0;
        for (int i = 0; i < map.size(); i++) {
            if (jjk.contains(i)) {
                sum += ans1[0][i];
            }
            sum = (sum % 1000000007);
        }
        FileWriter wr = new FileWriter("minimization.out");
        wr.write(String.valueOf(sum));
        wr.close();
        sc.close();
    }
}