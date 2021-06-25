import java.util.*;

public class bst {
    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
            this.left = this.right = null;
        }

        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static Node construction(int[] arr, int lo, int hi) {
        if (lo > hi)
            return null;

        int mid = lo + (hi - lo) / 2;

        Node root = new Node(arr[mid]);

        root.left = construction(arr, lo, mid - 1);
        root.right = construction(arr, mid + 1, hi);

        return root;
    }

    public static void display(Node node) {
        if (node == null)
            return;

        String str = "";
        str += node.left == null ? "." : node.left.data;
        str += " <- [" + node.data + "] -> ";
        str += node.right == null ? "." : node.right.data;

        System.out.println(str);
        display(node.left);
        display(node.right);
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
        if(node.right == null) return node.data;
        return max(node.right);
    }

    public static int min(Node node) {
        if(node.left == null) return node.data;
        return min(node.left);
    }

    public static boolean find(Node node, int data) {
        if(node == null) {
            return false;
        }

        boolean res = false;
        if(node.data == data) {
            // data found
            res = true;
        } else if(node.data > data) {
            // left side
            res = find(node.left, data);
        } else {
            // right side
            res = find(node.right, data);
        }
        return res;
    }

    public static Node add(Node node, int data) {
        if(node == null) {
            return new Node(data, null, null);
        }

        if(node.data < data) {
            node.right = add(node.right, data);
        } else if(node.data > data){
            node.left = add(node.left, data);
        }
        
        return node;
    }
    

    public static void fun() {
        int[] data = { 12, 25, 30, 37, 50, 62, 70, 75, 87 };
        Node root = construction(data, 0, data.length - 1);
        display(root);
    }

    public static void main(String[] args) {
        fun();
    }
}