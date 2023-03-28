import java.util.*;

public class N {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int n = sc.nextInt();
        int fp = sc.nextInt();
        int sp = sc.nextInt();
        int time = Math.min(fp, sp);
        int left = 0;
        int right = (n - 1) * Math.max(fp, sp);
        while (right - left > 1) {
            int mid = (right + left) / 2;
            if (mid/fp + mid/sp < n - 1) {
                left = mid;
            } else {
                right = mid;
            }
        }
        System.out.println(right + time);
    }
}
