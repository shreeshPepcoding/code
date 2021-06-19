import java.util.*;

public class btree {

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

    public static class Pair {
        Node node;
        int state;

        public Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static Node construct(Integer[] data) {
        Stack<Pair> st = new Stack<>();

        Node root = new Node(data[0]);
        int indx = 1;
        st.push(new Pair(root, -1));
        while (st.size() > 0) {
            Pair p = st.peek();

            if (p.state == -1) {
                // left child addition
                if (data[indx] != null) {
                    Node nn = new Node(data[indx]);
                    p.node.left = nn;
                    st.push(new Pair(nn, -1));
                }
                p.state++;
                indx++;
            } else if (p.state == 0) {
                // right child addition
                if (data[indx] != null) {
                    Node nn = new Node(data[indx]);
                    p.node.right = nn;
                    st.push(new Pair(nn, -1));
                }
                p.state++;
                indx++;
            } else {
                // wipe from out
                st.pop();
            }
        }
        return root;
    }

    public static void display(Node node) {
        if (node == null)
            return;

        // self printing
        String str = "";
        str += node.left == null ? "." : node.left.data;
        str += " <- [" + node.data + "] -> ";
        str += node.right == null ? "." : node.right.data;

        System.out.println(str);
        // left call
        display(node.left);
        // right call
        display(node.right);
    }

    public static int size(Node node) {
        if(node == null) return 0;

        int lsize = size(node.left);
        int rsize = size(node.right);

        return lsize + rsize + 1;
    }

    public static int sum(Node node) {
        if(node == null) return 0;
        return sum(node.left) + sum(node.right) + node.data;
    }

    public static int max(Node node) {
        if(node == null) return Integer.MIN_VALUE;

        int lmax = max(node.left);
        int rmax = max(node.right);

        return Math.max(node.data, Math.max(lmax, rmax));
    }

    public static int min(Node node) {
        if(node == null) return Integer.MAX_VALUE;

        int lmin = min(node.left);
        int rmin = min(node.right);

        return Math.min(node.data, Math.min(lmin, rmin));
    }

    public static int height(Node node) {
        if(node == null) return -1;

        int lh = height(node.left);
        int rh = height(node.right);

        return Math.max(lh, rh) + 1;
    }

    public static void preOrder(Node node) {
        if(node == null) return;

        System.out.print(node.data + " ");
        preOrder(node.left);
        preOrder(node.right);
    }

    public static void inOrder(Node node) {
        if(node == null) return;

        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    public static void postOrder(Node node) {
        if(node == null) return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data + " ");
    }

    public static void levelOrder(Node node) {
        Queue<Node> qu = new LinkedList<>();

        qu.add(node);

        while(qu.size() > 0) {
            int sz = qu.size();

            while(sz-- > 0) {
                // get + remove
                Node rem = qu.remove();
                // print
                System.out.print(rem.data + " ");
                // add left
                if(rem.left != null) qu.add(rem.left);
                // add right
                if(rem.right != null) qu.add(rem.right);
            }
            System.out.println();
        }
    }


    public static void fun() {
        Integer[] data = { 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null,
                null };
        Node root = construct(data);
        display(root);
        System.out.println("Max : " + max(root));
        System.out.println("Min : " + min(root));
        System.out.println("Height : " + height(root));
        System.out.println("Size : " + size(root));
        System.out.println("Sum : " + sum(root));

        System.out.print("Pre Order : ");
        preOrder(root);
        System.out.print("\nIn Order : ");
        inOrder(root);
        System.out.print("\nPost Order : ");
        postOrder(root);
        System.out.println("\nLevelOrder -> ");
        levelOrder(root);
    }

    public static void main(String[] args) {
        fun();
    }
}