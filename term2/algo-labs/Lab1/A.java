import java.util.Scanner;

public class A {


    public static void buildFenvTree(int[] a, long[] b, int n) {
        for (int i = 0; i < n; i++) {
            int k = i & (i + 1);
            for (int j = k; j <= i; j++) {
                b[i] += a[j];
            }
        }
    }

    public static long sum(long[] b, int l, int r) {
        long sum = 0;
        while (r >= 0) {
            sum += b[r];
            r = r & (r + 1);
            r--;
            if (r == 0) {
                break;
            }
        }
        while (l >= 0) {
            sum -= b[l];
            l = l & (l + 1);
            l--;
        }
        return sum;
    }

    public static void set(long[] b, int[] a, int pos, int value) {
        long x = -a[pos] + value;
        a[pos] = value;
        b[pos] += x;
        while (true) {
            pos = pos | (pos + 1);
            if (pos >= b.length) {
                break;
            }
            b[pos] += x;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] a = new int[n];
        long[] b = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        buildFenvTree(a, b, n);
        for (int i = 0; i < k; i++) {
            if (sc.nextInt() == 1) {
                set(b, a, sc.nextInt(), sc.nextInt());
            } else {
                System.out.println(sum(b, sc.nextInt() - 1, sc.nextInt() - 1));
            }
        }
    }
}
