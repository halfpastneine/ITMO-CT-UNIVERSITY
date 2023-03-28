import java.util.ArrayList;
import java.util.Scanner;

public class C {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<String> as = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("0".repeat(Math.max(0, n)));
        as.add(sb.toString());
        for (int i = 0; i < Math.pow(3, n - 1) - 1; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (sb.charAt(j) == '0') {
                    sb.replace(j, j + 1, "1");
                    break;
                }
                if (sb.charAt(j) == '1') {
                    sb.replace(j, j + 1, "2");
                    break;
                }
                if (sb.charAt(j) == '2') {
                    sb.replace(j, j + 1, "0");
                }
            }
            as.add(sb.toString());
        }
        for (String a : as) {
            StringBuilder s = new StringBuilder();
            s.append(a);
            System.out.println(s);
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < n; k++) {
                    char ch = s.charAt(k);
                    if (ch == '2') {
                        s.replace(k, k + 1, "0");
                    }
                    if (ch == '1') {
                        s.replace(k, k + 1, "2");
                    }
                    if (ch == '0') {
                        s.replace(k, k + 1, "1");
                    }
                }
                System.out.println(s);
            }
        }
    }
}
