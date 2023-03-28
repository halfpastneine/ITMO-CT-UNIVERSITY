import java.util.Scanner;

public class J {

    static int gcd(int a, int b) {
        if (a == b || b == 0) {
            return a;
        }
        if (a == 0) {
            return b;
        }
        if (a % 2 == 0) {
            if (b % 2 == 0) {
                return gcd(a / 2, b / 2) * 2;
            } else {
                return gcd(a / 2, b);
            }
        }
        if (b % 2 == 0) {
            return gcd(a, b / 2);
        }
        if (a > b) {
            return gcd((a - b) / 2, b);
        }
        return gcd((b - a) / 2, a);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int min;
        boolean tr = false;
        if (n == 1) {
            System.out.println(sc.nextInt());
        } else {
            if (n % 2 == 1) {
                n--;
                tr = true;
            }
            int a = sc.nextInt();
            int b = sc.nextInt();
            min = gcd(a, b);
            for (int i = 0; i < n - 2; i++) {
                a = sc.nextInt();
                min = gcd(min, a);
            }
            if (tr) {
                min = gcd(min, sc.nextInt());
            }
            System.out.println(min);
        }
    }
}
