import java.util.*;

public class M {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] sort = new int[n];
        int[] poisk = new int[k];
        for (int i = 0; i < n; i++) {
            sort[i] = sc.nextInt();
        }
        for (int i = 0; i < k; i++) {
            poisk[i] = sc.nextInt();
            int tmp = poisk[i];
            int l = 0;
            int r = sort.length - 1;
            int mid = (l + r) / 2;
            while (l < r) {
                if (sort[mid] == tmp) {
                    break;
                } else if (sort[mid] < tmp) {
                    l = mid;
                } else if (sort[mid] > tmp) {
                    r = mid;
                }
                mid = (l + r) / 2;
                if (l == mid || r == mid) {
                    break;
                }
            }
            if (sort[mid] == tmp) {
                System.out.println(sort[mid]);
            } else if (Math.abs(sort[r] - tmp) < Math.abs(sort[l] - tmp)) {
                System.out.println(sort[r]);
            } else {
                System.out.println(sort[l]);
            }
        }
    }
}
