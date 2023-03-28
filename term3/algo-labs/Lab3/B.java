import java.util.*;

public class B {

    static int tops;
    static int edge;
    static int[] answer;
    static boolean[] used;
    static HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
    static Queue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));

    static class Pair {
        int weight;
        int number;

        public Pair(int weight, int number) {
            this.weight = weight;
            this.number = number;
        }
    }


    private static void Djkstra(int v) {
        for (int i = 0; i < graph.get(v).size(); i += 2) {
            int a = graph.get(v).get(i);
            int b = graph.get(v).get(i + 1);
            if (answer[a] > answer[v] + b) {
                answer[a] = answer[v] + b;
                pq.add(new Pair(answer[a], a));
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        tops = sc.nextInt();
        edge = sc.nextInt();
        answer = new int[tops + 1];
        Arrays.fill(answer, Integer.MAX_VALUE);
        used = new boolean[tops + 1];
        for (int i = 1; i <= tops; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int i = 0; i < edge; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            graph.get(a).add(b);
            graph.get(a).add(c);
            graph.get(b).add(a);
            graph.get(b).add(c);
        }
        answer[1] = 0;
        pq.add(new Pair(0, 1));
        for (int i = 2; i <= tops; i++) {
            pq.add(new Pair(Integer.MAX_VALUE, i));
        }
        while (pq.size() != 0) {
            Pair a = pq.poll();
            if (a.weight == answer[a.number]) {
                Djkstra(a.number);
            }
        }
        for (int i = 1; i < answer.length; i++) {
            System.out.print(answer[i] + " ");
        }
    }
}
