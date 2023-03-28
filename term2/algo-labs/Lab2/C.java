import java.util.Scanner;

public class C {
    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }


    public static Node root = null;

    static Node next(Node root, int value) {
        Node n = null;
        while (root != null) {
            if (value < root.value) {
                n = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return n;
    }

    static Node prev(Node root, int value) {
        Node n = null;
        while (true) {
            if (root == null) {
                break;
            }
            if (value > root.value) {
                n = root;
                root = root.right;
            } else {
                root = root.left;
            }
            if (root == null) {
                break;
            }
        }
        return n;
    }

    static Node delete(int value, Node root) {
        if (exists(value, root)) {
            if (value > root.value) {
                root.right = delete(value, root.right);
            } else if (value < root.value) {
                root.left = delete(value, root.left);
            } else {
                if (root.left == null && root.right == null) {
                    root = null;
                } else if (root.left == null) {
                    root = root.right;
                } else if (root.right == null) {
                    root = root.left;
                } else {
                    Node m = min(root.right);
                    root.value = m.value;
                    root.right = delete(root.value, root.right);
                }
            }
            return root;
        }
        return root;
    }

    static Node min(Node node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }

    static boolean exists(int value, Node root) {
        if (root == null) {
            return false;
        } else {
            if (value > root.value) {
                return exists(value, root.right);
            } else if (value == root.value) {
                return true;
            } else {
                return exists(value, root.left);
            }
        }
    }

    static void insert(int value) {
        if (root == null) {
            root = new Node(value, null, null);
        } else {
            Node cur = root;
            while (true) {
                if (value > cur.value) {
                    if (cur.right == null) {
                        cur.right = new Node(value, null, null);
                        break;
                    }
                    cur = cur.right;
                } else if (cur.value == value) {
                    break;
                } else {
                    if (cur.left == null) {
                        cur.left = new Node(value, null, null);
                        break;
                    }
                    cur = cur.left;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String str = sc.next();
            int n = sc.nextInt();
            switch (str) {
                case "insert":
                    insert(n);
                    break;
                case "delete":
                    root = delete(n, root);
                    break;
                case "exists":
                    System.out.println(exists(n, root));
                    break;
                case "next":
                    Node node = next(root, n);
                    if (node == null) {
                        System.out.println("none");
                    } else {
                        System.out.println(node.value);
                    }
                    break;
                case "prev":
                    Node nod = prev(root, n);
                    if (nod == null) {
                        System.out.println("none");
                    } else {
                        System.out.println(nod.value);
                    }
                    break;
            }
        }
    }

}

