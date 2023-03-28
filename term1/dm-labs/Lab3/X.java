import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class X {

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
        boolean tr = false;
        for (int i = n - 1; i > 0; i--) {
            if (ints[i] > ints[i - 1]) {
                int k = i - 1;
                for (int j = n - 1; j >= i; j--) {
                    if (ints[j] > ints[k]) {
                        swap(ints, k, j);
                        reverse(ints, k);
                        for (int z = 0; z < n; z++) {
                            tr = true;
                            System.out.print(ints[z] + " ");
                        }
                        break;
                    }
                }
                break;
            }
        }
        if (!tr) {
            for (int i = 0; i < n; i++) {
                System.out.print(0 + " ");
            }
        }
    }

    public static void prevPer(int[] ints, int n) {
        boolean tr = false;
        for (int i = n - 1; i > 0; i--) {
            if (ints[i] < ints[i - 1]) {
                int pos = 0;
                for (int j = i; j < n; j++) {
                    if (ints[i - 1] > ints[j]) {
                        pos = j;
                    }
                }
                swap(ints, pos, i - 1);
                reverse(ints, i - 1);
                for (int k = 0; k < n; k++) {
                    tr = true;
                    System.out.print(ints[k] + " ");
                }
                System.out.println();
                break;
            }
        }
        if (!tr) {
            for (int i = 0; i < n; i++) {
                System.out.print(0 + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] ints = new int[n];
        int[] ints1 = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = sc.nextInt();
            ints1[i] = ints[i];
        }
        prevPer(ints, n);
        nextPer(ints1, n);
    }
}
