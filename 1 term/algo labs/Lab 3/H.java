import java.util.ArrayList;
import java.util.Scanner;

public class H {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> as = new ArrayList<>();
        for (int i = 0; i <= Math.sqrt(n); i++) {
            as.add(i);
        }
        as.set(1, 0);
        int pos = 2;
        while (pos != as.size()) {
            if (as.get(pos) != 0) {
                int j = pos * 2;
                while (j < as.size()) {
                    as.set(j, 0);
                    j += pos;
                }
                while (n % as.get(pos) == 0) {
                    n /= as.get(pos);
                    System.out.print(as.get(pos) + " ");
                }
            }
            pos++;
        }
        if (n != 1) {
            System.out.print(n);
        }
    }
}
//10004983
