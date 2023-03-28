import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class B {
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
            int k = sc.nextInt();
            int[] coins = new int[n + 1];
            int[] dp = new int[n + 1];
            for (int i = 2; i < n; i++) {
                coins[i] = sc.nextInt();
            }
            dp[1] = 0;
            coins[1] = 0;
            coins[n] = 0;
            ArrayList<Integer> ans = new ArrayList<>();
            for (int i = 2; i <= n; i++) {
                int num = i - 1;
                for (int j = Math.max(1, i - k); j <= i - 1; j++) {
                    if (dp[j] > dp[num]) {
                        num = j;
                    }
                }
                dp[i] = dp[num] + coins[i];
                if (!ans.contains(num)) {
                    ans.add(num);
                }
//
            }
            ans.add(n);
            buffer1.write((dp[n]) + System.lineSeparator());
            buffer1.write((ans.size() - 1) + System.lineSeparator());
            for (Integer an : ans) {
                buffer1.write(an + " ");
            }
            buffer1.close();
        } catch (IOException ignored){}
    }
}
