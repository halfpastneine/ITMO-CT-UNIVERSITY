import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        sb.append("0".repeat(Math.max(0, n)));
        System.out.println(sb);
        for (int i = 1; i < Math.pow(2, n); i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (sb.charAt(j) == '0') {
                    sb.replace(j , j + 1, "1");
                    if (j < n - 1) {
                        int x = j;
                        while (x != n - 1) {
                            sb.replace(x + 1, x + 2, "0");
                            x++;
                        }
                    }
                    break;
                }
            }
            System.out.println(sb);
        }
    }
}
