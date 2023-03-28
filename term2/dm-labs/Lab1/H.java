import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class H {

    static int[][] delta;
    static boolean[] visited;
    static boolean[] vis1;
    static HashMap<String, Integer> h;

    static class Pair {
        ArrayList<Integer> first;
        String second;

        public Pair(ArrayList<Integer> first, String second) {
            this.first = first;
            this.second = second;
        }
    }

    static boolean[] df(int k) {
        vis1[k] = true;
        for (int i = 0; i <= h.size(); i++) {
            if (delta[k][i + 1] != 0) {
                if (!vis1[delta[k][i + 1]]) {
                    df(delta[k][i + 1]);
                }
            }
        }
        return vis1;
    }

    static boolean[] dfs(int k) {
        visited[k] = true;
        for (int i = 0; i <= h.size(); i++) {
            if (delta[k][i + 1] != 0) {
                if (!visited[delta[k][i + 1]]) {
                    dfs(delta[k][i + 1]);
                }
            }
        }
        return visited;
    }

    static ArrayList<ArrayList<Integer>> split(ArrayList<Integer> C, ArrayList<Integer> R, String a) {
        ArrayList<ArrayList<Integer>> RR = new ArrayList<>();
        ArrayList<Integer> R1 = new ArrayList<>();
        ArrayList<Integer> R2 = new ArrayList<>();
        int tmp = h.get(a);
        for (Integer integer : R) {
            if (C.contains(delta[integer][tmp])) {
                R1.add(integer);
            } else {
                R2.add(integer);
            }
        }
        RR.add(R1);
        RR.add(R2);
        return RR;
    }

    public static void main(String[] args) throws IOException {
        FileReader rd = new FileReader("minimization.in");
        Scanner sc = new Scanner(rd);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int k = sc.nextInt();
        ArrayList<Integer> term = new ArrayList<>();
        ArrayList<Integer> non_term = new ArrayList<>();
        HashSet<Integer> hs = new HashSet<>();
        for (int i = 0; i < k; i++) {
            int gg = sc.nextInt();
            hs.add(gg);
        }
        ArrayList<ArrayList<Integer>> P = new ArrayList<>();
        P.add(non_term);
        P.add(term);
        delta = new int[n + 1][40];
        visited = new boolean[n + 1];
        h = new HashMap<>(40);


        HashMap<Integer, String> hm = new HashMap<>();
        int count = 1;
        for (int i = 0; i < m; i++) {
            int tmp = sc.nextInt();
            int tmp1 = sc.nextInt();
            String ch = sc.next();
            if (!h.containsKey(ch)) {
                h.put(ch, count);
                hm.put(count, ch);
                count++;
            }
            delta[tmp][h.get(ch)] = tmp1;
        }
        boolean[] vis = new boolean[n + 1];
        vis1 = new boolean[n + 1];
        vis1 = df(1);
        if (hs.contains(1)) {
            term.add(1);
        } else {
            non_term.add(1);
        }
        vis[1] = true;
        for (int i = 2; i <= n; i++) {
            if (vis1[i]) {
                visited = dfs(i);
                boolean tr = false;
                for (Integer integer : hs) {
                    if (visited[integer]) {
                        tr = true;
                        if (hs.contains(i)) {
                            term.add(i);
                        } else {
                            non_term.add(i);
                        }
                        break;
                    }
                }
                vis[i] = tr;
                Arrays.fill(visited, false);
            }
        }
//        System.out.println(Arrays.toString(vis));
        LinkedList<Pair> ll = new LinkedList<>();
        for (int i = 0; i < h.size(); i++) {
            ll.add(new Pair(non_term, hm.get(i + 1)));
            ll.add(new Pair(term, hm.get(i + 1)));
        }
        while (ll.size() != 0) {
            Pair a = ll.poll();
            for (int i = 0; i < P.size(); i++) {
                ArrayList<ArrayList<Integer>> as = split(a.first, P.get(i), a.second);
                if (as.get(0).size() > 0 && as.get(1).size() > 0) {
                    P.remove(P.get(i));
                    P.add(i, new ArrayList<>(as.get(0)));
                    P.add(i + 1, new ArrayList<>(as.get(1)));
                    for (int j = 0; j < h.size(); j++) {
                        ll.add(new Pair(as.get(0), hm.get(j + 1)));
                        ll.add(new Pair(as.get(1), hm.get(j + 1)));
                    }
                }
            }
        }
        P.remove(new ArrayList<>());
        HashMap<Integer, Integer> im = new HashMap<>();
        HashMap<ArrayList<Integer>, Integer> am = new HashMap<>();
        int c = 2;
        StringBuilder sb1 = new StringBuilder();
        HashSet<ArrayList<Integer>> ha = new HashSet<>();
        boolean rr = false;
        int lk = 0;
        for (ArrayList<Integer> integers : P) {
            boolean tr = false;
            for (int j = 0; j < integers.size(); j++) {
                if (!rr) {
                    if (integers.contains(1)) {
                        im.put(integers.get(j), 1);
                    } else {
                        im.put(integers.get(j), c);
                    }
                    if (hs.contains(integers.get(j)) && !tr) {
                        tr = true;
                    }
                } else {
                    im.put(integers.get(j), c);
                    if (hs.contains(integers.get(j)) && !tr) {
                        tr = true;
                    }
                }
            }
            if (!rr) {
                if (integers.contains(1)) {
                    am.put(integers, 1);
                    rr = true;
                    if (tr) {
                        sb1.append(1 + " ");
                        lk++;
                    }
                } else {
                    am.put(integers, c);
                    c++;
                    if (tr) {
                        sb1.append(c - 1 + " ");
                    }
                }
            } else {
                am.put(integers, c);
                c++;
                if (tr) {
                    sb1.append(c - 1 + " ");
                }
            }

        }
//        System.out.println(P);
        StringBuilder sb = new StringBuilder();
        int co = 0;
        for (ArrayList<Integer> integers : P) {
            int z = am.get(integers);
            for (int j = 0; j < h.size(); j++) {
                if (delta[integers.get(0)][j + 1] != 0) {
                    if (im.get(delta[integers.get(0)][j + 1]) != null) {
                        sb.append(z + " " + im.get(delta[integers.get(0)][j + 1]) + " " + hm.get(j + 1) + "\n");
                        co++;
                    }
                }
            }
        }
        FileWriter wr = new FileWriter("minimization.out");
        wr.write(P.size() + " " + co + " " + ha.size() + "\n");
        wr.write(sb1.toString());
        wr.write("\n");
        wr.write(sb.toString());
        wr.close();
        sc.close();
    }
}