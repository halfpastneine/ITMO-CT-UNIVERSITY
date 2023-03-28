import java.util.*;

public class J {

    static HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
    static Pair[] weight;
    static boolean[] used;
    static int[] parent;
    static int tops;
    static int edge;
    static long sum = 0;

    static class Pair {
        int weight;
        int number;

        public Pair(int weight, int number) {
            this.weight = weight;
            this.number = number;
        }
    }

    static void read() {
        Scanner sc = new Scanner(System.in);
        tops = sc.nextInt();
        edge = sc.nextInt();
        weight = new Pair[edge];
        used = new boolean[tops + 1];
        parent = new int[tops + 1];
        for (int i = 1; i <= edge; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            graph.put(i, new ArrayList<>(Arrays.asList(a, b)));
            Pair x = new Pair(c, i);
            weight[i - 1] = x;
        }
        Arrays.sort(weight, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return Integer.compare(o1.weight, o2.weight);
            }
        });
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

    static void makeTree() {
        for (int i = 1; i <= tops; i++) {
            makeSet(i);
        }
        for (int i = 0; i < edge; i++) {
            int a = graph.get(weight[i].number).get(0);
            int b = graph.get(weight[i].number).get(1);
            if (findSet(a) != findSet(b)) {
                unionSets(a, b);
                sum += weight[i].weight;
            }
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {
        read();
        makeTree();
    }
}