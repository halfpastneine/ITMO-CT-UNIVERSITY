import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;

public class D {


    static long[][] matrixPow(long[][] matrix, long[][] matrix1, int n) {
        long[][] mat = new long[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    mat[i][j] += matrix[i][k] * matrix1[k][j];
                }
                mat[i][j] = (long) (mat[i][j] % 1000000007);
            }
        }
        return mat;
    }

    static void print(long[][] matrix, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        FileReader rd = new FileReader("problem4.in");
        Scanner sc = new Scanner(rd);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        int l = sc.nextInt();
        HashSet<Integer> h = new HashSet<>();
        for (int i = 0; i < k; i++) {
            h.add(sc.nextInt() - 1);
        }
        long[][] matrix = new long[n][n];
        for (int i = 0; i < m; i++) {
            int tmp = sc.nextInt();
            int tmp1 = sc.nextInt();
            sc.next();
            matrix[tmp - 1][tmp1 - 1]++;
        }
        long[][] matrix1 = matrix;
        print(matrix, n);
        for (int i = 1; i < l; i++) {
            matrix = matrixPow(matrix, matrix1, n);
        }
        long sum = 0;
        for (int i = 0; i < n; i++) {
            if (h.contains(i)) {
                sum += matrix[0][i];
            }
            sum = (sum % 1000000007);
        }
        FileWriter wr = new FileWriter("problem4.out");
        wr.write(String.valueOf(sum));
        wr.close();
        sc.close();
    }
}
