import java.util.Scanner;

public class B {

    static class Node {
        int value;
        int left;
        int right;

        public Node(int value, int left, int right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    static void buildBST(Node[] node, int i, int value, int count) {
        if (value > node[i].value) {
            if (node[i].right == -1) {
                node[i].right = count;
                node[count] = new Node(value, -1, -1);
            }
            buildBST(node, node[i].right, value, count);
        }
        if (value < node[i].value) {
            if (node[i].left == -1) {
                node[i].left = count;
                node[count] = new Node(value, -1, -1);
            }
            buildBST(node, node[i].left, value, count);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Node[] node = new Node[n + 1];
        int count = 2;
        node[1] = new Node(sc.nextInt(), -1, -1);
        for (int i = 2; i <= n; i++) {
            buildBST(node, 1, sc.nextInt(), count);
            count++;
        }
        System.out.println(n);
        for (int i = 1; i <= n; i++) {
            System.out.println(node[i].value + " " + node[i].left + " " + node[i].right);
        }
        System.out.println(1);
    }
}
