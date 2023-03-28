import java.io.*;
import java.nio.charset.StandardCharsets;

public class C {
    public static void main(String[] args) {
        try {
            BufferedReader buffer = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream("input.txt"),
                            StandardCharsets.UTF_8
                    )
            );
            BufferedWriter buffer1 = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream("output.txt"),
                            StandardCharsets.UTF_8
                    )
            );
            int n = Integer.parseInt(buffer.readLine());
            long[] dp = new long[n + 1];
            dp[0] = 2;
            dp[1] = 3;
            for (int i = 2; i < n; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            buffer1.write(String.valueOf(dp[n - 1]));
            buffer.close();
            buffer1.close();
        } catch (IOException e) {
            System.out.println();
        }
    }
}
//C:\Users\dortm\Desktop\homework\алгосы\Lab2\src\input.txt