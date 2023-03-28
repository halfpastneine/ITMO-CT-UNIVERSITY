import java.util.Scanner;

public class I {

    public static void bracket(int n, int lc, int rc, String s) {
        if (lc + rc == 2 * n) {
            System.out.println(s);
        }
        if (lc < n) {
            bracket(n, lc + 1, rc, s + "(");
        }
        if (lc > rc) {
            bracket(n, lc, rc + 1, s + ")");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = "";
        bracket(n, 0, 0, s);
    }
}
