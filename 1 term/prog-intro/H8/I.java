import java.util.*;

public class I {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int g = 1000000000;
        int xl = g;
        int xr = -g;
        int yl = g;
        int yr = -g;
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int h = sc.nextInt();
            xl = Math.min(xl, x - h);
            xr = Math.max(xr, x + h);
            yl = Math.min(yl, y - h);
            yr = Math.max(yr, y + h);
        }
        int x = (xl + xr) / 2;
        int y = (yl + yr) / 2;
        int h = ((Math.max(xr - xl, yr - yl) + 1) / 2);
        System.out.println(x + " " + y + " " + h);
    }
}
