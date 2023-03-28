import java.util.*;
public class B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] ints = new int[n];
        int[] ans = new int[101];
        for (int i = 0; i < n; i++) {
            ints[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            ans[ints[i]]++;
        }
        for (int i = 0; i < 101; i++) {
            if (ans[i] != 0) {
                while (ans[i] != 0) {
                    ans[i]--;
                    System.out.print(i + " ");
                }
            }
        }
    }
}
