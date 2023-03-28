import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class K {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] num = new int[n];
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = sc.nextInt();
            dp[i] = 1;
        }
        int m = 0;
        int[] p = new int[n];
        for (int j = 1; j < n; j++) {
            p[j] = -1;
            for (int i = 0; i < j; i++) {
                if (num[j] > num[i]) {
                    if (dp[j] <= dp[i]) {
                        dp[j] = dp[i] + 1;
                        p[j] = i;
                    }
                    if (m < dp[j]) {
                        m = dp[j];
                    }
                }
            }
        }
        int pos = 0;
        int length = dp[0];
        for (int i = 0; i < n; i++) {
            if (dp[i] > length) {
                pos = i;
                length = dp[i];
            }
        }
        p[0] = -1;
        ArrayList<Integer> ans = new ArrayList<>();
        while (pos != -1) {
            ans.add(num[pos]);
            pos = p[pos];
        }
        Collections.reverse(ans);
        if (m == 0) {
            m = 1;
        }
        System.out.println(m);
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i) + " ");
        }
    }
}
