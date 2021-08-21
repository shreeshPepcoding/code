import java.util.*;

public class AVL {
    
    public static class Node {
        int data;
        Node left;
        Node right;

        int balance;
        int height;

        public Node(int data) {
            this.data = data;
            this.left = this.right = null;
            this.balance = 0;
            this.height = 0;
        }
    }

    public static void updateHeightAndBalance(Node node) {
        int lh = node.left == null ? -1 : node.left.height;
        int rh = node.right == null ? -1 : node.right.height;

        node.balance = lh - rh;
        node.height = Math.max(lh, rh) + 1;
    }

    public static Node rightRotation(Node A) {
        Node B = A.left;
        A.left = B.right;
        B.right = A;
        updateHeightAndBalance(A);
        updateHeightAndBalance(B);
        return B;
    }

    public static Node leftRotation(Node A) {
        Node B = A.right;
        A.right = B.left;
        B.left = A;
        updateHeightAndBalance(A);
        updateHeightAndBalance(B);
        return B;
    }

    public static Node getRotation(Node node) {
        updateHeightAndBalance(node);
        if(node.balance == 2) {
            if(node.left.balance == 1 || node.left.balance == 0) {
                // LL Structure -> call for rightRotation
                return rightRotation(node);
            } else if(node.left.balance == -1) {
                // LR Struture
                // left rotation on B node
                node.left = leftRotation(node.left);
                // right rotation on A
                return rightRotation(node);
            }
        } else if(node.balance == -2) {
            if(node.right.balance == 1) {
                // RL Structure
                node.right = rightRotation(node.right);
                return leftRotation(node);
            } else if(node.right.balance == -1 || node.right.balance == 0) {
                // RR Struture
                return leftRotation(node);
            }
        }
        return node;
    }

    public static Node add(Node node, int data) {
        if(node == null) {
            return new Node(data);
        }
        if(data < node.data) {
            node.left = add(node.left, data);
        } else {
            node.right = add(node.right, data);
        }
        node = getRotation(node);
        return node;
    }

    public static int max(Node node) {
        if(node.right == null) return node.data;
        return max(node.right);
    }

    public static Node remove(Node node, int data) {
        if(node == null) return null;
        if(data < node.data) {
            node.left = remove(node.left, data);
        } else if(node.data < data) {
            node.right = remove(node.right, data);
        } else {
            // leaf child + have only left child + have only right child + both child
            if(node.left == null || node.right == null) {
                // leaf child + have only left child + have only right child
                return node.left == null ? node.right : node.left;
            }
            int lmax = max(node.left);
            node.data = lmax;
            node.left = remove(node.left, lmax);
        }
        node = getRotation(node);
        return node;
    }

    public static void display(Node node) {
        if(node == null) return;
        String str = "";
        str += node.left != null ? node.left.data + " <- ": ". <- ";
        str += node.data;
        str += node.right != null ? " -> " + node.right.data : " -> .";

        System.out.println(str);
        display(node.left);
        display(node.right);
    }

    public static void fun() {
        int[] arr = {10, 20, 30, 40, 50, 60, 70, 80, 90};

        Node node = null;
        for(int i = 0; i < arr.length; i++) {
            node = add(node, arr[i]);
            // display(node);
            // System.out.println("=====================================");
        }

        int[] arr2 = {50, 70};
        display(node);
        System.out.println("---------------------");
        for(int val : arr2) {
            node = remove(node, val);
            display(node);
            System.out.println("====================");
        }
    }

    public static void main(String[] args) {
        fun();    
    }
}