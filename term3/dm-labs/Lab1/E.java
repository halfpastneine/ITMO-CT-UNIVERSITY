import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class E {

    static int vertexQuantity;
    static StringBuilder answer = new StringBuilder();
    static HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
    static PriorityQueue<Integer> queue = new PriorityQueue<>();
    static boolean[] removedVertex;
    static int[] degree;

    static void pruferTree() {
        int count = 0;
        while (count != vertexQuantity - 2) {
            if (queue.size() != 0) {
                int least = queue.poll();
                removedVertex[least] = true;
                int min;
                int vertex = Integer.MAX_VALUE;
                for (int i = 0; i < graph.get(least).size(); i++) {
                    min = graph.get(least).get(i);
                    if (vertex > min && !removedVertex[min])
                        vertex = min;
                }
                degree[vertex]--;
                if (degree[vertex] == 1) {
                    queue.add(vertex);
                }
                answer.append(vertex).append(" ");
                count++;
            }
        }
        System.out.println(answer);
    }

    static void read() {
        Scanner sc = new Scanner(System.in);
        vertexQuantity = sc.nextInt();
        removedVertex = new boolean[vertexQuantity + 1];
        degree = new int[vertexQuantity + 1];
        for (int i = 1; i <= vertexQuantity; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int i = 0; i < vertexQuantity - 1; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
            graph.get(b).add(a);
        }
        for (int i = 1; i <= vertexQuantity; i++) {
            degree[i] = graph.get(i).size();
            if (degree[i] == 1) {
                queue.add(i);
            }
        }
    }

    public static void main(String[] args) {
        read();
        pruferTree();
    }
}

//   2 3 3
//         1
//        /
//   4   2
//    \ /
//     3
//     |
//     5