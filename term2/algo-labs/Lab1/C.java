import java.util.Scanner;

public class C {


    static class Node {
        int left;
        int right;
        int sum;
        int bestSum;

        public Node(int left, int right, int sum, int bestSum) {
            this.left = left;
            this.right = right;
            this.sum = sum;
            this.bestSum = bestSum;
        }
    }

    static void buildTree(Node[] node, int n, int z) {
        for (int i = z - 1; i > 0; i--) {
            node[i].sum = node[2 * i].sum + node[2 * i + 1].sum;
            node[i].left = Math.max(node[2 * i].left, node[2 * i].sum + node[2 * i + 1].right);
            node[i].right = Math.max(node[2 * i + 1].right, node[2 * i + 1].sum + node[2 * i + 1].left);
            node[i].bestSum = Math.max(Math.max(node[2 * i].bestSum, node[2 * i + 1].bestSum),
                    node[2 * i].left + node[2 * i + 1].right);
        }
    }

    static void set(Node[] node, int n, int z, int pos, int value) {
        node[pos + node.length / 2].sum = value;
        if (pos % 2 == 1) {
            pos--;
        }
        int k = pos + node.length / 2;
        pos = k;
        while (k != 1) {
            if (pos % 2 == 1) {
                pos--;
            }
            k = k >> 1;
            node[k].sum = node[pos].sum + node[pos + 1].sum;
            node[k].left = Math.max(node[pos].left, node[pos].sum + node[pos + 1].right);
            node[k].right = Math.max(node[pos + 1].right, node[pos + 1].sum + node[pos + 1].left);
            node[k].bestSum = Math.max(Math.max(node[pos].bestSum, node[pos + 1].bestSum),
                    node[pos].left + node[pos + 1].right);
            pos = k;
        }
    }



    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int g = 0;
        while (n > Math.pow(2, g)) {
            g++;
        }
        int z = (int) Math.pow(2, g);
        Node[] node = new Node[2 * z];
        for (int i = z; i < z + n; i++) {
            int tmp = sc.nextInt();
            int q = Math.max(tmp, 0);
            node[i] = new Node(tmp, q, q, q);
        }
        for (int i = z + n; i < node.length; i++) {
            node[i] = new Node(0, 0, 0, 0);
        }
    }
}
