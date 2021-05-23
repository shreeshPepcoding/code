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

    public static void traversals(Node node){
        // node pre
        System.out.println("Node Pre " + node.data);
        for(Node child : node.children) {
            // before call -> edge pre
            System.out.println("Edge Pre " + node.data + "--" + child.data);
            // call
            traversals(child);
            // after call -> edge post
            System.out.println("Edge Post " + node.data + "--" + child.data);
        }
        // node post
        System.out.println("Node Post " + node.data);
    }

    public static void levelOrder(Node node){
        Queue<Node> qu = new ArrayDeque<>();

        qu.add(node);

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

    // level order using 2 queues
    public static void levelOrderLinewise1(Node node){
        Queue<Node> mainQ = new ArrayDeque<>();
        Queue<Node> childQ = new ArrayDeque<>();
        
        mainQ.add(node);
        int level = 1;
        System.out.print("level - " + level + " : ");
        while(mainQ.size() > 0) {
            Node rem = mainQ.remove();
            System.out.print(rem.data + " ");

            for(Node child : rem.children) {
                childQ.add(child);
            }

            if(mainQ.size() == 0) {
                // hit enter
                System.out.println();
                level++;
                // swap mainQ and childQ
                Queue<Node> temp = mainQ;
                mainQ = childQ;
                childQ = temp;
                if(mainQ.size() != 0)
                    System.out.print("level - " + level + " : ");
            }
        }
    }

    // level order using delimiter
    public static void levelOrderLinewise2(Node node){
        // use linkedlist as a queue, because arrayDeque is not allow us to add null
        Queue<Node> qu = new LinkedList<>();

        qu.add(node);
        qu.add(null);

        while(qu.size() > 0) {
            // 1. get + remove
            Node rem = qu.remove();

            if(rem == null) {
                // 2. hit enter
                System.out.println();
                // 3. if size is more than 0 then again add null
                if(qu.size() > 0)
                    qu.add(null);

            } else {
                // 2. print
                System.out.print(rem.data + " ");
                // 3. add children
                for(Node child : rem.children) {
                    qu.add(child);
                }
            }
        }
    }

    // level order line wise, using single que, count approach
    public static void levelOrderLinewise3(Node node){
        // use linkedlist as a queue, because arrayDeque is not allow us to add null
        Queue<Node> qu = new LinkedList<>();
        qu.add(node);

        while(qu.size() > 0) {
            int sz = qu.size();
            while(sz-- > 0) {
                // 1. get + remove
                Node rem = qu.remove();
                // 2. print
                System.out.print(rem.data + " ");
                // 3. add children
                for(Node child : rem.children) {
                    qu.add(child);
                }
            }
            System.out.println();
        }
    }

    public static void fun() {
        Integer[] data = {10, 20, 50, null, 60, null, null, 30, 70, null, 80, 110, null, 120, null,
                                 null, 90, null, null, 40, 100, null, null, null};
        
        Node root = construct(data);
        display(root);
        levelOrderLinewise3(root);
        // levelOrderLinewise2(root);
        // levelOrderLinewise1(root);
        // levelOrder(root);
        // System.out.println("Size : " + size(root));
        // System.out.println("Max : " + max(root));
        // System.out.println("Min : " + min(root));
    }

    public static void main(String[] args) {
        fun();
    }
}