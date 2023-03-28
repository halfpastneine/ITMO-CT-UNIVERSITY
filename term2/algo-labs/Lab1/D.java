import java.util.Arrays;
import java.util.Scanner;

public class D {

    public static void getK1(int[] b, int k) {
        int i = 1;
        while (i < b.length / 2) {
            if (b[i * 2] >= k) {
                i *= 2;
            } else {
                k -= b[i * 2];
                i = i * 2 + 1;
            }
        }
        System.out.println(i - b.length / 2);
    }

    public static void buildTree(int[] a, int[] b) {
        System.arraycopy(a, 0, b, a.length, b.length - a.length);
        for (int i = a.length - 1; i > 0; i--) {
            b[i] = b[2 * i] + b[2 * i + 1];
        }
    }

    public static void set(int[] b, int pos, int value, int n) {
        b[pos + b.length / 2] = value;
        if (pos % 2 == 1) {
            pos--;
        }
        int k = pos + b.length / 2;
        pos = k;
        while (k != 1) {
            if (pos % 2 == 1) {
                pos--;
            }
            k = k >> 1;
            b[k] = b[pos] + b[pos + 1];
            pos = k;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int z = 0;
        while (n > Math.pow(2, z)) {
            z++;
        }
        int g = (int) Math.pow(2, z);
        int[] a = new int[g];
        int[] b = new int[2 * g];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        for (int i = n; i < a.length; i++) {
            a[i] = 0;
        }
        buildTree(a, b);
        for (int i = 0; i < k; i++) {
            if (sc.nextInt() == 1) {
                int j = sc.nextInt();
                if (a[j] == 0) {
                    a[j] = 1;
                    set(b, j, 1, n);
                } else {
                    a[j] = 0;
                    set(b, j, 0, n);
                }
            } else {
                getK1(b, sc.nextInt() + 1);
            }
        }
    }

}
