import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class M {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        boolean tr = false;
        ArrayList<Integer> num = new ArrayList<>();
        num.add(0);
        for (int i = 0; i < n; i++) {
            num.add(sc.nextInt());
        }
//        Collections.sort(num);
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i < k + 1; i++) {
            dp[0][i] = 0;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < k + 1; j++) {
                if (j >= num.get(i)) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - num.get(i)] + num.get(i));
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        for (int i = 0; i < k + 1; i++) {
            if (dp[n][i] == k) {
                tr = true;
                break;
            }
        }
        if (tr) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
