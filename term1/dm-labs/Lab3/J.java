import java.util.*;
import java.util.Scanner;

public class J {

    public static LinkedList nextPat(LinkedList<Integer> as, int n) {
        int pr1 = as.get(as.size() - 1) - 1;
        int pr2 = as.get(as.size() - 2) + 1;
        int[] ints = new int[n];
        if (pr2 > pr1) {
            pr2 += pr1;
            ints[0] = pr2;
            as.remove(as.size() - 1);
            as.remove(as.size() - 1);
            as.add(ints[0]);
            return as;
        } else if (pr2 == pr1) {
            as.remove(as.size() - 1);
            as.remove(as.size() - 1);
            as.add(pr2);
            as.add(pr1);
            return as;
        } else {
            if (pr2 * 2 > pr1) {
                as.remove(as.size() - 1);
                as.remove(as.size() - 1);
                as.add(pr2);
                as.add(pr1);
            } else {
                int i = 1;
                ints[0] = pr2;
                ints[1] = pr1;
                while (pr2 * 2 <= pr1) {
                    ints[i] = pr2;
                    pr1 -= pr2;
                    i++;
                    ints[i] = pr1;
                }
                as.remove(as.size() - 1);
                as.remove(as.size() - 1);
                for (int j = 0; j <= i; j++) {
                    as.add(ints[j]);
                }
            }
            return as;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        LinkedList<Integer> as = new LinkedList<>();
        for (int i = 0; i < n - 1; i++) {
            as.add(1);
            System.out.print(1 + "+");
        }
        as.add(1);
        System.out.print(1);
        System.out.println();
        while (as.size() != 1) {
            nextPat(as, n);
            for (int i = 0; i < as.size() - 1; i++) {
                System.out.print(as.get(i) + "+");
            }
            System.out.print(as.get(as.size() - 1));
            System.out.println();
        }
    }
}