import java.util.*;

public class C {

    static long count = 0;
    static long[] mergeSort(long[] left, long[] right) {
        long[] ans = new long[left.length + right.length];
        int i = 0;
        int j = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                ans[i + j] = left[i];
                i++;
            } else {
                ans[i + j] = right[j];
                j++;
                count += left.length - i;
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

    static long[] div(long[] ints, long n) {
        if (n < 2) {
            return ints;
        }
        int mid = (int) n/2;
        long[] left = new long[mid];
        long[] right = new long[(int)n - mid];
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
        long[] ints = new long[n];
        long[] ans = new long[n];
        for (int i = 0; i < n; i++) {
            ints[i] = sc.nextLong();
        }
        ans = div(ints, n);
        System.out.print(count);
    }
}
