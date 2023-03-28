import java.util.Random;
import java.util.*;

public class F {

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
                node.sum = node.key;
            } else if (node.left == null) {
                node.sum = node.key + node.right.sum;
            } else if (node.right == null) {
                node.sum = node.key + node.left.sum;
            } else {
                node.sum = node.key + node.left.sum + node.right.sum;
            }
        }
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
            if (node.key < key) {
                c = split(node.right, key);
                node.right = c.first;
                c.first = node;
            } else {
                c = split(node.left, key);
                node.left = c.second;
                c.second = node;
            }
            updateSum(c.first);
            updateSum(c.second);
            return c;
        }
    }

    static Node insert(Node node, long value, int count) {
        Pair a;
        a = split(node, value);
        Node n = new Node(value, count, 0, null, null);
        a.first = merge(a.first, n);
        return merge(a.first, a.second);
    }

    static long sum(Node node, int l, int r) {
        Pair a;
        Pair b;
        a = split(node, l);
        b = split(a.second, r + 1);
        if (b.first == null) {
            System.out.println(0);
            merge(a.first, merge(b.first, b.second));
            return 0;
        } else {
            System.out.println(b.first.sum);
            long sum = b.first.sum;
            merge(a.first, merge(b.first, b.second));
            return sum;
        }
    }

    public static void main(String[] args) {
        HashSet<Long> hs = new HashSet<>();
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long sum = 0;
        int count = 20;
        boolean tr = false;
        hs.add(Long.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            switch (sc.next()) {
                case "+":
                    long k = sc.nextInt();
                    if (tr) {
                        k = k + sum;
                        k = (long) (k % Math.pow(10, 9));
                        tr = false;
                    }
                    if (!hs.contains(k)) {
                        count++;
                        root = insert(root, k, count);
                        hs.add(k);
                    }
                    break;
                case "?":
                    sum = sum(root, sc.nextInt(), sc.nextInt());
                    tr = true;
                    break;
            }
        }
    }

}