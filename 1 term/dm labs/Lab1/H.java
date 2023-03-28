import java.util.*;

public class H {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String x = "";
        StringBuilder s = new StringBuilder();
        String base = "((A0|B0)|(A0|B0))";
        for (int i = 0; i < n*2 - 2; i++) {
            s.append("(");
        }
        s.append(base + "|");
        x += s.toString();
        s = new StringBuilder();
        if (n == 1) {
            System.out.println(base);
        } else {
            for (int i = 1; i < n; i++) {
                String k = "(A" + i  + "|A" + i + ")";
                String k1 = "(B" + i  + "|B" + i + ")";
                String k2 = "(A" + i  + "|B" + i + ")";
                s.append("(" + k + '|' + k1 + ")" + ")" + "|" + k2 + ")");
                if (i == n - 1) {
                    x += s.toString();
                    s = new StringBuilder();
                } else {
                    s.append('|');
                    x += s.toString();
                    s = new StringBuilder();
                }
             }
             System.out.println(x);
        }
    }
}
