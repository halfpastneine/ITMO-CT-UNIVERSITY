import java.util.*;

public class I {

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static Pair[] graph;
    static double[] distance;
    static boolean[] used;
    static int n;
    static double sum = 0;

    static double Euclyde_distance(Pair a, Pair b) {
        return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
    }

    static int get_min() {
        double d = Double.MAX_VALUE;
        int vertex = -1;
        for (int i = 1; i < n; i++) {
            if (d > distance[i] && !used[i]) {
                d = distance[i];
                vertex = i;
            }
        }
        return vertex;
    }

    private static void read() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        graph = new Pair[n];
        used = new boolean[n];
        distance = new double[n];
        int a = sc.nextInt();
        int b = sc.nextInt();
        graph[0] = new Pair(a, b);
        distance[0] = 0;
        for (int i = 1; i < n; i++) {
            int c = sc.nextInt();
            int d = sc.nextInt();
            graph[i] = new Pair(c, d);
            distance[i] = Euclyde_distance(graph[i], graph[0]);
        }
        used[0] = true;
    }

    private static void Prim() {
        for (int i = 1; i < n; i++) {
            int v = get_min();
            sum += distance[v];
            used[v] = true;
            for (int j = 0; j < n; j++) {
                if (!used[j] && distance[j] > Euclyde_distance(graph[j], graph[v])) {
                    distance[j] = Euclyde_distance(graph[j], graph[v]);
                }
            }
        }
        System.out.println(sum);
    }

    public static void main(String[] args) {
        read();
        Prim();
    }

}
