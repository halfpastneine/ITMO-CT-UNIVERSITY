import java.util.Scanner;

public class C3 {

    public static void nextPat(int pr1, int pr2, int n) {
        if (pr2 > pr1) {
            pr2 += pr1;
            System.out.print(pr2);
        } else if ((pr2 == pr1) || (pr2 * 2 > pr1)) {
            System.out.print(pr2 + "+" + pr1);
        } else {
            int[] ints = new int[n];
            int i = 1;
            ints[0] = pr2;
            ints[1] = pr1;
            while (pr2 * 2 <= pr1) {
                ints[i] = pr2;
                pr1 -= pr2;
                i++;
                ints[i] = pr1;
            }
            for (int j = 0; j < i; j++) {
                System.out.print(ints[j] + "+");
            }
            System.out.print(ints[i]);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        sb.append(sc.next());
        int count = 0;
        int pos = sb.length() - 1;
        int[] ints = new int[2];
        StringBuilder s = new StringBuilder();
        int o = 0;
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '+') {
                o++;
                break;

            }
        }
        int k = 1;
        if (o == 0) {
            System.out.println("No solution");
        } else {
            while (count != 2) {
                while (sb.charAt(pos) != '+' && sb.charAt(pos) != '=') {
                    s.insert(0, sb.charAt(pos));
                    sb.delete(sb.length() - 1, sb.length());
                    pos--;
                }
                count++;
                if (count != 2) {
                    sb.delete(sb.length() - 1, sb.length());
                }
                pos--;
                ints[k] = Integer.parseInt(s.toString());
                s = new StringBuilder();
                k--;
            }
            System.out.print(sb);
            nextPat(ints[1] - 1, ints[0] + 1, 100000);
        }

    }
}
