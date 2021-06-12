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
        if(node == null) return;
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

    public static int size(Node node){
        if(node == null) return 0;
        int sz = 0;
        for(int i = 0; i < node.children.size(); i++) {
            Node child = node.children.get(i);
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

    public static int height(Node node) {
        int ht = -1; // for edge basis, ht = 0, it is for node basis

        for(Node child : node.children) {
            ht = Math.max(ht, height(child));
        }
        return ht + 1;
    }

    public static void traversals(Node node){
        // pre
        System.out.println("Node Pre " + node.data);
        for(Node child : node.children) {
            // edge pre
            System.out.println("Edge Pre " + node.data + "--" + child.data);
            // call
            traversals(child);
            // edge post
            System.out.println("Edge Post " + node.data + "--" + child.data);
        }
 
        // post
        System.out.println("Node Post " + node.data);
    }


    public static void levelOrder(Node root){
        Queue<Node> qu = new ArrayDeque<>();
        qu.add(root);

        while(qu.size() > 0) {
            // 1. get + remove
            Node rem = qu.remove();
            // 2. print
            System.out.print(rem.data + " ");
            // 3. add children
            for(Node child : rem.children) {
                qu.add(child);
            }
        }
        System.out.println(".");
    }

    public static void levelOrderLineWise1(Node root) {
        Queue<Node> qu = new LinkedList<>();
        qu.add(root);
        qu.add(null);

        while(qu.size() > 0) {
            // 1. get + remove
            Node rem = qu.remove();

            if(rem == null) {
                System.out.println();
                if(qu.size() > 0) 
                    qu.add(null);
                
                continue;
            }
            // 2. print 
            System.out.print(rem.data + " ");

            // 3. add children
            for(Node child : rem.children) {
                qu.add(child);
            }
        }



    }



    public static void fun() {
        Integer[] data = {10, 20, 50, null, 60, null, null, 30, 70, null, 80, 110, null,
             120, null, null, 90, null, null, 40, 100, null, null, null};

        Node root = construct(data);
        levelOrderLineWise1(root);
        // levelOrder(root);

        // traversals(root);

        // display(root);
        // System.out.println("Min : " + min(root));
    }

    public static void main(String[] args) {
        fun();
    }
}