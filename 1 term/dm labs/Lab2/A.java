import java.util.*;
import java.math.*;

public class A {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        BigInteger[] ints = new BigInteger[n + 2];
        // ArrayList<BigInteger> array = new ArrayList<BigInteger>();
        BigInteger[] sum = new BigInteger[n + 2];
        int q = 0;
        int w = 0;
        BigInteger ans = BigInteger.valueOf(0);
        for (int i = 0; i < n; i++) {
            ints[i] =  BigInteger.valueOf(sc.nextInt());
            sum[i] = BigInteger.valueOf(2).pow(50);
        }
        ints[n] = BigInteger.valueOf(2).pow(50);
        ints[n + 1] = BigInteger.valueOf(2).pow(50);
        for (int i = 1; i < ints.length; i++) {
            BigInteger tmp = ints[i];
            int j = i - 1;
            while(j >= 0 && tmp.compareTo(ints[j]) == -1) {
                ints[j + 1] = ints[j];
                j--;
            }
            ints[j + 1] = tmp;
        }
        for (int k = 0; k < n - 1; k++) {
            if (ints[q].add(ints[q + 1]).compareTo(sum[w].add(ints[q])) != 1 &&
             ints[q].add(ints[q + 1]).compareTo(sum[w + 1].add(sum[w])) != 1) {
                sum[k] = ints[q].add(ints[q + 1]);
                ans = ans.add(sum[k]);
                q += 2;
            }
            else if (ints[q].add(sum[w]).compareTo(ints[q + 1].add(ints[q])) != 1 &&
             ints[q].add(sum[w]).compareTo(sum[w + 1].add(sum[w])) != 1) {
                sum[k] = ints[q].add(sum[w]);
                ans = ans.add(sum[k]);
                q++;
                w++;
            } else {
                sum[k] = sum[w].add(sum[w + 1]);
                ans = ans.add(sum[k]);
                w += 2;
            }
        }
        System.out.println(ans);
    }
}
