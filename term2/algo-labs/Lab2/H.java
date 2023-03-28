import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

public class H {

    static class Node {
        long key;
        int pr;
        long sum;
        Node left;
        Node right;


        public Node(long key, int pr, long sum, Node left, Node right) {
            this.key = key;
            this.pr = pr;
            this.sum = sum;
            this.left = left;
            this.right = right;
        }
    }

    static class Pair {
        Node first;
        Node second;

        public Pair(Node first, Node second) {
            this.first = first;
            this.second = second;
        }
    }


    public static Node root = null;

    static void updateSum(Node node) {
        if (node != null) {
            if (node.left == null && node.right == null) {
                node.sum = 1;
            } else if (node.left == null) {
                node.sum = 1 + node.right.sum;
            } else if (node.right == null) {
                node.sum = 1 + node.left.sum;
            } else {
                node.sum = 1 + node.left.sum + node.right.sum;
            }
        }
    }

    static long getSum(Node node) {
        if (node == null) {
            return 0;
        }
        return node.sum;
    }

    static Node merge(Node a, Node b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        if (a.pr > b.pr) {
            a.right = merge(a.right, b);
            updateSum(a);
            return a;
        } else {
            b.left = merge(a, b.left);
            updateSum(b);
            return b;
        }
    }


    static Pair split(Node node, long key) {
        Pair c;
        if (node == null) {
            return new Pair(null, null);
        } else {
            if (getSum(node.left) < key) {
                c = split(node.right, key - getSum(node.left) - 1);
                node.right = c.first;
                c.first = node;
                updateSum(c.first);
            } else {
                c = split(node.left, key);
                node.left = c.second;
                c.second = node;
                updateSum(c.second);
            }
            return c;
        }
    }

    static Node insert(Node node, int index, long value, int count) {
        Pair a;
        a = split(node, index);
        Node n = new Node(value, count, 1, null, null);
        return merge(merge(a.first, n), a.second);
    }

    static Node delete(Node node, int index) {
        Pair a, b;
        a = split(node, index);
        b = split(a.second, 1);
        return merge(a.first, b.second);
    }

    static long getIndex(Node node, int index) {
        Pair a, b;
        a = split(node, index);
        b = split(a.second, 1);
        long res = b.first.key;
        root = merge(a.first, merge(b.first, b.second));
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int g = sc.nextInt();
        int n = sc.nextInt();
        int[] ints = new int[g];
        Random random = new Random();
        for (int i = 0; i < g; i++) {
            root = insert(root, i, sc.nextInt(), random.nextInt());
        }
        for (int i = 0; i < n; i++) {
            switch (sc.next()) {
                case "add":
                    int index = sc.nextInt();
                    long k = sc.nextInt();
                        root = insert(root, index, k, random.nextInt());
                    break;
                case "del":
                    root = delete(root, sc.nextInt() - 1);
            }
        }
        if (root == null) {
            System.out.println(0);
        } else {
            System.out.println(root.sum);
            for (int i = 0; i < root.sum; i++) {
                System.out.print(getIndex(root, i) + " ");
            }
        }
    }
}