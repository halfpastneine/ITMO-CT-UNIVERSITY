import java.util.Scanner;

public class D {
    static class Node {
        int value;
        int height;
        Node left;
        Node right;

        public Node(int value, int height, Node left, Node right) {
            this.value = value;
            this.height = height;
            this.left = left;
            this.right = right;
        }
    }


    public static Node root = null;

    static int updateHeight(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 0;
        } else if (node.left == null) {
            return 1 + node.right.height;
        } else if (node.right == null) {
            return 1 + node.left.height;
        } else {
            return 1 + Math.max(node.left.height, node.right.height);
        }
    }

    static Node rotateLeft(Node node) {
        Node right = node.right;
        node.right = right.left;
        right.left = node;
        right.left.height = updateHeight(right.left);
        right.height = updateHeight(right);
        return right;
    }

    static Node rotateRight(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        left.right.height = updateHeight(left.right);
        left.height = updateHeight(left);
        return left;
    }

    static Node bigRotateLeft(Node node) {
        node.right = rotateRight(node.right);
        return rotateLeft(node);
    }

    static Node bigRotateRight(Node node) {
        node.left = rotateLeft(node.left);
        return rotateRight(node);
    }

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
            if (root != null) {
                root = checkRotateDelete(root);
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

    static int getBalance(Node node) {
        if (node.left == null && node.right == null) {
            return 0;
        } else if (node.left == null) {
            return -node.right.height;
        } else if (node.right == null) {
            return node.left.height;
        } else {
            return node.left.height - node.right.height;
        }
    }

    static Node checkRotateDelete(Node node) {
        node.height = updateHeight(node);
        int balance = getBalance(node);
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rotateRight(node);
        } else if (balance > 1 && getBalance(node.left) < 0) {
            return bigRotateRight(node);
        } else if (balance < -1 && getBalance(node.right) > 0) {
            return bigRotateLeft(node);
        } else if (balance < -1 && getBalance(node.right) <= 0) {
            return rotateLeft(node);
        }
        return node;
    }

    static Node checkRotateInsert(Node node, int value) {
        node.height = updateHeight(node);
        int balance = getBalance(node);
        System.out.println(balance);
        if (balance < -1 && value > node.right.value) {
            return rotateLeft(node);
        } else if (balance > 1 && value < node.left.value) {
            return rotateRight(node);
        } else if (balance > 1 && value > node.left.value) {
            return bigRotateRight(node);
        } else if (balance < -1 && value < node.right.value) {
            return bigRotateLeft(node);
        }
        return node;
    }

    static Node insert(Node node, int value) {
        if (node == null) {
            node = new Node(value, 0, null, null);
        } else if (value > node.value) {
            node.right = insert(node.right, value);
        } else if (value < node.value) {
            node.left = insert(node.left, value);
        } else {
            return node;
        }
        return checkRotateInsert(node, value);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        root = insert(root, 1);
        root = insert(root, 2);
        root = insert(root,3);
        while (sc.hasNext()) {
            String str = sc.next();
            int n = sc.nextInt();
            switch (str) {
                case "insert":
                    root = insert(root, n);
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

