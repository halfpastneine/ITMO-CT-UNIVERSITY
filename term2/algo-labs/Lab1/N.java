import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class N {

    static class Node {
        int left;
        int right;

        public Node(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }


    static void pr(Node[] node) {
        System.out.print("[");
        for (Node value : node) {
            System.out.print("[" + value.left + ", " + value.right + "] ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Node[] node = new Node[2 * n + 2];
        int count = 0;
        node[0] = new Node(0, 0);
        for (int i = 1; i <= 2 * n; i += 2) {
            int t1 = sc.nextInt() * 3600 + sc.nextInt() * 60 + sc.nextInt();
            int t2 = sc.nextInt() * 3600 + sc.nextInt() * 60 + sc.nextInt();
            if (t2 < t1) {
                count++;
            }
            if (t2 == t1) {
                count++;
                node[i] = new Node(0, 1);
                node[i + 1] = new Node(0, -1);
            } else {
                node[i] = new Node(t1, 1);
                node[i + 1] = new Node(t2, -1);
            }

        }
        node[2 * n + 1] = new Node(24 * 3600, -1);
        Arrays.sort(node, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.left - o2.left;
            }
        });
//        pr(node);
        int ans = 0;
        for (int i = 0; i < node.length; i++) {
            count += node[i].right;
            if (count == n) {
                ans += node[i + 1].left - node[i].left;
            }
        }
        System.out.println(ans);
    }
}


