import java.util.*;

public class bst {

    public static class Node {
        int data;
        Node left;
        Node right;

        Node() {
            // default constructor
        }

        Node(int data) {
            this.data = data;
            this.left = this.right = null;
        }

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static void display(Node root) {
        if(root == null) return;
        // self print
        String str = root.left != null ? "" + root.left.data : ".";
        str += " <- " + root.data + " -> ";
        str += root.right != null ? "" + root.right.data : ".";
        System.out.println(str);
        // left print
        display(root.left);
        // right print
        display(root.right);
    }

    public static Node construct(int[] data, int lo, int hi) {
        if(lo > hi) {
            return null;
        }
        int mid = (lo + hi) / 2;
        Node midNode = new Node(data[mid]);
        midNode.left = construct(data, lo, mid - 1);
        midNode.right = construct(data, mid + 1, hi);
        return midNode;
    }

    public static int size(Node node) {
        if(node == null) return 0;
        return size(node.left) + size(node.right) + 1;
    }

    public static int sum(Node node) {
        if(node == null) return 0;
        return sum(node.left) + sum(node.right) + node.data;
    }

    public static int max(Node node) {
        // move toward right for max in bst
        if(node == null) return Integer.MIN_VALUE;
        if(node.right == null) return node.data;
        return max(node.right);
    }

    public static int min(Node node) {
        if(node == null) return Integer.MAX_VALUE;
        if(node.left == null) return node.data;

        return min(node.left);
    }

    public static boolean find(Node node, int data) {
        if(node == null) return false;
        if(node.data == data) {
            return true;
        } else if(node.data < data) {
            return find(node.right, data);
        } else {
            return find(node.left, data);
        }
    }

    public static Node add(Node node, int data) {
        if(node == null) {
            return new Node(data);
        } else if(node.data < data) {
            node.right = add(node.right, data);
        } else {
            node.left = add(node.left, data);
        }
        return node;
    }

    public static Node remove(Node node, int data) {
        if(node == null) return null;
        if(node.data == data) {
            if(node.left != null && node.right != null) {
                // have both child
                int lmax = max(node.left);
                node.data = lmax;
                node.left = remove(node.left, lmax);
                return node;
            } else if(node.left != null) {
                // have only left child
                return node.left;
            } else if(node.right != null) {
                // have only right child
                return node.right;
            } else {
                // leaf node
                return null;
            }
        } else if(node.data > data) {
            node.left = remove(node.left, data);
        } else {
            node.right = remove(node.right, data);
        }
        return node;
    }

    static int sum = 0;
    public static void rwsol(Node node){
        if(node == null) return;

        rwsol(node.right);
        // reverse inorder area
        int data = node.data;
        node.data = sum; // set 
        sum += data; // add in sum
        rwsol(node.left);
    }

    public static int lca(Node node, int d1, int d2) {
        if(node == null) return -1;

        if(d1 < node.data && d2 < node.data) {
            // lca exist in left
            return lca(node.left, d1, d2);
        } else if(d1 > node.data && d2 > node.data) {
            // lca exist in right
            return lca(node.right, d1, d2);
        } else {
            // node is LCA
            return node.data;
        }
    }

    public static void pir(Node node, int d1, int d2) {
        if(node == null) return;

        if(d1 < node.data && d2 < node.data) {
            // print segment is appears in left
            pir(node.left, d1, d2);
        } else if(d1 > node.data && d2 > node.data) {
            // print segment is appears in right side
            pir(node.right, d1, d2);
        } else {
            // move toward left, print yourself then move toward right
            pir(node.left, d1, d2);
            System.out.println(node.data);
            pir(node.right, d1, d2);
        }
    }

    public static void targetSumPairUsingFind(Node node, Node root, int target) {
        if(node == null) return;

        targetSumPairUsingFind(node.left, root, target);
        int val1 = node.data;
        int val2 = target - node.data;
        if(val2 > val1) {
            if(find(root, val2)) {
                System.out.println(val1 + " " + val2);
            }
        }
        targetSumPairUsingFind(node.right, root, target);
    }
    

    public static void fun() {
        int[] data = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        Node root = construct(data, 0, data.length - 1);
        display(root);
    }

    public static void main(String[] args) {
        fun();
    }
}