import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class D {

    static ArrayList<Integer> hamiltonWay = new ArrayList<>();
    static ArrayList<Integer> helper = new ArrayList<>();
    static int[][] graph;
    static int tops;

    private static void read() {
        Scanner sc = new Scanner(System.in);
        tops = sc.nextInt();
        graph = new int[tops][tops];
        sc.nextLine();
        for (int i = 0; i < tops; i++) {
            String x = sc.nextLine();
            for (int j = 0; j < x.length(); j++) {
                int y = x.charAt(j) - '0';
                if (y == 1) {
                    graph[i][j] = 1;
                } else {
                    graph[j][i] = 1;
                }
            }
        }
//        for (int i = 0; i < tops; i++) {
//            System.out.println(Arrays.toString(graph[i]));
//        }
    }

    private static void makeHamiltonWay() {
        hamiltonWay.add(0);
//        for (int i = 1; i < tops; i++) {
//            for (int j = 0; j < i; j++) {
//                if (graph[hamiltonWay.get(j)][i] == 0) {
//                    hamiltonWay.add(j, i);
//                    break;
//                }
//            }
//        }
        for (int i = 1; i < tops; ++i) {
            int j = 0;
            while (graph[hamiltonWay.get(j)][i] == 1) {
                j++;
                if (j == i)
                    break;
            }
            hamiltonWay.add(j, i);
        }
//        System.out.println(hamiltonWay);
    }

// u0 -> u1 -> u2
    private static void makeHamiltonCycle() {
        for (int i = hamiltonWay.size() - 1; i > 1; i--) {
            if (graph[hamiltonWay.get(i)][hamiltonWay.get(0)] == 1) {
                if (i == hamiltonWay.size() - 1) {
                    for (Integer integer : hamiltonWay) {
                        System.out.print((integer + 1) + " ");
                    }
                } else {
                    ArrayList<Integer> head = new ArrayList<>(hamiltonWay.subList(0, i + 1));
                    List<Integer> tail = hamiltonWay.subList(i + 1, hamiltonWay.size());
                    for (int k = 0; k < tail.size(); k++) {
                        for (int j = 0; j < head.size(); j++) {
                            if (graph[head.get(j)][tail.get(k)] == 1) {
                                if (helper.size() == 0) {
                                    head.add(j, tail.get(k));
                                } else {
                                    for (int l = helper.size() - 1; l >= 0; l--) {
                                        head.add(j, helper.get(l));
                                    }
                                    helper = new ArrayList<>();
                                }
                                break;
                            }
                            if (j == head.size() - 1) {
                                helper.add(tail.get(k));
                            }
                        }
                    }
                    for (Integer integer : head) {
                        System.out.print((integer + 1) + " ");
                    }
                }
                break;
            }
        }
    }

    public static void main(String[] args) {
        read();
        makeHamiltonWay();
        makeHamiltonCycle();
        System.out.println();
    }
}
