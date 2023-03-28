import java.util.ArrayList;
import java.util.Scanner;

public class N {

    static ArrayList<Integer> ans = new ArrayList<>();

    static void getAns(int k, int s, int[][] dp, ArrayList<Integer> num) {
        if (dp[k][s] == 0) {
            return;
        }
        if (dp[k - 1][s] == dp[k][s]) {
            getAns(k - 1, s, dp, num);
        } else {
            getAns(k - 1, s - num.get(k), dp, num);
            ans.add(num.get(k));
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        ArrayList<Integer> num = new ArrayList<>();
        num.add(0);
        for (int i = 0; i < n; i++) {
            num.add(sc.nextInt());
        }
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
        int max = 0;
        int pos1 = 0;
        int pos2 = 0;
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < k + 1; j++) {
                if (dp[i][j] <= k && max < dp[i][j]) {
                    max = dp[i][j];
                    pos1 = i;
                    pos2 = j;
                }
            }
        }
        getAns(pos1, pos2, dp, num);
        System.out.println(max);
        System.out.println(ans.size());
        for (Integer an : ans) {
            System.out.print(an + " ");
        }
    }
}
