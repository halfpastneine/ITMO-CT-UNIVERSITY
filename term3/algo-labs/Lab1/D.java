import javax.swing.*;
import java.util.*;

public class D {
    static int tops;

    static int edge;
    static HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
    static int[] answer;
    static Stack<Integer> stack = new Stack<>();
    static boolean[] used;
    static int[] tin;
    static int[] up;
    static int time = 0;
    static int count = 0;
    static int last;

    private static void print() {
        System.out.println(count);
        for (Integer integer : answer) {
            System.out.print(integer + " ");
        }
    }

    private static void setAnswer(int top) {
        last = -1;
        count++;
        while (stack.size() != 0 && last != top) {
            answer[stack.peek() - 1] = count;
            last = stack.peek();
            stack.pop();
        }
    }

    static void read() {
        Scanner sc = new Scanner(System.in);
        tops = sc.nextInt();
        edge = sc.nextInt();
        used = new boolean[tops + 1];
        tin = new int[tops + 1];
        up = new int[tops + 1];
        answer = new int[tops];
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
        }
    }

    private static void DFS(int top, int prev) {
        stack.push(top);
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
                        int cnt = 0;
                        for (Integer integer1 : graph.get(top)) {
                            if (Objects.equals(integer1, integer)) cnt++;
                        }
                        if (cnt == 1) {
                            setAnswer(integer);
                        }
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
                setAnswer(i);
            }
        }
    }

    public static void main(String[] args) {
        read();
        startDFS();
        print();
    }
}