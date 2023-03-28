import java.util.Scanner;

public class K {

    static void buildSparceTable(int[][] a, int n) {
        int k = n;
        for (int j = 1; j <= Math.ceil(Math.log(n)); j++) {
            for (int i = 0; i < k - Math.pow(2, j - 1); i++) {
                a[j][i] = Math.min(a[j - 1][i], a[j - 1][i + (int) Math.pow(2, j - 1)]);
                System.out.print(a[j][i] + " ");
            }
            System.out.println();
            k -= Math.pow(2, j - 1);
        }
    }

//    static void log()

//    static int min(int[][] a, int n) {
//
//    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[][] a = new int[(int) Math.log(n) + 2][n];
        a[0][0] = sc.nextInt();
        for (int i = 1; i < n; i++) {
//            a[0][i] = (23 * a[0][i - 1] + 21563) % 16714589;
            a[0][i] = sc.nextInt();
        }
        buildSparceTable(a, n);

    }
}
