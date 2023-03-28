import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class C {

    static int n;
    static boolean[] used;
    static int[] parSoch;
    static ArrayList<Integer> weight = new ArrayList<>();
    static HashMap<Integer, ArrayList<Integer>> weightCon = new HashMap<>();
    static HashMap<Integer, ArrayList<Integer>> edges = new HashMap<>();

    static boolean dfs(int v) {
        if (used[v]) return false;
        used[v] = true;
        for (Integer integer : edges.get(v)) {
            if (parSoch[integer] == 0) {
                parSoch[integer] = v;
                return true;
            } else if (dfs(parSoch[integer])) {
                parSoch[integer] = v;
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(Paths.get("destroy.in"));
        n = sc.nextInt();
        parSoch = new int[n + 1];
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            if (weightCon.containsKey(a)) {
                weightCon.get(a).add(i + 1);
            } else {
                weight.add(a);
                ArrayList<Integer> as = new ArrayList<>();
                as.add(i + 1);
                weightCon.put(a, as);
            }
        }
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            ArrayList<Integer> as = new ArrayList<>();
            for (int j = 0; j < a; j++) {
                as.add(sc.nextInt());
            }
            edges.put(i + 1, as);
        }
        sc.close();
        weight.sort(Collections.reverseOrder());
        for (Integer value : weight) {
            for (Integer integer : weightCon.get(value)) {
                used = new boolean[n + 1];
                dfs(integer);
            }
        }
        FileWriter fileWriter = new FileWriter("destroy.out");
        int[] massiv = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            massiv[parSoch[i]] = i;
        }
        for (int i = 1; i <= n; i++) {
            fileWriter.write(massiv[i] + " ");
        }
        fileWriter.close();
    }
}
