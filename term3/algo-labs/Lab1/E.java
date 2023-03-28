import java.util.*;

public class E {

    static int tops;
    static int edge;
    static HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
    static HashMap<List<Integer>, List<Integer>> edgeNumber = new HashMap<>();
    static int[] answer;
    static boolean[] used;
    static int[] tin;
    static int[] up;
    static int time = 0;
    static int maxColor = 0;

    static void insert(int a, int b, int i) {
        List<Integer> as;
        if (a >= b) {
            as = Arrays.asList(a, b);
        } else {
            as = Arrays.asList(b, a);
        }
        if (edgeNumber.containsKey(as)) {
            edgeNumber.get(as).add(i);
        } else {
            edgeNumber.put(as, new ArrayList<>(List.of(i)));
        }
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        sb.append(maxColor).append('\n');
        for (int j : answer) {
            sb.append(j).append(" ");
        }
        System.out.println(sb);
    }

    static void read() {
        Scanner sc = new Scanner(System.in);
        tops = sc.nextInt();
        edge = sc.nextInt();
        used = new boolean[tops + 1];
        tin = new int[tops + 1];
        up = new int[tops + 1];
        answer = new int[edge];
        for (int i = 1; i <= tops; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int i = 1; i <= edge; i++) {
            int vertex1 = sc.nextInt();
            int vertex2 = sc.nextInt();
            graph.get(vertex1).add(vertex2);
            graph.get(vertex2).add(vertex1);
            insert(vertex1, vertex2, i - 1);
        }
    }

    private static void DFS(int top, int prev) {
        used[top] = true;
        time = time + 1;
        tin[top] = up[top] = time;
        for (Integer integer : graph.get(top)) {
            if (integer == prev) continue;
            if (!used[integer]) {
                DFS(integer, top);
                up[top] = Math.min(up[top], up[integer]);
            } else {
                up[top] = Math.min(up[top], tin[integer]);
            }
        }
    }

    static void setColor(int a, int color, int b) {
        List<Integer> tmp;
        if (a < b) {
            tmp = edgeNumber.get(Arrays.asList(b, a));
        } else {
            tmp = edgeNumber.get(Arrays.asList(a, b));
        }
        for (Integer integer : tmp) {
            answer[integer] = color;
        }
    }

    static void paint(int top, int color, int prev) {
        used[top] = true;
        for (Integer integer : graph.get(top)) {
            if (integer == prev) continue;
            if (!used[integer]) {
                if (up[integer] >= tin[top]) {
                    maxColor++;
                    setColor(top, maxColor, integer);
                    paint(integer, maxColor, top);
                } else {
                    setColor(top, color, integer);
                    paint(integer, color, top);
                }
            } else if (tin[integer] < tin[top]) {
                setColor(top, color, integer);
            }
        }
    }

    static void startDFS() {
        for (int i = 1; i <= tops; i++) {
            if (!used[i]) {
                DFS(i, -1);
            }
        }
        used = new boolean[tops + 1];
        for (int i = 1; i <= tops; i++) {
            if (!used[i]) {
                paint(i, maxColor, -1);
            }
        }
    }

    public static void main(String[] args) {
        read();
        startDFS();
        print();
    }
}



