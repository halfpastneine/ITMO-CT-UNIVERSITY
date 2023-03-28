import java.util.ArrayList;
import java.util.Scanner;

public class F {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = 1;
        int n = sc.nextInt();
        boolean tr = false;
        ArrayList<String> as = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append("0".repeat(Math.max(0, n)));
        as.add(sb.toString());
        for (int i = 1; i < Math.pow(2, n); i++) {
            tr = false;
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
            for (int j = 0; j < sb.length() - 1; j++) {
                if (sb.charAt(j) == sb.charAt(j + 1) && sb.charAt(j) == '1') {
                    tr = true;
                }
            }
            if (!tr) {
                count++;
                as.add(sb.toString());
            }
        }
        System.out.println(count);
        for (int i = 0; i < as.size(); i++) {
            System.out.println(as.get(i));
        }
    }
}
