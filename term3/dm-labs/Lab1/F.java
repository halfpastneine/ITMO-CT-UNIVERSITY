import java.util.*;

public class F {

    static int vertexQuantity;
    static int[] degree;
    static ArrayList<Integer> graph = new ArrayList<>();
    static StringBuilder answer = new StringBuilder();
    static PriorityQueue<Integer> queue = new PriorityQueue<>();

    private static void read() {
        Scanner sc = new Scanner(System.in);
        vertexQuantity = sc.nextInt();
        degree = new int[vertexQuantity + 1];
        for (int i = 0; i < vertexQuantity - 2; i++) {
            int k = sc.nextInt();
            graph.add(k);
            degree[k]++;
        }
        for (int i = 1; i <= vertexQuantity; i++) {
            if (degree[i] == 0)
                queue.add(i);
        }
    }

    private static void pruferToGraph() {
        for (Integer integer : graph) {
            answer.append(queue.poll()).append(" ").append(integer).append('\n');
            degree[integer]--;
            if (degree[integer] == 0) {
                queue.add(integer);
            }
        }
        answer.append(queue.poll()).append(" ").append(queue.poll());
        System.out.println(answer);
    }

    public static void main(String[] args) {
        read();
        pruferToGraph();
    }
}
