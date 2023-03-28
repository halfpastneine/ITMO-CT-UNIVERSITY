import java.util.*;

public class F {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int max = 0;
        ArrayList<Integer> a = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            a.add(in.nextInt());
            if (max < a.get(i)) {
                max = a.get(i);
            }
        }
        ArrayList<Integer> s = new ArrayList<>();
        int[] ints = new int[max + 1];
        for (int i = 0; i <= max; i++) {
            ints[i] = 0;
        }
        for (int i = 2; i <= max; i++) {
            if (ints[i] == 0) {
                ints[i] = i;
                s.add(i);
            }
            int j = 0;
            while (j < s.size() && s.get(j) <= ints[i] && s.get(j) * i <= max) {
                ints[s.get(j) * i] =  s.get(j);
                j++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (Integer integer : a) {
            if (integer == ints[integer]) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
        System.out.println(sb);
    }
}