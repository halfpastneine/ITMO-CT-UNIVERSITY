import java.util.*;
public class M {

    static class Node {
        int left;
        int right;
        int g;

        public Node(int left, int right, int g) {
            this.left = left;
            this.right = right;
            this.g = g;
        }
    }

    static void pr(Node[] node) {
        System.out.print("[");
        for (Node value : node) {
            System.out.print("[" + value.left + ", " + value.right +  "] ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] ans = new int[k];
        Node[] node = new Node[n * 2 + k];
        for (int i = 0; i < 2 * n; i += 2) {
            int t1 = sc.nextInt();
            int t2 = sc.nextInt();
            if (t1 > t2) {
                node[i] = new Node(t2, 1, 1);
                node[i + 1] = new Node(t1, -1, 1);
            } else {
                node[i] = new Node(t1, 1, 1);
                node[i + 1] = new Node(t2, -1, 1);
            }
        }
        for (int i = 2 * n; i < 2 * n + k; i++) {
            node[i] = new Node(sc.nextInt(), 0, i - 2 * n);
        }
        Arrays.sort(node, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.left == o2.left) {
                    return o2.right - o1.right;
                }
                return o1.left - o2.left;
            }
        });
//        pr(node);

        int count = 0;
        int cnt = 0;
        for (Node value : node) {
            if (value.right == 0) {
                ans[value.g] = count;
            }
            count += value.right;
        }
        for (int an : ans) {
            System.out.print(an + " ");
        }
    }
}
