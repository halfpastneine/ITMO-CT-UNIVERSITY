import java.util.*;

public class O {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double c = sc.nextDouble();
        double l = 1;
        double r = Math.sqrt(c);
        double mid = (l + r) / 2;
        while (r - l > 0.000001) {
            if (Math.pow(mid, 2) + Math.sqrt(mid) > c) {
                r = mid;
            }
            if (Math.pow(mid, 2) + Math.sqrt(mid) < c) {
                l = mid;
            }
            mid = (l + r) / 2;
        }
        System.out.println(r);
    }
}
