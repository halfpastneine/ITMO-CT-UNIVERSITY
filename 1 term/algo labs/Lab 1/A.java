import java.util.*;

public class A {

    static int[] mergeSort(int[] left, int[] right) {
        int[] ans = new int[left.length + right.length];
        int i = 0;
        int j = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                ans[i + j] = left[i];
                i++;
            } else {
                ans[i + j] = right[j];
                j++;
            }
        }
        while (i < left.length) {
            ans[i + j] = left[i];
            i++;
        }
        while (j < right.length) {
            ans[i + j] = right[j];
            j++;
        }
        return ans;
    }

    static int[] div(int[] ints, int n) {
        if (n < 2) {
            return ints;
        }
        int mid = n/2;
        int[] left = new int[mid];
        int[] right = new int[n - mid];
        for (int i = 0; i < mid; i++) {
            left[i] = ints[i];
        }
        for (int i = mid; i < n; i++) {
            right[i - mid] = ints[i];
        }
        left = div(left, mid);
        right = div(right, n - mid);
        return mergeSort(left, right);

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] ints = new int[n];
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = sc.nextInt();
        }
        ans = div(ints, n);
        for (int i = 0; i < n; i++) {
            System.out.print(ans[i] + " ");
        }

    }
}
