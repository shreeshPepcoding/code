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

    public static Node construct(Integer[] arr) {
        Node root = new Node(arr[0]);
        Stack<Pair> st = new Stack<>();
        st.push(new Pair(root, 0));

        int indx = 0;
        while (st.size() > 0) {
            Pair p = st.peek();
            if (p.state == 0) {
                // left child processing
                indx++;
                if (arr[indx] != null) {
                    Node nn = new Node(arr[indx]);
                    p.node.left = nn;
                    st.push(new Pair(nn, 0));
                }
                p.state++;
            } else if (p.state == 1) {
                // right child processing
                indx++;
                if (arr[indx] != null) {
                    Node nn = new Node(arr[indx]);
                    p.node.right = nn;
                    st.push(new Pair(nn, 0));
                }
                p.state++;
            } else {
                // pop out node-pair from stack
                st.pop();
            }
        }
        return root;
    }

    public static void display(Node root) {
        if (root == null)
            return;

        String str = root.left == null ? " ." : "" + root.left.data;
        str += " <- [" + root.data + "] -> ";
        str += root.right == null ? ". " : root.right.data;
        System.out.println(str);

        display(root.left);
        display(root.right);
    }

    public static int size(Node node) {
        if(node == null) return 0;

        int ls = size(node.left);
        int rs = size(node.right);

        return ls + rs + 1;
    }

    public static int sum(Node node) {
        if(node == null) return 0;

        int lsum = sum(node.left);
        int rsum = sum(node.right);

        return lsum + rsum + node.data;
    }

    public static int sum1(Node node) {
        // if(node == null) return 0; // root == null

        if(node.left != null && node.right != null) {
            int lsum = sum1(node.left);
            int rsum = sum1(node.right);
            return lsum + rsum + node.data;
        } else if(node.left != null) {
            int lsum = sum1(node.left);
            return lsum + node.data;
        } else if(node.right != null) {
            int rsum = sum1(node.right);
            return rsum + node.data;
        } else {
            return node.data;
        }
    }

    public static int sum2(Node node) {

        int sum = 0;
        if(node.left != null) {
            sum += sum2(node.left);
        }

        if(node.right != null) {
            sum += sum2(node.right);
        }
        return sum + node.data;
    }

    public static int max(Node node) {
        if(node == null) return Integer.MIN_VALUE;

        int lmax = max(node.left);
        int rmax = max(node.right);

        return Math.max(node.data, Math.max(lmax, rmax));
    }

    public static int height(Node node) {
        if(node == null) return -1;
        
        int lh = height(node.left);
        int rh = height(node.right);

        return Math.max(lh, rh) + 1;
    }

    public static void levelOrder(Node node) {
        Queue<Node> que = new ArrayDeque<>();

        que.add(node);

        while(que.size() > 0) {
            int sz = que.size();

            while(sz-- > 0) {
                // 1. get + remove
                Node rem = que.remove();
                // 2. print
                System.out.print(rem.data + " ");
                // 3. add children
                if(rem.left != null)
                    que.add(rem.left);

                if(rem.right != null) 
                    que.add(rem.right);
            }
            System.out.println();
        }
    }

    // preAre -> area before all calls
    public static void preOrder(Node root) {
        if(root == null) return;
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    // inAre -> area between the calls i.e. left and right calls
    public static void inOrder(Node root) {
        if(root == null) return;
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }

    // postArea -> area after all calls
    public static void postOrder(Node root) {
        if(root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + " ");
    }

    static ArrayList<Integer> pre;
    static ArrayList<Integer> in;
    static ArrayList<Integer> post;
    public static void traversal(Node root) {
        if(root == null) return;
        pre.add(root.data);
        traversal(root.left);
        in.add(root.data);
        traversal(root.right);
        post.add(root.data);
    }

    public static void fun() {
        Integer[] arr = { 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null,
                null };
        Node root = construct(arr);

        display(root);

        // levelOrder(root);
        System.out.print("Pre Order : ");
        preOrder(root);
        System.out.print("\nIn Order : ");
        inOrder(root);
        System.out.print("\nPost Order : ");
        postOrder(root);

        pre = new ArrayList<>();
        in = new ArrayList<>();
        post = new ArrayList<>();
        traversal(root);
    
        System.out.print("\nPre Order : ");
        for(int val : pre)
            System.out.print(val + " ");
        
        System.out.print("\nIn Order : ");
        for(int val : in)
            System.out.print(val + " ");
        
        System.out.print("\nPost Order : ");
        for(int val : post)
            System.out.print(val + " ");

        // System.out.println("Size : " + size(root));
        // System.out.println("Sum : " + sum(root));
        // System.out.println("Max : " + max(root));
        // System.out.println("Height : " + height(root));
        // System.out.println("Sum 1 : " + sum1(root));
        // System.out.println("Sum 2 : " + sum2(root));


    }

    public static void main(String[] args) {
        fun();
    }
}