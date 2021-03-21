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
        Node root = new Node(data[0]);
        // code here
        int indx = 1;
        Stack<Pair> st = new Stack<>();
        st.push(new Pair(root, 1));

        while(st.size() > 0) {
            Pair top = st.peek();
            if(top.state == 1) {
                if(data[indx] != null) {
                    Node nn = new Node(data[indx]);
                    top.node.left = nn;
                    st.push(new Pair(nn, 1));
                } else {
                    top.node.left = null;
                }
                top.state++;
                indx++;
            } else if(top.state == 2) {
                if(data[indx] != null) {
                    Node nn = new Node(data[indx]);
                    top.node.right = nn;
                    st.push(new Pair(nn, 1));
                } else {
                    top.node.right = null;
                }
                top.state++;
                indx++;
            } else {
                st.pop();
            }
        }
        return root;
    }

    public static void display(Node root) {
        if(root == null) return;

        String str = "";
        str += root.left == null ? " ." : root.left.data;
        str += " <- " + root.data + " -> ";
        str += root.right == null ? "." : root.right.data;
        System.out.println(str);

        display(root.left);
        display(root.right);
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



    public static void main(String[] args) {
        Integer[] data = {50, 25, 20, null, null, 35, 30, null, null, null, 75, 65, null, 60, null, null, 85, null, null};
        Node root = construct(data);
        display(root);
        System.out.println("\n==================");
        preOrder(root);
        System.out.println("\n==================");
        postOrder(root);
        System.out.println("\n==================");
        inOrder(root);

    }
}