import java.util.Scanner;

public class B2 {

    public static void nextM(int[] ints, int n, StringBuilder sb, int i) {
        int j = i;
        while (j < n - 1 && ints[j + 1] > ints[i]) {
            j++;
        }
        int tmp = ints[i];
        ints[i] = ints[j];
        ints[j] = tmp;
        System.out.print(ints[i] + " ");
        for (int z = n - 1; z > i; z--) {
            System.out.print(ints[z] + " ");
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        StringBuilder s = new StringBuilder();
        int[] ints = new int[n];
        int pos = -1;
        ints[0] = sc.nextInt();
        sb.append(0).append(" ");
        s.append(ints[0]).append(" ");
        int tmp = 0;
        for (int i = 1; i < n; i++) {
            ints[i] = sc.nextInt();
            sb.append(0).append(" ");
            if (ints[i - 1] < ints[i]) {
                pos = i - 1;
                tmp = s.length();
            }
            s.append(ints[i]).append(" ");
        }
        if (pos == -1) {
            System.out.println(sb);
        } else {
            s.delete(tmp, s.length());
            int h = s.length() - 1;
            while (s.charAt(h) != ' ') {
                h--;
            }
            s.delete(h - 1, s.length());
            System.out.print(s);
            nextM(ints, n, s, pos);
        }
    }
}