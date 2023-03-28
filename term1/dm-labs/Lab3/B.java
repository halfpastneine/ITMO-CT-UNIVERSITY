import java.util.ArrayList;
import java.util.Scanner;

public class B {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int x = 1;
        ArrayList<String> as = new ArrayList<>();
        as.add("0");
        as.add("1");
        while (x < n) {
            for (int i = as.size() - 1; i >= 0; i--) {
                as.add(as.get(i));
            }
            for (int i = 0; i < as.size()/2; i++) {
                String sb = 0 +
                        as.get(i);
                as.set(i, sb);
            }
            for (int i = as.size()/2; i < as.size(); i++) {
                String sb = 1 +
                        as.get(i);
                as.set(i, sb);
            }
            x++;
        }
        for (String a : as) {
            System.out.println(a);
        }
    }
}
