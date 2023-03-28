import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class O {

    static class Node {
        int left;
        int right;
        int pos;

        public Node(int left, int right, int pos) {
            this.left = left;
            this.right = right;
            this.pos = pos;
        }
    }


    static void pr(Node[] node) {
        System.out.print("[");
        for (Node value : node) {
            System.out.print("[" + value.left + ", " + value.right + ", " + value.pos + "] ");
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        Node[] node = new Node[2 * k];
        for (int i = 0; i < 2 * k; i += 2) {
            node[i] = new Node(sc.nextInt(), 1, i);
            node[i + 1] = new Node(sc.nextInt(), -1, i);
        }
        Arrays.sort(node, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.left == o2.left) {
                    if (o1.right == o2.right) {
                        if (o1.right == 1) {
                            return o2.pos - o1.pos;
                        } else {
                            return o1.pos - o2.pos;
                        }
                    }
                    return o2.right - o1.right;
                }
                return o1.left - o2.left;
            }
        });
//        pr(node);
        int count = 0;
        int ans = 0;
        int i = 0;
        while (i < node.length) {
            int tmp = node[i].pos;
            boolean tr = false;
            while (i < node.length - 1 && tmp >= node[i + 1].pos) {
//                System.out.println("-----" + i);
                if (tmp == node[i + 1].pos) {
                    ans++;
//                    System.out.println(ans + " " + node[i + 1].pos);
                    i += 2;
                    tr = true;
                    break;
                }
                i++;
                tr = true;
            }
            if (!tr) {
                i++;
            }
        }
        System.out.println(ans);
    }
}