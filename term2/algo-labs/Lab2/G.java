import java.util.Scanner;

public class G {
    static class Node {
        int value;
        int height;
        int weight;
        Node left;
        Node right;

        public Node(int value, int height, int weight, Node left, Node right) {
            this.value = value;
            this.height = height;
            this.left = left;
            this.right = right;
            this.weight = weight;
        }
    }


    public static Node root = null;

    static int update_weight(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        } else if (node.left == null) {
            return node.right.weight + 1;
        } else if (node.right == null) {
            return node.left.weight + 1;
        } else {
            return node.left.weight + node.right.weight + 1;
        }
    }

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
        right.left.weight = update_weight(right.left);
        right.weight = update_weight(right);
        return right;
    }

    static Node rotateRight(Node node) {
        Node left = node.left;
        node.left = left.right;
        left.right = node;
        left.right.height = updateHeight(left.right);
        left.height = updateHeight(left);
        left.right.weight = update_weight(left.right);
        left.weight = update_weight(left);
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
        node.weight = update_weight(node);
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
        node.weight = update_weight(node);
        int balance = getBalance(node);
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
            node = new Node(value, 0, 0, null, null);
        } else if (value > node.value) {
            node.right = insert(node.right, value);
        } else if (value < node.value) {
            node.left = insert(node.left, value);
        } else {
            return node;
        }
        return checkRotateInsert(node, value);
    }


    static void find_K_max(Node node, int k) {
        while (node != null) {
            if (node.right == null) {
                if (node.left == null) {
                    System.out.println(node.value);
                    break;
                }
                if (k == 0) {
                    System.out.println(node.value);
                    break;
                }
                node = node.left;
                k--;
            } else {
                if (k == node.right.weight) {
                    System.out.println(node.value);
                    break;
                } else if (k > node.right.weight) {
                    k -= node.right.weight + 1;
                    if (node.left == null) {
                        System.out.println(node.weight);
                        break;
                    }
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int k = sc.nextInt();
            switch (k) {
                case 1:
                    root = insert(root, sc.nextInt());
                    break;
                case -1:
                    root = delete(sc.nextInt(), root);
                    break;
                case 0:
                    find_K_max(root, sc.nextInt() - 1);
            }
        }
    }

}

