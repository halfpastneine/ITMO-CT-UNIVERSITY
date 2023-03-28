import java.util.*;

public class F {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int n = sc.nextInt();
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            int n1 = sc.nextInt();
            if (n1 == 0) {
                ints[i] = sc.nextInt();
            } else {
                int tmp = 0;
                int pos = 0;
                for (int j = 0; j < i; j++) {
                    if (ints[j] > tmp) {
                        tmp = ints[j];
                        pos = j;
                    }
                }
                ints[pos] = 0;
                System.out.println(tmp);
            }
        }
    }
}
