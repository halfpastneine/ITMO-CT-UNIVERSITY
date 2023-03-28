import java.math.BigInteger;
import java.util.Scanner;

public class P {

    public static long getNumComb(int n, int k) {
        BigInteger comb = new BigInteger("1");
        for (int i = 2; i <= n; i++) {
            comb = comb.multiply(BigInteger.valueOf(i));
        }
        for (int i = 2; i <= k; i++) {
            comb = comb.divide(BigInteger.valueOf(i));
        }
        for (int i = 2; i <= (n - k); i++) {
            comb = comb.divide(BigInteger.valueOf(i));
        }
        return Long.parseLong(String.valueOf(comb));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] ints = new int[k + 1];
        ints[0] = 0;
        for (int i = 1; i <= k; i++) {
            ints[i] = sc.nextInt();
        }
        long ans = 0;
        for (int i = 1; i <= k; i++) {
            for (int j = ints[i - 1]+ 1; j <= ints[i] - 1 ; j++) {
                ans += getNumComb(n - j, k - i);
            }
        }
        System.out.println(ans);
    }
}
