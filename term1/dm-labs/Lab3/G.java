import java.util.ArrayList;
import java.util.Scanner;

public class G {

    public static void swap(int[] ints, int k, int j) {
        int tmp = ints[k];
        ints[k] = ints[j];
        ints[j] = tmp;
    }

    public static void reverse(int[] ints, int k) {
        ArrayList<Integer> as = new ArrayList<>();
        for (int i = ints.length - 1; i > k; i--) {
            as.add(ints[i]);
        }
        int h = 0;
        for (int i = k + 1; i < ints.length; i++) {
            ints[i] = as.get(h);
            h++;
        }
    }

    public static void nextPer(int[] ints, int n) {
        for (int i = n - 1; i > 0 ; i--) {
            if (ints[i] > ints[i - 1]) {
                int k = i - 1;
                for (int j = n - 1; j >= i; j--) {
                    if (ints[j] > ints[k]) {
                        swap(ints, k, j);
                        reverse(ints, k);
                        return;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int count = 1;
        for (int i = 2; i <= n; i++) {
            count *= i;
        }
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = i + 1;
            System.out.print(ints[i] + " ");
        }
        System.out.println();
        while (count > 1) {
            nextPer(ints, n);
            for (int i = 0; i < n; i++) {
                System.out.print(ints[i] + " ");
            }
            count--;
            System.out.println();
        }

    }
}
