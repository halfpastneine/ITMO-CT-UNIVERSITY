import java.util.Arrays;
import java.util.Scanner;

public class B {

    public static void buildTree(int[] a, int[] b, int[] c) {
        System.arraycopy(a, 0, b, a.length, b.length - a.length);
        for (int i = a.length - 1; i > 0; i--) {
            b[i] = Math.min(b[2 * i], b[2 * i + 1]);
            if (b[2 * i] == b[2 * i + 1]) {
                c[i] = c[2 * i] + c[2 * i + 1];
            } else {
                if (b[2 * i] < b[2 * i + 1]) {
                    c[i] = c[2 * i];
                } else {
                    c[i] = c[2 * i + 1];
                }
            }
        }
    }

    public static void set(int[] b, int pos, int value, int n, int[] c) {
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
            b[k] = Math.min(b[pos], b[pos + 1]);
            int tmp;
            if (b[pos] < b[pos + 1]) {
                tmp = pos;
            } else {
                tmp = pos + 1;
            }
            if (b[pos] == b[pos + 1]) {
                c[k] = c[pos] + c[pos + 1];
            } else {
                c[k] = c[tmp];
            }
            pos = k;
        }
    }

    public static void min(int[] b, int l, int r, int[] c) {
        l += b.length / 2;
        r += b.length / 2;
//        int mk = 0;
        int num = Integer.MAX_VALUE;
        int count = 0;
        int count1 = 0;
        int tmp = 0;
        while (l <= r) {
            if (l % 2 == 1) {
                tmp = num;
                if (num == b[l]) {
                    count += c[l];
                }
                if (num > b[l]) {
                    count = c[l];
                }
                num = Math.min(num, b[l]);
            }
            if (r % 2 != 1) {
                tmp = num;
                if (num == b[r]) {
                    count += c[r];
                }
                if (num > b[r]) {
                    count = c[r];
                }
                num = Math.min(num, b[r]);
            }
            l = (l + 1) / 2;
            r = (r - 1) / 2;
        }
        System.out.println(num + " " +(count + count1));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int g = 0;
        while (n > Math.pow(2, g)) {
            g++;
        }
        int z = (int) Math.pow(2, g);
        int[] a = new int[z];
        int[] b = new int[2 * z];
        int[] c = new int[2 * z];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        Arrays.fill(c, 1);
        for (int i = n; i < a.length; i++) {
            a[i] = Integer.MAX_VALUE;
        }
        buildTree(a, b, c);
        for (int i = 0; i < k; i++) {
            if (sc.nextInt() == 1) {
                set(b, sc.nextInt(), sc.nextInt(), n, c);
            } else {
                min(b, sc.nextInt(), sc.nextInt() - 1, c);
            }
        }
    }
}
