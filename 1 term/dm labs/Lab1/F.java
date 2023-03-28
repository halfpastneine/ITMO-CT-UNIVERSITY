import  java.util.*;

public class F {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean tr = false;
        int count = 0;
        int count1 = 0;
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[][] ints = new int[k][n];
        for (int i = 0; i < k; i++) {
            count1 = 0;
            for (int j = 0; j < n; j++) {
                int q = sc.nextInt();
                ints[i][j] = q;
                if (q == 1 || q == 0) {
                    count1++;
                }
            }
            if (count1 != 1) {
                for (int l = 0; l < n; l++) {
                    ints[i][l] = 1000;
                }
            }
        }
        for (int i = 0; i < k; i++) {
            for (int j = 1; j < k; j++) {
                for (int w = 0; w < n; w++){
                    if (ints[i][w] == 1 && ints[j][w] == 0) {
                        tr = true;
                        break;
                    }
                }
            }
        }
        if (!tr) {
            System.out.println("NO");
        } else System.out.println("YES");
    }
}
