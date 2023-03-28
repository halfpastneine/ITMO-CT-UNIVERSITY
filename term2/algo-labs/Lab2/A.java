import java.util.Scanner;

public class A {

    static class Node {
        int key;
        int left;
        int right;

        public Node(int key, int left, int right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }


    static boolean isBinSearchTree(Node[] node, int i, int min_value, int max_value) {
        if (i == -1) {
            return true;
        }
        if (node[i].key <= min_value || node[i].key >= max_value) {
            return false;
        }
        return isBinSearchTree(node, node[i].left, min_value, node[i].key) &&
        isBinSearchTree(node, node[i].right, node[i].key, max_value);
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Node[] node = new Node[n + 1];
        for (int i = 1; i <= n; i++) {
            node[i] = new Node(sc.nextInt(), sc.nextInt(), sc.nextInt());
        }
        int k = sc.nextInt();
        boolean tr = isBinSearchTree(node, k, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (tr) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
