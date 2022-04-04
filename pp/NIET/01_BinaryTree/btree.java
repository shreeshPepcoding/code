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

    public static int min(Node node) {
        if(node == null) return Integer.MAX_VALUE;

        int lmin = min(node.left);
        int rmin = min(node.right);
        int mmin = Math.min(node.data, Math.min(lmin, rmin));
        return mmin;
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
    
    public static boolean find(Node node, int data){
        if(node == null) return false;

        if(node.data == data) return true;

        boolean lres = find(node.left, data);
        if(lres == true) {
            return true;
        }
        boolean rres = find(node.right, data);
        return rres;

        // return node.data == data || find(node.left, data) || find(node.right, data);

    }

    public static ArrayList<Integer> nodeToRootPath(Node node, int data){
        if(node == null) return new ArrayList<>();

        if(node.data == data) {
            ArrayList<Integer> mres = new ArrayList<>();
            mres.add(node.data);
            return mres;
        }

        ArrayList<Integer> lres = nodeToRootPath(node.left, data);
        if(lres.size() > 0) {
            lres.add(node.data);
            return lres;
        }
        ArrayList<Integer> rres = nodeToRootPath(node.right, data);
        if(rres.size() > 0) {
            rres.add(node.data);
            return rres;
        }
        return new ArrayList<>();
    }

    public static void printKLevelsDown(Node node, int k){
        if(node == null) return;

        if(k == 0) {
            System.out.println(node.data);
            return;
        }

        printKLevelsDown(node.left, k - 1);
        printKLevelsDown(node.right, k - 1);
    }

    public static ArrayList<Node> nodeToRootPath2(Node node, int data){
        if(node == null) return new ArrayList<>();

        if(node.data == data) {
            ArrayList<Node> mres = new ArrayList<>();
            mres.add(node);
            return mres;
        }

        ArrayList<Node> lres = nodeToRootPath2(node.left, data);
        if(lres.size() > 0) {
            lres.add(node);
            return lres;
        }
        ArrayList<Node> rres = nodeToRootPath2(node.right, data);
        if(rres.size() > 0) {
            rres.add(node);
            return rres;
        }
        return new ArrayList<>();
    }

    public static void printKLevelsDown2(Node node, int k, Node blocker){
        if(node == null) return;

        if(node == blocker) return;

        if(k == 0) {
            System.out.println(node.data);
            return;
        }

        printKLevelsDown2(node.left, k - 1, blocker);
        printKLevelsDown2(node.right, k - 1, blocker);
    }

    public static void printKNodesFar(Node node, int data, int k) {
        ArrayList<Node> n2rp = nodeToRootPath2(node, data);
        Node blocker = null;
        for(int i = 0; i < n2rp.size() && k - i >= 0; i++) {
            Node root = n2rp.get(i);
            printKLevelsDown2(root, k - i, blocker);
            blocker = root;
        }
    }

    public static void pathToLeafFromRoot(Node node, String path, int sum, int lo, int hi){
        if(node == null) return;

        path += node.data;
        sum += node.data;
        
        if(node.left == null && node.right == null) {
            // leaf
            if(lo <= sum && sum <= hi) {
                System.out.println(path);
            }    
            return;
        }

        pathToLeafFromRoot(node.left, path + " ", sum, lo, hi);
        pathToLeafFromRoot(node.right, path + " ", sum, lo, hi);
    }

    public static Node createLeftCloneTree(Node root){
        if(root == null) return null;

        Node leftRoot = createLeftCloneTree(root.left);
        Node rightRoot = createLeftCloneTree(root.right);

        Node nn = new Node(root.data);
        
        nn.left = leftRoot;
        root.left = nn;
        root.right = rightRoot;

        return root;
    }   

    public static Node transBackFromLeftClonedTree(Node root){
        if(root == null) return null;

        Node lroot = transBackFromLeftClonedTree(root.left.left);
        Node rroot = transBackFromLeftClonedTree(root.right);

        root.right = rroot;
        root.left = lroot;
        return root;
    }

    public static void printSingleChildNodes(Node node, Node parent){
        if(node == null) return;
        // self cgeck
        if(parent != null && 
            ((parent.left == null && parent.right != null) || 
            (parent.right == null && parent.left != null))) {
            System.out.println(node.data);
        }
        // left + right
        printSingleChildNodes(node.left, node);
        printSingleChildNodes(node.right, node);
    }

    // call -> foolish, base case-> smart
    public static void traverse1(Node node) {
        if(node == null) return;

        traverse1(node.left);
        traverse1(node.right);
    }

    // no base case, every call is smart
    public static void traverse2(Node node) {
        if(node.left != null)
            traverse1(node.left);
        if(node.right != null)
            traverse1(node.right);
    }

    public static void traverse3(Node node) {
        if(node.left != null && node.right != null) {
            // left + right both exist
            traverse3(node.left);
            traverse3(node.right);
        } else if(node.left != null) {
            // left exist
            traverse3(node.left);
        } else if(node.right != null) {
            // right exist
            traverse3(node.right);
        } else {
            // no child exist, you are at leaf
            // no call
        }
    }

    public static Node removeLeaves(Node node){
        if(node.left != null && node.right != null) {
            // left + right both exist
            node.left = removeLeaves(node.left);
            node.right = removeLeaves(node.right);
        } else if(node.left != null) {
            // left exist
            node.left = removeLeaves(node.left);
        } else if(node.right != null) {
            // right exist
            node.right = removeLeaves(node.right);
        } else {
            // no child exist, you are at leaf
            return null;
        }
        return node;
    }

    public static int diameter1(Node node) {
        if(node == null) return 0;

        int ld = diameter1(node.left);
        int rd = diameter1(node.right);

        int lh = height(node.left);
        int rh = height(node.right);
        int md = lh + rh + 1;

        return Math.max(md, Math.max(ld, rd));
    }


    public static class DPair {
        int ht;
        int dia;

        DPair(int ht, int dia) {
            this.ht = ht;
            this.dia = dia;
        }
    }

    public static DPair diameter2(Node node) {
        if(node == null) {
            return new DPair(-1, 0);
        }

        DPair lpair = diameter2(node.left);
        DPair rpair = diameter2(node.right);

        int my_ht = Math.max(lpair.ht, rpair.ht) + 1;
        int my_dia = Math.max(lpair.ht + rpair.ht + 2, Math.max(lpair.dia, rpair.dia));
        DPair mpair = new DPair(my_ht, my_dia);
        return mpair;
    }

    static int tilt = 0;
    private static int tilt_(Node node) {
        if(node == null) return 0;

        int lsum = tilt_(node.left);
        int rsum = tilt_(node.right);
        tilt += Math.abs(lsum - rsum);

        return lsum + rsum + node.data;
    }

    public static int tilt(Node node){
        tilt_(node);
        return tilt;
    }

    public static class BSTPair {
        boolean isBST;
        int max;
        int min;
        int size;

        BSTPair(boolean isBST, int max, int min, int size) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
        }
    }
    public static int maxSize = 0;
    public static Node nn;
    public static BSTPair isBST_(Node node) {
        if(node == null) {
            return new BSTPair(true, Integer.MIN_VALUE, Integer.MAX_VALUE, 0);
        }
        BSTPair lpair = isBST_(node.left);
        BSTPair rpair = isBST_(node.right);
        int mx = Math.max(Math.max(lpair.max, rpair.max), node.data);
        int mn = Math.min(Math.min(lpair.min, rpair.min), node.data);
        int msize = lpair.size + rpair.size + 1;
        boolean isbst = lpair.isBST && rpair.isBST && (lpair.max < node.data && node.data < rpair.min);

        if(isbst == true && msize > maxSize) {
            maxSize = msize;
            nn = node;
        }

        BSTPair mpair = new BSTPair(isbst, mx, mn, msize);
        return mpair;
    }

    public static boolean isBST(Node node) {
        if(node == null) return true;
        boolean lres = isBST(node.left);
        boolean rres = isBST(node.right);
        int lmax = max(node.left);
        int rmin = min(node.right);
        boolean mres = lmax < node.data && node.data < rmin;
        return lres && rres && mres;
    }

    public static class BalancePair {
        int ht;
        boolean isBalance;

        BalancePair(boolean isBalance, int ht) {
            this.isBalance = isBalance;
            this.ht = ht;
        }
    }

    public static BalancePair isBalanced_(Node node) {
        if(node == null) {
            return new BalancePair(true, 0);
        }
        BalancePair lpair = isBalanced_(node.left);
        BalancePair rpair = isBalanced_(node.right);

        int mh = Math.max(lpair.ht, rpair.ht) + 1;
        boolean mbalance = Math.abs(lpair.ht - rpair.ht) <= 1 && lpair.isBalance && rpair.isBalance;
        BalancePair mpair = new BalancePair(mbalance, mh);
        return mpair;
    }


    public static void main(String[] args) {
        fun();
    }

}