import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class F {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new FileInputStream("knight.in"));
            BufferedWriter buffer1 = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream("knight.out"),
                            StandardCharsets.UTF_8
                    )
            );
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] dp = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    dp[i][j] = -1;
                }
            }
            dp[0][0] = 1;
            buffer1.write(String.valueOf(ans(n - 1, m - 1, n, m, dp)));
            buffer1.close();
        } catch (IOException ignored) {
        }
    }

    public static int ans(int pos1, int pos2, int n, int m, int[][] dp) {
        if (tr(pos1, pos2, n, m)) {
            if (dp[pos1][pos2] == -1) {
                dp[pos1][pos2] = ans(pos1 - 2, pos2 - 1, n, m, dp) + ans(pos1 - 1, pos2 - 2, n, m, dp);
            }
        } else {
            return 0;
        }
        return dp[pos1][pos2];
    }

    public static boolean tr(int pos1, int pos2, int n, int m) {
        return pos1 >= 0 && pos2 >= 0 && pos1 < n && pos2 < m;
    }
}
