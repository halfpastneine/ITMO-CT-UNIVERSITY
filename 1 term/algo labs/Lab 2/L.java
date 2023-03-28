import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class L {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] num = new int[n];
        num[0] = sc.nextInt();
        int[] dp = new int[n];
        dp[0] = 1;
        int k = sc.nextInt();
        int b = sc.nextInt();
        int mz = sc.nextInt();
        for (int i = 1; i < n; i++) {
            num[i] = (k * num[i - 1] + b) % mz;
            dp[i] = 1;
        }
        int m = 0;
        for (int j = 1; j < n; j++) {
            for (int i = 0; i < j; i++) {
                if (num[j] > num[i]) {
                    if (dp[j] <= dp[i]) {
                        dp[j] = dp[i] + 1;
                    }
                    if (m < dp[j]) {
                        m = dp[j];
                    }
                }
            }
        }
        System.out.println(m);
    }
}
