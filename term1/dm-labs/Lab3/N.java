import java.util.Scanner;

public class N {

    public static long getF(int n) {
        long cnt = 1;
        for (int i = 1; i <= n; i++) {
            cnt *= i;
        }
        return cnt;
    }

    public static long getNum(int n, int k, int[] ints) {
        long count = 0;
        for (int i = 0; i < k; i++) {
            if (ints[i] != -1) {
                count++;
            }
        }
        for (int i = 0; i < ints.length; i++) {
            if (ints[i] == k) {
                ints[i] = -1;
                break;
            }
        }
        return (count - 1) * getF(n - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] ints = new int[n];
        for (int i = 1; i <= n; i++) {
            ints[i - 1] = i;
        }
        long count = 0;
        while (n != 0) {
            int k = sc.nextInt();
            count += getNum(n, k, ints);
            n--;
        }
        System.out.println(count);
    }
}
