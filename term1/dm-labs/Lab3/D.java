import java.util.HashSet;
import java.util.Scanner;

public class D {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        HashSet<String> as = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        sb.append("0".repeat(n));
        System.out.println(sb);
        for (int i = 0; i < Math.pow(2, n) - 1; i++) {
            sb.delete(0, 1);
            if (as.contains(sb + "1")) {
                sb.append("0");
            } else {
                sb.append("1");
            }
            as.add(sb.toString());
            System.out.println(sb);
        }
    }
}
