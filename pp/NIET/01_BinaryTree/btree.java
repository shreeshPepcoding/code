import java.util.*;

public class btree {

    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = this.right = null;
        }  

        Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    public static class Pair {
        Node node;
        int state;

        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static Node construct(Integer[] data) {
        Stack<Pair> st = new Stack<>();
        Node root = new Node(data[0]);
        st.push(new Pair(root, 1));
        // state = 1, set left child
        // state = 2, set right child
        // state = 3, pop from stack

        int indx = 0;
        while(st.size() > 0) {
            Pair top = st.peek();
            if(top.state == 1) {
                indx++;
                if(data[indx] != null) {
                    Node nn = new Node(data[indx]);
                    top.node.left = nn;
                    st.push(new Pair(nn, 1));
                }
                top.state++;
            } else if(top.state == 2) {
                indx++;
                if(data[indx] != null) {
                    Node nn = new Node(data[indx]);
                    top.node.right = nn;
                    st.push(new Pair(nn, 1));
                }
                top.state++;
            } else {
                st.pop();
            }
        }
        return root;
    }

    public static void display(Node root) {
        if(root == null) return;
        // self print
        String str = root.left != null ? "" + root.left.data : ".";
        str += " <- " + root.data + " -> ";
        str += root.right != null ? "" + root.right.data : ".";
        System.out.println(str);
        // left print
        display(root.left);
        // right print
        display(root.right);
    }

    public static int size(Node node) {
        if(node == null) return 0;
        int lsize = size(node.left);
        int rsize = size(node.right);
        int msize = lsize + rsize + 1;
        return msize;
    }

    public static int sum(Node node) {
        if(node == null) return 0;
        // return sum(node.left) + sum(node.right) + node.data;
        int lsum = sum(node.left);
        int rsum = sum(node.right);
        int msum = lsum + rsum + node.data;
        return msum;
    }   

    public static int max(Node node) {
        if(node == null) return Integer.MIN_VALUE;

        int lmax = max(node.left);
        int rmax = max(node.right);
        int mmax = Math.max(node.data, Math.max(lmax, rmax));
        return mmax;
    }

    public static int height(Node node) {
        if(node == null) return -1; // on the basis of edge
        int lh = height(node.left);
        int rh = height(node.right);
        int mh = Math.max(lh, rh) + 1;
        return  mh;
    }


    private static void fun() {
        Integer[] data = {50, 25, 12, null, null, 37, 30, null, null, 
                null, 75, 62, null, 70, null, null, 87, null, null};
        
        Node root = construct(data);
        display(root);
    }

    public static void levelOrder(Node node) {
        
        LinkedList<Node> qu = new LinkedList<>();
        // linkedlist as a queue -> addLast, removeFirst
        qu.addLast(node);
        while(qu.size() > 0) {
            int sz = qu.size();
            while(sz-- > 0) {
                // get + remove
                Node rem = qu.removeFirst();
                // work -> print data
                System.out.print(rem.data + " ");
                // add children if valid
                if(rem.left != null) qu.addLast(rem.left);
                if(rem.right != null) qu.addLast(rem.right);
            }
            // hit enter
            System.out.println();
        }
    }

    // leetcode 102, https://leetcode.com/problems/binary-tree-level-order-traversal/
    public List<List<Integer>> levelOrder(TreeNode node) {
        List<List<Integer>> res = new ArrayList<>();
        if(node == null) return res;

        LinkedList<TreeNode> qu = new LinkedList<>();
        // linkedlist as a queue -> addLast, removeFirst
        qu.addLast(node);
        while(qu.size() > 0) {
            int sz = qu.size();
            List<Integer> list = new ArrayList<>();
            while(sz-- > 0) {
                // get + remove
                TreeNode rem = qu.removeFirst();
                // work -> print data
                list.add(rem.val);
                // add children if valid
                if(rem.left != null) qu.addLast(rem.left);
                if(rem.right != null) qu.addLast(rem.right);
            }
            res.add(list);
        }
        return res;
    }

    public static void iterativePrePostInTraversal(Node node) {
        ArrayList<Integer> pre = new ArrayList<>();
        ArrayList<Integer> in = new ArrayList<>();
        ArrayList<Integer> post = new ArrayList<>();

        Stack<Pair> st = new Stack<>();
        st.push(new Pair(node, 1));
        while(st.size() > 0) {
            Pair top = st.peek();
            if(top.state == 1) {
                // pre Area

                pre.add(top.node.data);
                if(top.node.left != null) st.add(new Pair(top.node.left, 1));
                top.state++;
            } else if(top.state == 2) {
                // in area

                in.add(top.node.data);
                if(top.node.right != null) st.add(new Pair(top.node.right, 1));
                top.state++;
            } else {
                // post area

                post.add(top.node.data);
                st.pop();
            }
        }
        // print
        for(int val : pre) {
            System.out.print(val + " ");
        }
        System.out.println();
        for(int val : in) {
            System.out.print(val + " ");
        }
        System.out.println();
        for(int val : post) {
            System.out.print(val + " ");
        }
    }
    
    public static void main(String[] args) {
        fun();
    }

}