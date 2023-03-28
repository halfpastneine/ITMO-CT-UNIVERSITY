import  java.util.*;

public class F1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean tr = false;
        boolean tr1 = false;
        boolean hp = true;
        int count = 0;
        int src = 0;
        int s = 0;
        int src1 = 0;
        int src2 = -1;
        int qwerty = 0;
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[][] ints = new int[k][n];
        Map<Integer , Integer> q = new HashMap<>();
        for (int j = 0; j < k; j++) {
            for (int i = 0; i < n; i++) {
                int qr = sc.nextInt();
                if (qr == 0) {
                    ints[j][i] = 2;
                } else ints[j][i] = qr;
            }
        }
        while (src != n && hp) {
            for (int i = 0; i < k; i++) {
                for (int w = 0; w < n; w++){
                    count = 0;
                    if ((ints[i][w] == 1)) {
                        // System.out.println(111111111);
                        for (int j = 0; j < n; j++) {
                            if (ints[i][j] == -1) {
                                count++;
                            }
                        }
                        // System.out.println(count);
                        if (count == n - 1) {
                            // System.out.println(111111111 + " " + i +" " + w +" " + ints[i][w] );
                            if (q.containsKey(w)) {
                                if (q.get(w) == 1) {
                                    continue;
                                } else {
                                    // System.out.println(11111);
                                    // System.out.println(q.get(w));
                                    tr = true;
                                    break;
                                }
                            } else {
                                q.put(w, 1);
                            }
                            // System.out.println(q.get(w));
                        }
                    }
                    if ((ints[i][w] == 2) && (src1 != src2)) {
                        for (int j = 0; j < n; j++) {
                            if (ints[i][j] == -1 || ints[i][j] == 0) {
                                count++;
                            }
                        }
                        if ((count == n - 1)) {
                            // System.out.println(222222222 + " " + i +" " + w);
                            if (q.containsKey(w)) {
                                if (q.get(w) == 0) {
                                    continue;
                                } else {
                                    // System.out.println(222222222);
                                    tr = true;
                                    break;
                                }
                            } else {
                                q.put(w, 0);
                            }
                            // System.out.println(q.get(w));
                        }
                    }
                }
            }
            for (int j = 0; j < n; j++) {
                // int cnt = 0;
                for (int i = 0; i < k; i++){
                    if (q.containsKey(j)) {
                        tr1 = true;
                        // System.out.println(i +" "+ j);
                        if ((ints[i][j] != q.get(j)) && q.get(j) == 0 && ints[i][j] == 1) {
                            ints[i][j] = 0;
                            // cnt++;
                            // System.out.println(i +" "+ j);
                            break;
                        }
                        if ((ints[i][j] == 2) && (q.get(j) == 0)) {
                            ints[i][j] = 1;
                        }
                        if ((ints[i][j] == 2) && (q.get(j) == 1)) {
                            ints[i][j] = 0;
                        }
                    }
                }
            }
            src += q.size();
            if (qwerty == q.size()) {
                hp = false;
            }
            qwerty = q.size();
            // System.out.println(11111);
            q = new HashMap<>();
        }
        // System.out.println(ints[3][1] +" "+3333);
        for (int j = 0; j < k; j++) {
            for (int i = 0; i < n; i++){
                if (ints[j][i] == -1) {
                    ints[j][i] = 0;
                }
            }
        }
        // System.out.println(ints[3][1] +" "+4444);
        for (int j = 0; j < k; j++) {
            int count1 = 0;
            for (int i = 0; i < n; i++){
                // System.out.println();
                // System.out.println(ints[j][i]);
                 if ((ints[j][i] == 0) && tr1 &&  src == n) {
                     count1++;
                     // System.out.println(count1);
                     // System.out.println(ints[j][i]);
                 }
            }
            // System.out.println(q.size());
            // System.out.println(count1 + " " + 22);
            if (count1 == n) {
                tr = true;
                // System.out.println(ints[0][0]);
            }
        }
        // System.out.println(ints[3][1] +" "+55555);
        // System.out.println(ints[0][0]);
        if (!tr) {
            System.out.println("NO");
        } else System.out.println("YES");
    }
}
