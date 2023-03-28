import java.util.*;

public class A{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int n = sc.nextInt();
        int count = 1 + 2 * (int) Math.ceil((double)(n - b) / (b - a));
        System.out.println(count);
    }
}
