import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class A {

    static int tops;
    static int edge;
    static HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
    static int[] color;
    static boolean check_cycle = false;
    static ArrayList<Integer> newTops;

    static void read() {
        Scanner sc = new Scanner(System.in);
        tops = sc.nextInt();
        edge = sc.nextInt();
        color = new int[tops + 1];
        newTops = new ArrayList<>();
        for (int i = 1; i <= edge; i++) {
            int vertex1 = sc.nextInt();
            int vertex2 = sc.nextInt();
            if (!graph.containsKey(vertex1)) {
                graph.put(vertex1, new ArrayList<>());
            }
            graph.get(vertex1).add(vertex2);
        }
    }

    static void print() {
        for (int i = newTops.size() - 1; i >= 0; i--) {
            System.out.print(newTops.get(i) + " ");
        }
    }

    static void checkCycle(int top) {
        color[top] = 1;
        if (graph.containsKey(top)) {
            ArrayList<Integer> as = graph.get(top);
            for (Integer integer : as) {
                if (color[integer] == 0) {
                    checkCycle(integer);
                }
                if (color[integer] == 1) {
                    check_cycle = true;
                    break;
                }
            }
        }
        color[top] = 2;
    }

    static void startCheckCycle() {
        for (int i = 1; i <= tops; i++) {
            if (color[i] == 0) {
                checkCycle(i);
            }
        }
        if (check_cycle) {
            System.out.println(-1);
        } else {
            color = new int[tops + 1];
        }
    }

    static void DFS(int top) {
        if (graph.containsKey(top)) {
            ArrayList<Integer> as = graph.get(top);
            for (Integer integer : as) {
                if (color[integer] != 1) {
                    color[integer] = 1;
                    DFS(integer);
                }
            }
        }
        color[top] = 1;
        newTops.add(top);
    }

    static void startDFS() {
        for (int i = 1; i <= tops; i++) {
            if (color[i] == 0) {
                DFS(i);
            }
        }
        print();
    }

    public static void main(String[] args) {
        read();
        startCheckCycle();
        if (!check_cycle) {
            startDFS();
        }
    }
}
