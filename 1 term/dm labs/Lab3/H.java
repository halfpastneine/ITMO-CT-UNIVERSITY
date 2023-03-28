import java.util.Scanner;

public class H {

    public static int[] nextCom(int[] ints, int n, int k) {
        boolean tr = false;
        ints[k] = n + 1;
        int s = 0;
        for (int i = k; i > 0; i--) {
            if (ints[i] - ints[i - 1] >= 2) {
                if (ints[i - 1] + 1 <= n) {
                    ints[i - 1]++;
                    s = i - 1;
                    tr = true;
                }
                break;
            }
        }
        for (int i = s + 1; i < k; i++) {
            ints[i] = ints[i - 1] + 1;
        }
        if (tr) {
            return ints;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] ints = new int[k + 1];
        for (int i = 1; i <= k; i++) {
            ints[i - 1] = i;
            System.out.print(i + " ");
        }
        System.out.println();
        if (n != 1 && n - k != 0) {
            ints[k - 1]++;
            for (int i = 1; i <= k; i++) {
                System.out.print(ints[i - 1] + " ");
            }
            System.out.println();
        }
        while (nextCom(ints, n, k) != null) {
            for (int i = 0; i < k; i++) {
                System.out.print(ints[i] + " ");
            }
            System.out.println();
        }

    }
}
