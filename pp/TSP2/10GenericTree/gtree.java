import java.util.*;

public class gtree {

    public static class Node {
        int data;
        ArrayList<Node> children;

        public Node() {
            this.data = 0;
            this.children = new ArrayList<>();
        }

        public Node(int data) {
            this.data = data;
            this.children = new ArrayList<>();
        }
    }

    public static Node construct(Integer[] arr) {
        Node root = null;
        Stack<Node> st = new Stack<>();

        for(int i = 0; i < arr.length; i++) {
            Integer data = arr[i];
            if(data != null) {
                Node nn = new Node(data);
                if(st.size() == 0) {
                    root = nn;
                    st.push(nn);
                } else {
                    st.peek().children.add(nn);
                    st.push(nn);
                }
            } else {
                st.pop();
            }
        }

        return root;
    }

    public static void display(Node root) {
        
        String str = "[" + root.data + "] -> ";
        for(Node child : root.children) {
            str += child.data + ", ";
        }
        System.out.println(str + " .");

        for(int i = 0; i < root.children.size(); i++) {
            Node child = root.children.get(i);
            display(child);
        }
    }

    public static int size(Node root) {
        int sz = 0;
        for(Node child : root.children) {
            sz += size(child);
        }
        return sz + 1;
    }

    public static int max(Node node) {
        int mx = Integer.MIN_VALUE;
        for(Node child : node.children) {
            mx = Math.max(mx, max(child));
        }
        return Math.max(mx, node.data);
    }

    public static int min(Node node) {
        int mn = Integer.MAX_VALUE;
        for(Node child : node.children) {
            mn = Math.min(mn, min(child));
        }
        return Math.min(mn, node.data);
    }

    public static int height(Node root) {
        int ht = -1; // on the basis of edge

        for(Node child : root.children) {
            ht = Math.max(ht, height(child));
        }

        return ht + 1;
    }

    public static void fun() {
        Integer[] data = {10, 20, 50, null, 60, null, null, 30, 70, null, 80, 110, null, 120, null,
                                 null, 90, null, null, 40, 100, null, null, null};
        
        Node root = construct(data);
        display(root);
        System.out.println("Size : " + size(root));
        System.out.println("Max : " + max(root));
        System.out.println("Min : " + min(root));

    }

    public static void main(String[] args) {
        fun();
    }
}