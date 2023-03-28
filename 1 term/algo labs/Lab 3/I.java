import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class I {

    static StringBuilder sb = new StringBuilder();

    static void sort(ArrayList<Integer> as, boolean tr) {
        Collections.sort(as);
        for (Integer a : as) {
            sb.append(a).append(" ");
        }
        sb.append("\n");
        if (tr) {
            System.out.println(sb);
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] in = new int[n];
        int max = 0;
        for (int k = 0; k < n; k++) {
            in[k] = sc.nextInt();
            if (max < in[k]) {
                max = in[k];
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
        for (int i = 0; i < in.length; i++) {
            int pos = in[i];
            ArrayList<Integer> tmp = new ArrayList<>();
            while (in[i] != 1) {
                while (in[i] % ints[pos] == 0) {
                    in[i] /= ints[pos];
                    tmp.add(ints[pos]);
                }
                pos = in[i];
            }
            sort(tmp, i == in.length - 1);
        }
    }
}