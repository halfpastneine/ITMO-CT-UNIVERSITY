import java.util.*;

public class M {
    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int[] f = new int[t];
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            int[] ints = new int[n];
            for (int j = 0; j < n; j++) {
                ints[j] = sc.nextInt();
            }
            Map<Integer, Integer> map = new HashMap<>();
            int count = 0;
            for (int k = 0; k < n; k++) {
                for (int j = k + 1; j < n; j++) {
                    int ab = 2 * ints[k] - ints[j];
                    if (map.containsKey(ab)) {
                        count += map.get(ab);
                    }
                }
                if (map.containsKey(ints[k])) {
                    map.put(ints[k], map.get(ints[k]) + 1);
                } else {
                    map.put(ints[k], 1);
                }
            }
            f[i] = count;
        }
        for (int i = 0; i < t; i++) {
            System.out.println(f[i]);
        }
    }
}
