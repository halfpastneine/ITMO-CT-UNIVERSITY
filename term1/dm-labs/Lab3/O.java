import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class O {

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

    public static void getCom(int n, int k, int m) {
        ArrayList<Integer> as = new ArrayList<>();
        int pos = 1;
        while(k > 0) {
            long comb = getNumComb(n - 1, k - 1);
            if (comb > m) {
                as.add(pos);
                k--;
            } else {
                m -= comb;
            }
            n--;
            pos++;
        }
        for (Integer a : as) {
            System.out.print(a + " ");
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int m = sc.nextInt();
        getCom(n, k, m);
    }
}
