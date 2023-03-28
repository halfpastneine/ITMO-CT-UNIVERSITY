import java.util.Scanner;

public class E{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // int i = 0;
        double q = Math.pow(2, n);
        int a = (int) q;
        String[] string = new String[a];
        int[][] ints = new int[a][a];
        int[] intq = new int[a];
        for (int i = 0; i < a; i++) {
            string[i] = sc.next();
            ints[0][i] = sc.nextInt();
        }
        for (int x = 1; x < a; x++) {
            for (int j = 0; j < a; j++) {
                if (j == a - 1){
                    break;
                }
                else {
                    ints[x][j] = ints[x-1][j] ^ ints[x-1][j + 1];
                }
            }
        }
        for (int r = 0; r < a; r++) {
            intq[r] = ints[r][0];
        }
        for (int y = 0; y < a; y++) {
            System.out.println(string[y] + " " + intq[y]);
        }
    }
}
