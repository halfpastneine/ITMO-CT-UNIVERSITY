import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class G {

    static int vertexQuantity;
    static int edgeQuantity;
    static int[] degree;
    static int[] color;
    static int[] c;
    static boolean[] used;
    static int min;
    static int max = -1;
    static HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();


    private static void read() {
        Scanner sc = new Scanner(System.in);
        vertexQuantity = sc.nextInt();
        edgeQuantity = sc.nextInt();
        degree = new int[vertexQuantity + 1];
        color = new int[vertexQuantity + 1];
        used = new boolean[vertexQuantity + 1];
        for (int i = 1; i <= vertexQuantity; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int i = 0; i < edgeQuantity; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph.get(a).add(b);
            graph.get(b).add(a);
            degree[a]++;
            degree[b]++;
        }
        int m = Integer.MAX_VALUE;
        for (int i = 1; i < degree.length; i++) {
            if (m > degree[i]) {
                m = degree[i];
                min = i;
            }
            if (max < degree[i])
                max = degree[i];
        }
        c = new int[max + 1];
        for (int i = 0; i < c.length; i++) {
            c[i] = i + 1;
        }
    }

    private static void DFS(int top) {
        for (Integer integer : graph.get(top)) {
            if (!used[integer]) {
                for (int j : c) {
                    int count = 0;
                    for (Integer in : graph.get(integer)) {
                        if (color[in] != j) {
                            count++;
                        }
                    }
                    if (count == graph.get(integer).size()) {
                        used[integer] = true;
                        color[integer] = j;
                        DFS(integer);
                        break;
                    }
                }
            }
        }
    }

    private static void startDFS() {
        used[min] = true;
        color[min] = 1;
        DFS(min);
        if (max % 2 == 0)
            max++;
        StringBuilder sb = new StringBuilder();
        sb.append(max).append('\n');
        for (int i = 1; i < color.length; i++) {
            sb.append(color[i]).append('\n');
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        read();
        startDFS();
    }
}