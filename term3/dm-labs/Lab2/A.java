import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class A {


    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(Paths.get("destroy.in"));
        int n = sc.nextInt();
        HashMap<Long, ArrayList<Long>> hs = new HashMap<>();
        ArrayList<Long> as = new ArrayList<>();
        TreeSet<Long> tt = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            long a = sc.nextInt();
            long b = sc.nextInt();
            if (!hs.containsKey(b)) {
                ArrayList<Long> tmp = new ArrayList<>();
                tmp.add(a);
                hs.put(b, tmp);
                as.add(b);
            } else {
                hs.get(b).add(a);
            }
        }
        sc.close();
        as.sort(Collections.reverseOrder());
        for (long i = 1; i <= n; i++) {
            tt.add(i);
        }
        long sum = 0;
        for (int j = 0; j < as.size(); j++) {
            long h = as.get(j);
            ArrayList<Long> tmp = hs.get(h);
            Collections.sort(tmp);
            for (int i = 0; i < tmp.size(); i++) {
                if (tt.floor(tmp.get(i)) == null) {
                    sum += h;
                } else {
                    tt.remove(tt.floor(tmp.get(i)));
                }
            }
        }
        FileWriter fileWriter = new FileWriter("destroy.out");
        fileWriter.write((String.valueOf(sum)));
        fileWriter.close();
    }
}
