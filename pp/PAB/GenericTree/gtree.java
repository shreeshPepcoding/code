import java.util.*;

public class gtree {

    public static class Node {
        int data;
        ArrayList<Node> children;

        public Node(int data) {
            this.data = data;
            this.children = new ArrayList<>();
        }
    }

    public static Node construct(Integer[] data) {
        Stack<Node> st = new Stack<>();
        Node root = new Node(data[0]);
        st.push(root);
        for(int i = 1; i < data.length; i++) {
            if(data[i] != null) {
                // 1. create a new node
                Node nn = new Node(data[i]);
                // 2. add yourself in children of top of stack
                st.peek().children.add(nn);
                // 3. add yourself in stack
                st.push(nn);
            } else {
                st.pop();
            }
        }

        return root;
    }

    public static void display(Node root) {
        if(root == null) return;
        // our work
        System.out.print("[" + root.data + "] -> ");
        for(int i = 0; i < root.children.size(); i++) {
            Node child = root.children.get(i);
            System.out.print(child.data + ", ");
        }
        System.out.println(".");
        
        // faith
        // for(int i = 0; i < root.children.size(); i++) {
        //     Node child = root.children.get(i);
        //     display(child);
        // }

        // using for-each loop
        for(Node child : root.children) {
            display(child);
        }
    }

    public static int size(Node node) {
        int sz = 0;

        for(Node child : node.children) {
            sz += size(child);
        }

        return sz + 1;
    }

    public static int max(Node node) {
        int mx = Integer.MIN_VALUE;
        
        for(Node child : node.children) {
            int rmax = max(child); // recursive max
            mx = Math.max(mx, rmax);
        }

        return Math.max(mx, node.data);
    }

    public static int min(Node node) {
        int mn = Integer.MAX_VALUE;
        
        for(Node child : node.children) {
            int rmin = max(child); // recursive min
            mn = Math.min(mn, rmin);
        }

        return Math.min(mn, node.data);
    }

    public static int height(Node node) {
        int ht = -1;

        for(Node child : node.children) {
            int cht = height(child); // child height

            ht = Math.max(ht, cht);
        }
        return ht + 1;
    }

    public static void traversals(Node node) {
        System.out.println("Node pre " + node.data);
        for(Node child : node.children) {
            System.out.println("Edge pre " + node.data + "--" + child.data);
            traversals(child);
            System.out.println("Edge post " + node.data + "--" + child.data);
        }
        System.out.println("Node post " + node.data);
    }

    public static void fun() {
        Integer[] data = {10, 20, 50, null, 60, null, null, 30, 70, null, 80, 110, null,
             120, null, null, 90, null, null, 40, 100, null, null, null};
        Node root = construct(data);
        // display(root);

        // System.out.println("Size is : " + size(root));
        // System.out.println("Min : " + min(root));
        // System.out.println("Max : " + max(root));
        // System.out.println("Height : " + height(root));
        
        traversals(root);
    }


    public static void main(String[] args) {
        fun();
    }
}