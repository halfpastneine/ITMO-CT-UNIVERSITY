import java.util.Scanner;

public class A1 {

    public static void nextBracket(char[] buffer) {
        int depth = 0;
        boolean tr = false;
        for (int i = buffer.length - 1; i >= 0; i--) {
            if (buffer[i] == ')') {
                depth++;
            } else {
                depth--;
            }
            if (buffer[i] == '(' && depth > 0) {
                StringBuilder sb = new StringBuilder();
                buffer[i] = ')';
                int d = 0;
                for (int j = 0; j <= i; j++) {
                    sb.append(buffer[j]);
                    if (buffer[j] == ')') {
                        d--;
                    } else {
                        d++;
                    }
                }
                sb.append("(".repeat((buffer.length - i - d) / 2));
                sb.append(")".repeat((buffer.length - i - d) / 2));
                sb.append(")".repeat(d));
                System.out.println(sb);
                tr = true;
                break;
            }
        }
        if (!tr) {
            System.out.println("-");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] buffer = sc.next().toCharArray();
        nextBracket(buffer);
    }
}
