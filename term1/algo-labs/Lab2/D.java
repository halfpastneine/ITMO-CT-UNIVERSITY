import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class D {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new FileInputStream("input.txt"));
            BufferedWriter buffer1 = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream("output.txt"),
                            StandardCharsets.UTF_8
                    )
            );
            int n = sc.nextInt();
            int m = sc.nextInt();
            int[][] dp = new int[n][m];
            int[][] coins = new int[n][m];
            int[][] pos = new int[n][m];
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    coins[i][j] = sc.nextInt();
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (i == 0 && j == 0) {
                        dp[0][0] = coins[0][0];
                        pos[i][j] = -1;
                    } else if (i == 0) {
                        dp[i][j] = dp[i][j - 1] + coins[i][j];
                        pos[i][j] = 0;
                    } else if (j == 0) {
                        dp[i][j] = dp[i - 1][j] + coins[i][j];
                        pos[i][j] = 1;
                    } else {
                        dp[i][j] = coins[i][j] + Math.max(dp[i - 1][j], dp[i][j - 1]);
                        if (dp[i][j - 1] > dp[i - 1][j]) {
                            pos[i][j] = 0;
                        } else {
                            pos[i][j] = 1;
                        }
                    }
                }
            }
            int i = n - 1;
            int j = m - 1;
            while (i > 0 || j > 0) {
                if (pos[i][j] == 1) {
                    i--;
                    list.add(0, "D");
                } else {
                    j--;
                    list.add(0, "R");
                }
            }
            buffer1.write(dp[n - 1][m - 1] + System.lineSeparator());
            for (int k = 0; k < list.size(); k++) {
                buffer1.write(list.get(k) + "");
            }
            buffer1.close();
        } catch (IOException ignored) {
        }
    }
}
