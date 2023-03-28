import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class G {
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
            BigInteger[][] dp = new BigInteger[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    dp[i][j] = BigInteger.valueOf(-1);
                }
            }
            dp[0][0] = BigInteger.valueOf(1);
            BigInteger big = new BigInteger(String.valueOf(ans(n - 1, m - 1, n, m, dp)));
            BigInteger lon = big.subtract((BigInteger.valueOf(1000000 + 7).multiply(big.divide(BigInteger.valueOf(1000000 + 7)))));
            buffer1.write(String.valueOf(lon));
            buffer1.close();
        } catch (IOException ignored) {
        }
    }

    public static BigInteger ans(int pos1, int pos2, int n, int m, BigInteger[][] dp) {
        if (tr(pos1, pos2, n, m)) {
            if (dp[pos1][pos2].compareTo(BigInteger.valueOf(-1)) == 0) {
                dp[pos1][pos2] = ans(pos1 - 2, pos2 - 1, n, m, dp).add(ans(pos1 - 2, pos2 + 1, n, m, dp)).add(
                        ans(pos1 - 1, pos2 - 2, n, m, dp).add(ans(pos1 + 1, pos2 - 2, n, m, dp)));
            }
        } else {
            return BigInteger.valueOf(0);
        }
        return dp[pos1][pos2];
    }

    public static boolean tr(int pos1, int pos2, int n, int m) {
        return pos1 >= 0 && pos2 >= 0 && pos1 < n && pos2 < m;
    }
}
