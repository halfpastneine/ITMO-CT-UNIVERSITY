import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Paths;
import java.util.*;

public class B {

    static HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
    static Pair[] weight;
    static boolean[] used;
    static int[] parent;
    static int[] newEdges;
    static int tops;
    static int edge;
    static long s;
    static BigInteger sumWeight = new BigInteger("0");

    static class Pair {
        long weight;
        int number;

        public Pair(long weight, int number) {
            this.weight = weight;
            this.number = number;
        }
    }

    static void read() throws IOException {
        Scanner sc = new Scanner(Paths.get("destroy.in"));
        tops = sc.nextInt();
        edge = sc.nextInt();
        s = sc.nextLong();
        weight = new Pair[edge];
        used = new boolean[tops + 1];
        newEdges = new int[edge];
        parent = new int[tops + 1];
        for (int i = 1; i <= edge; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            long c = sc.nextLong();
            sumWeight = sumWeight.add(BigInteger.valueOf(c));
            graph.put(i, new ArrayList<>(Arrays.asList(a, b)));
            Pair x = new Pair(c, i);
            weight[i - 1] = x;
        }
        sc.close();
        Arrays.sort(weight, (o1, o2) -> Long.compare(o2.weight, o1.weight));
    }


    static void makeSet(int top) {
        parent[top] = top;
    }

    static int findSet(int top) {
        if (parent[top] == top) {
            return top;
        }
        return parent[top] = findSet(parent[top]);
    }

    static void unionSets(int a, int b) {
        a = findSet(a);
        b = findSet(b);
        if (a != b) {
            parent[b] = a;
        }
    }

    static void makeTree() throws IOException {
        for (int i = 1; i <= tops; i++) {
            makeSet(i);
        }
        ArrayList<Pair> q = new ArrayList<>();
        BigInteger bigInteger = new BigInteger(String.valueOf(0));
        for (int i = 0; i < edge; i++) {
            int a = graph.get(weight[i].number).get(0);
            int b = graph.get(weight[i].number).get(1);
            if (findSet(a) != findSet(b)) {
                unionSets(a, b);
                bigInteger = bigInteger.add(BigInteger.valueOf(weight[i].weight));
            } else {
                q.add(new Pair(weight[i].weight, weight[i].number));
            }
        }
        FileWriter fileWriter = new FileWriter("destroy.out");
        if (bigInteger.compareTo(sumWeight.subtract(BigInteger.valueOf(s))) > -1) {
            fileWriter.write(q.size() + "\n");
            ArrayList<Integer> g =new ArrayList<>();
            for (Pair pair : q) {
                g.add(pair.number);
            }
            Collections.sort(g);
            for (Integer integer : g) {
                fileWriter.write(integer + " ");
            }
        } else {
            q.sort(Comparator.comparing(p -> p.weight));
            int l = 0;
            for (int i = 0; i < q.size(); i++) {
                s -= q.get(i).weight;
                if (s < 0) {
                    l = i;
                    break;
                } else if (s == 0) {
                    l = i + 1;
                    break;
                }
            }
            fileWriter.write(l + "\n");
            ArrayList<Integer> g =new ArrayList<>();
            for (int i = 0; i < l; i++) {
                g.add(q.get(i).number);
            }
            Collections.sort(g);
            for (int i = 0; i < l; i++) {
                fileWriter.write(g.get(i) + " ");
            }
        }
        fileWriter.close();
    }

    public static void main(String[] args) throws IOException {
        read();
        makeTree();
    }
}