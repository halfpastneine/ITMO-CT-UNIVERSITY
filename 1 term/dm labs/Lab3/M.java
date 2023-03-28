import java.util.Scanner;

public class M {

    public static long getF(int n) {
        long count = 1;
        for (int i = 2; i <= n; i++) {
            count *= i;
        }
        return count;
    }

    public static long getPerm(int n, long k, int[] ints) {
        int i = 1;
        int cnt = 0;
        int z = 0;
        if (i * getF(n) < k) {
            while (i * getF(n) < k) {
                i++;
            }
            k = k - (i - 1) * getF(n);
        }
        int ja = 0;
        while (z != i) {
            if (ints[z + ja] != -1) {
                cnt = ints[z + ja];
                z++;
            } else {
                ja++;
            }
        }
        System.out.print(cnt + " ");
        i = cnt;
        for (int j = 0; j < ints.length; j++) {
            if (ints[j] == i) {
                ints[j] = -1;
            }
        }
        return k;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long k = sc.nextLong() + 1;
        int[] ints = new int[n];
        for (int i = 1; i <= n; i++) {
            ints[i - 1] = i;
        }
        n--;
        while (n != 0) {
            k = getPerm(n, k, ints);
            n--;
        }
        for (int anInt : ints) {
            if (anInt != -1) {
                System.out.print(anInt);
                break;
            }
        }
    }
}
