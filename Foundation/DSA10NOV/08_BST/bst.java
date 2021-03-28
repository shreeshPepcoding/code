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

    public static class Pair {
        Node node;
        int state;

        public Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static Node getInFromIterative(Stack<Pair> st) {
        // noarmal iterative
        while(st.size() > 0) {
            Pair top = st.peek();
            if(top.state == 0) {
                if(top.node.left != null) {
                    st.push(new Pair(top.node.left, 0));
                }
                top.state++;
            } else if(top.state == 1) {
                if(top.node.right != null) {
                    st.push(new Pair(top.node.right, 0));
                }
                top.state++;
                return top.node;
            } else {
                st.pop();
            }
        }
        return null;
    }

    public static Node getInFromRevIterative(Stack<Pair> st) {
        // reverse iterative
        while(st.size() > 0) {
            Pair top = st.peek();
            if(top.state == 0) {
                if(top.node.right != null) {
                    st.push(new Pair(top.node.right, 0));
                }
                top.state++;
            } else if(top.state == 1) {
                if(top.node.left != null) {
                    st.push(new Pair(top.node.left, 0));
                }
                top.state++;
                return top.node;
            } else {
                st.pop();
            }
        }
        return null;
    }

    public static void targetSumPair(Node node, int target) {
        Stack<Pair> iSt = new Stack<>();
        Stack<Pair> riSt = new Stack<>();

        iSt.push(new Pair(node, 0));
        riSt.push(new Pair(node, 0));

        Node lo = getInFromIterative(iSt);
        Node hi = getInFromRevIterative(riSt);

        while(lo.data < hi.data) {
            if(lo.data + hi.data == target) {
                System.out.println(lo.data + " " + hi.data);
                lo = getInFromIterative(iSt);
                hi = getInFromRevIterative(riSt);
            } else if(lo.data + hi.data > target) {
                hi = getInFromRevIterative(riSt);
            } else {
                lo = getInFromIterative(iSt);
            }
        }
    }

    public static void main(String[] args) {
        int[] data = {10, 12, 20, 25, 30, 37, 40, 50, 60, 62, 70, 75, 80, 87, 90};
        Node root = construct(data, 0, data.length - 1);

        // display(root);
        int target = 100;
        targetSumPair(root, target);
    }
}