import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

public class F {

    static ArrayList<Integer> newTops = new ArrayList<>();
    static HashSet<ArrayList<Integer>> superEdge = new HashSet<>();
    static byte[][] graph;
    static boolean[] used;
    static int[] topColor;
    static int tops;
    static int edge;
    static int color = 1;

    private static void read() {
        Scanner sc = new Scanner(System.in);
        tops = sc.nextInt();
        edge = sc.nextInt();
        graph = new byte[tops + 1][tops + 1];
        used = new boolean[tops + 1];
        topColor = new int[tops + 1];
        for (int i = 1; i <= edge; i++) {
            graph[sc.nextInt()][sc.nextInt()] = 1;
        }
    }

    static void topSort(int top) {
        used[top] = true;
        for (int i = 1; i <= tops; i++) {
            if (graph[top][i] == 1 && !used[i]) {
                used[i] = true;
                topSort(i);
            }
        }
        newTops.add(0, top);
    }

    static void startTopSort() {
        for (int i = 1; i <= tops; i++) {
            if (!used[i]) {
                topSort(i);
            }
        }
    }

    static void reverseGraph() {
        for (int i = 1; i <= tops; i++) {
            for (int j = i; j <= tops; j++) {
                byte tmp = graph[i][j];
                graph[i][j] = graph[j][i];
                graph[j][i] = tmp;
            }
        }
    }

    static void DFS(int top, int color) {
        topColor[top] = color;
        for (int i = 1; i <= tops; i++) {
            if (graph[top][i] == 1 && topColor[i] == 0) {
                DFS(i, color);
            }
        }
    }

    static void startDFS() {
        for (Integer integer : newTops) {
            if (topColor[integer] == 0) {
                DFS(integer, color);
                color++;
            }
        }
        used = new boolean[tops + 1];
    }

    static void countEdges(int top) {
        used[top] = true;
        for (int i = 1; i <= tops; i++) {
            if (graph[top][i] == 1 && topColor[top] != topColor[i]) {
                superEdge.add(new ArrayList<>(Arrays.asList(topColor[top], topColor[i])));
            }
        }
    }

    static void startCountEdges() {
        for (int i = 1; i <= tops; i++) {
            if (!used[i]) {
                countEdges(i);
            }
        }
    }

    public static void main(String[] args) {
        read();
        startTopSort();
        reverseGraph();
        startDFS();
        startCountEdges();
        System.out.println(superEdge.size());

    }
}
