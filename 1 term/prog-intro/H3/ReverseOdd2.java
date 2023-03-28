import java.util.*;

public class ReverseOdd2 {
    public static void main(String[] args) {
        int[] k1 = new int[100];
        int[][] k = new int[100][];
        FastScanner s = new FastScanner(System.in);
        int i = 0;
        int j = 0;
        int u = 0;
        while (s.hasNextLine()) {
            FastScanner q = new FastScanner(s.nextLine());
            while (q.hasNextInt()) {
                k1[i] = q.nextInt();
                i++;
                if (i == k1.length) {
                    k1 = Arrays.copyOf(k1, k1.length * 2);
                }
            }
            k[j] = new int[i];
            u++;
            if (u == k.length) {
                k = Arrays.copyOf(k, k.length * 2);
            }
            for (int p = 0; p < k[j].length; p++) {
                k[j][p] = k1[p];
            }
            j++;
            i = 0;

        }
        for (int w = j - 1; w >= 0; w--) {
            for (int h = k[w].length - 1; h >= 0; h--) {
                if ((w + h) % 2 != 0) {
                    System.out.print(k[w][h] + " ");
                }
            }
            System.out.println();
        }
    }
}
