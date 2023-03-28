import java.util.Scanner;

public class H {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.next();
        String s2 = sc.next();
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 1; i < s1.length() + 1; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i < s2.length() + 1; i++) {
            dp[0][i] = i;
        }
        dp[0][0] = 0;
        for (int i = 1; i < s1.length() + 1; i++) {
            for (int j = 1; j < s2.length() + 1; j++) {
                if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
                    dp[i][j] = Math.min(dp[i - 1][j] + 1, Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + 1));
                } else {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        System.out.println(dp[s1.length()][s2.length()]);
    }
}
