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
        Node root = new Node(arr[0]);
        Stack<Node> st = new Stack<>();
        st.push(root);

        for(int i = 1; i < arr.length; i++) {
            if(arr[i] == null) {
                st.pop(); // wipe out from recursive stack
            } else {
                // 1. Make a new Node
                Node nn = new Node(arr[i]);
                // 2. make connection to its parent
                st.peek().children.add(nn);
                // 3. push it in the stack
                st.push(nn);
            }
        }
        return root;
    }

    public static void display(Node node) {
        // self work
        System.out.print(node.data + " -> ");
        for(Node child : node.children) {
            System.out.print(child.data + " ");   
        }
        System.out.println(".");

        // faith work
        for(Node child : node.children) {
            display(child);
        }
    }


    public static void fun() {
        Integer[] data = {10, 20, 50, null, 60, null, null, 30, 70, null, 80, 110, null,
             120, null, null, 90, null, null, 40, 100, null, null, null};

        Node root = construct(data);
        display(root);
    }

    public static void main(String[] args) {
        fun();
    }
}