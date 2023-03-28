import java.util.*;

public class B {

    static int tops;
    static int edge;
    static HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
    static HashMap<List<Integer>, Integer> edgeNumber = new HashMap<>();
    static ArrayList<Integer> answer = new ArrayList<>();
    static boolean[] used;
    static int[] tin;
    static int[] up;
    static int time = 0;

    private static void print() {
        Collections.sort(answer);
        System.out.println(answer.size());
        for (Integer integer : answer) {
            System.out.print(integer + " ");
        }
    }

    static void read() {
        Scanner sc = new Scanner(System.in);
        tops = sc.nextInt();
        edge = sc.nextInt();
        used = new boolean[tops + 1];
        tin = new int[tops + 1];
        up = new int[tops + 1];
        for (int i = 1; i <= edge; i++) {
            int vertex1 = sc.nextInt();
            int vertex2 = sc.nextInt();
            if (!graph.containsKey(vertex1)) {
                graph.put(vertex1, new ArrayList<>());
            }
            if (!graph.containsKey(vertex2)) {
                graph.put(vertex2, new ArrayList<>());
            }
            graph.get(vertex1).add(vertex2);
            graph.get(vertex2).add(vertex1);
            edgeNumber.put(Arrays.asList(vertex1, vertex2), i);
            edgeNumber.put(Arrays.asList(vertex2, vertex1), i);
        }
    }

    private static void DFS(int top, int prev) {
        used[top] = true;
        time = time + 1;
        tin[top] = up[top] = time;
        if (graph.containsKey(top)) {
            for (Integer integer : graph.get(top)) {
                if (integer == prev) continue;
                if (!used[integer]) {
                    DFS(integer, top);
                    up[top] = Math.min(up[top], up[integer]);
                    if (up[integer] > tin[top]) {
                        answer.add(edgeNumber.get(Arrays.asList(top, integer)));
                    }
                } else {
                    up[top] = Math.min(up[top], tin[integer]);
                }
            }
        }
    }

    static void startDFS() {
        for (int i = 1; i <= tops; i++) {
            if (!used[i]) {
                DFS(i, -1);
            }
        }
    }

    public static void main(String[] args) {
        read();
        startDFS();
        print();
    }
}
