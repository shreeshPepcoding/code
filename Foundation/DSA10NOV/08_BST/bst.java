import java.lang.reflect.Constructor;
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
    }

    public static Node construct(int[] data, int lo, int hi) {
        // write your code
        if(lo > hi) return null;

        int mid = (lo + hi) / 2;
        // int mid = lo + (hi - lo) / 2;

        Node node = new Node(data[mid]);
        node.left = construct(data, lo, mid - 1);
        node.right = construct(data, mid + 1, hi);
        
        return node;
    }

    public static void display(Node root) {
        if(root == null) return;
        // write your code
        String str = "";
        str += root.left != null ? root.left.data : ".";
        str += " <- " + root.data + " -> ";
        str += root.right != null ? root.right.data : ".";

        System.out.println(str);

        display(root.left);
        display(root.right);
    }

    public static void main(String[] args) {
        int[] data = {12, 25, 30, 37, 40, 50, 60, 62, 70, 75, 87};
        Node root = construct(data, 0, data.length - 1);

        display(root);
    }
}