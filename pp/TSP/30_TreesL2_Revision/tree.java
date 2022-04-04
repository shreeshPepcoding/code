import java.util.*;

public class tree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = this.right = null;
        }
    }

    // leetcode 105, https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
    private TreeNode constructPreIn(int[] pre, int[] in, int preSt, int preEnd, int inSt, int inEnd) {
        if(preSt > preEnd) {
            return null;
        }

        TreeNode root = new TreeNode(pre[preSt]);
        int indx = inSt;
        while(in[indx] != root.val)
            indx++;

        int eleCount = indx - inSt;
        root.left = constructPreIn(pre, in, preSt + 1, preSt + eleCount, inSt, indx - 1);
        root.right = constructPreIn(pre, in, preSt + eleCount + 1, preEnd, indx + 1, inEnd);
        return root;
    }
    
    public TreeNode buildTree_01(int[] pre, int[] in) {
        return constructPreIn(pre, in, 0, pre.length - 1, 0, in.length - 1);
    }

    // leetcode 106, https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
    private TreeNode constructPostIn(int[] post, int[] in, int poSt, int poEnd, int inSt, int inEnd) {
        if(poSt > poEnd)
            return null;

        TreeNode root = new TreeNode(post[poEnd]);

        int indx = inSt;
        while(in[indx] != root.val) 
            indx++;
        
        int eleCount = indx - inSt;
        root.left = constructPostIn(post, in, poSt, poSt + eleCount - 1, inSt, indx - 1);
        root.right = constructPostIn(post, in, poSt + eleCount, poEnd - 1, indx + 1, inEnd);

        return root;
    }
    
    public TreeNode buildTree_02(int[] in, int[] post) {
        return constructPostIn(post, in, 0, post.length - 1, 0, in.length - 1);
    }

    // leetcode 889, https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
    private TreeNode constructPrePost(int[] pre, int[] post, int preSt, int preEnd, int poSt, int poEnd) {
        // single element
        if(preSt == preEnd) {
            return new TreeNode(pre[preSt]);
        }
        // invalid range
        if(preSt > preEnd) {
            return null;
        }

        TreeNode root = new TreeNode(pre[preSt]);
        int ele = pre[preSt + 1];
        int indx = poSt;
        while(post[indx] != ele)
            indx++;
        
        int eleCount = indx - poSt + 1;
        root.left = constructPrePost(pre, post, preSt + 1, preSt + eleCount, poSt, indx);
        root.right = constructPrePost(pre, post, preSt + eleCount + 1, preEnd, indx + 1, poEnd - 1);
        return root;
    }
    
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        return constructPrePost(pre, post, 0, pre.length - 1, 0, post.length - 1);
    }

    // https://practice.geeksforgeeks.org/problems/construct-tree-from-inorder-and-levelorder/1/
    private Node constructInLevel(int[] in, int inSt, int inEnd, ArrayList<Integer> level) {
        if(level.size() == 0) return null;
        Node root = new Node(level.get(0));
        int indx = inSt;
        HashSet<Integer> set = new HashSet<>();
        while(in[indx] != root.data) {
            set.add(in[indx]);
            indx++;
        }
        ArrayList<Integer> llvl = new ArrayList<>(); // left level order
        ArrayList<Integer> rlvl = new ArrayList<>(); // right level order

        for(int i = 1; i < level.size(); i++) {
            int val = level.get(i);
            if(set.contains(val)) {
                llvl.add(val);
            } else {
                rlvl.add(val);
            }
        }

        root.left = constructInLevel(in, inSt, indx - 1, llvl);
        root.right = constructInLevel(in, indx + 1, inEnd, rlvl);
        return root;
    }

    Node buildTree(int[] in, int[] level) {
        ArrayList<Integer> lvl = new ArrayList<>();
        for(int val : level) lvl.add(val);

        return constructInLevel(in, 0, in.length - 1, lvl);
    }

    // leetcode 108, https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
    private TreeNode constructBST_In(int[] in, int lo, int hi) {
        if(lo > hi) return null;
        int mid = (lo + hi) / 2;
        TreeNode root = new TreeNode(in[mid]);
        root.left = constructBST_In(in, lo, mid - 1);
        root.right = constructBST_In(in, mid + 1, hi);
        return root;
    }

    public TreeNode sortedArrayToBST(int[] in) {
        return constructBST_In(in, 0, in.length - 1);
    }

    // leetcode 1008, https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
    static int index = 0;
    private TreeNode constructBST_Pre(int[] pre, int leftRange, int rightRange) {
        if(index >= pre.length || pre[index] < leftRange || pre[index] > rightRange) {
            return null;
        }

        int val = pre[index++];
        TreeNode root = new TreeNode(val);
        root.left = constructBST_Pre(pre, leftRange, val);
        root.right = constructBST_Pre(pre, val, rightRange);
        return root;
    }

    public TreeNode bstFromPreorder(int[] pre) {
        index = 0;
        return constructBST_Pre(pre, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // construct BST from postorder, https://practice.geeksforgeeks.org/problems/construct-bst-from-post-order/1/
    private static Node constructBST_Post(int[] post, int leftRange, int rightRange) {
        if(index < 0 || post[index] < leftRange || post[index] > rightRange) {
            return null;
        }

        int val = post[index--];
        Node root = new Node(val);
        root.right = constructBST_Post(post, val, rightRange);
        root.left = constructBST_Post(post, leftRange, val);
        return root;
    }
    
    public static Node constructTree(int post[],int n) {
        index = post.length - 1;
        return constructBST_Post(post, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // construct BST from levelOrder, https://practice.geeksforgeeks.org/problems/convert-level-order-traversal-to-bst/1
    private Node constructBST_levelOrder(ArrayList<Integer> level) {
        if(level.size() == 0) {
            return null;
        }
        Node root = new Node(level.get(0));
        ArrayList<Integer> llvl = new ArrayList<>();
        ArrayList<Integer> rlvl = new ArrayList<>();
        for(int i = 1; i < level.size(); i++) {
            int val = level.get(i);
            if(val < root.data) {
                llvl.add(val);
            } else {
                rlvl.add(val);
            }
        }

        root.left = constructBST_levelOrder(llvl);
        root.right = constructBST_levelOrder(rlvl);
        return root;
    }
    
    public Node constructBST(int[] arr) {
        ArrayList<Integer> lvl = new ArrayList<>();
        for(int val : arr) lvl.add(val);
        return constructBST_levelOrder(lvl);
    }

    // leetcode 968, https://leetcode.com/problems/binary-tree-cameras/
    static int camera = 0;
    // state 0 -> camera is present
    // state 1 -> I am covered
    // state 2 -> I am unsafe

    private int minCameraCover_rec(TreeNode root) {
        if(root == null) {
            return 1; // null is safe, parent try to save yourself
        }
        int leftState = minCameraCover_rec(root.left);
        int rightState = minCameraCover_rec(root.right);

        // post area
        if(leftState == 1 && rightState == 1) {
            // root is unsafe
            return 2;
        } else if(leftState == 2 || rightState == 2) {
            // camera is required
            camera++;
            return 0;
        } else {
            return 1;
        }
    }

    public int minCameraCover(TreeNode root) {
        camera = 0;
        int finalState = minCameraCover_rec(root);
        if(finalState == 2) camera++;

        return camera;
    }

    // leetcode 337, https://leetcode.com/problems/house-robber-iii/
    private class RPair {
        int withRob;
        int withoutRob;

        RPair(int withRob, int withoutRob) {
            this.withRob = withRob;
            this.withoutRob = withoutRob;
        }
    }

    private RPair rob_rec(TreeNode root) {
        if(root == null) {
            return new RPair(0, 0);
        }

        RPair left = rob_rec(root.left);
        RPair right = rob_rec(root.right);

        int a = left.withRob;
        int b = left.withoutRob;
        int a_ = right.withRob;
        int b_ = right.withoutRob;
        int c = root.val;

        // robbery on root, c + b + b_
        int withRobbery = c + b + b_;
        // no robbery, max(a, b) + max(a_, b_)
        int withoutRobbery = Math.max(a, b) + Math.max(a_, b_);

        return new RPair(withRobbery, withoutRobbery);
    }

    public int rob(TreeNode root) {
        RPair res = rob_rec(root);
        return Math.max(res.withRob, res.withoutRob);
    }

    // leetcode 1372, https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/
    static int len = 0;
    private class ZPair {
        int lstart;
        int rstart;

        ZPair(int lstart, int rstart) {
            this.lstart = lstart;
            this.rstart = rstart;
        }
    }

    private ZPair longestZigZag_(TreeNode root) {
        if(root == null) return new ZPair(-1, -1);
        ZPair lpair = longestZigZag_(root.left);
        ZPair rpair = longestZigZag_(root.right);

        ZPair mpair = new ZPair(lpair.rstart + 1, rpair.lstart + 1);
        len = Math.max(len, Math.max(mpair.lstart, mpair.rstart));
        return mpair;
    }

    public int longestZigZag(TreeNode root) {
        len = 0;
        longestZigZag_(root);
        return len;
    }

    // leetcode 98, https://leetcode.com/problems/validate-binary-search-tree/

    // leetcode 99, https://leetcode.com/problems/recover-binary-search-tree/
    // pointer[0] -> prev
    // pointer[1] -> curr
    // pointer[2] -> a
    // pointer[3] -> b
    // static TreeNode prev, curr, a, b;
    private void recoverTree_(TreeNode root, TreeNode[] pointers) {
        if(root == null) return;
        recoverTree_(root.left, pointers);
        // inorder area
        if(pointers[0] == null) {
            pointers[0] = root;
        } else {
            pointers[1] = root; // curr -> root
            if(pointers[0].val > pointers[1].val) {
                // prev > curr
                if(pointers[2] == null) {
                    // first encounter
                    pointers[2] = pointers[0];
                    pointers[3] = pointers[1];
                } else {
                    // second encounter
                    pointers[3] = root;
                }
            }
            // set prev
            pointers[0] = pointers[1];
        }
        recoverTree_(root.right, pointers);
    }

    public void recoverTree(TreeNode root) {
        TreeNode[] pointers = new TreeNode[4];
        recoverTree_(root, pointers);
        // swap value of 'a' and 'b'
        int temp = pointers[2].val;
        pointers[2].val = pointers[3].val;
        pointers[3].val = temp;
    }

    // leetcode 297, https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
    // Encodes a tree to a single string.
    private void serialize_(TreeNode root, StringBuilder sb) {
        if(root == null) {
            sb.append("null#");
            return;
        }
        sb.append(root.val + "#");
        serialize_(root.left, sb);
        serialize_(root.right, sb);
    }

    public String serialize(TreeNode root) {
        if(root == null) return "null#";
        StringBuilder sb = new StringBuilder();
        serialize_(root, sb);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    private class SPair {
        TreeNode node;
        int state;

        SPair(TreeNode node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public TreeNode deserialize(String str) {
        if(str.equals("null#")) return null;
        String[] data = str.split("#");
        int indx = 1;
        TreeNode root = new TreeNode(Integer.parseInt(data[0]));
        Stack<SPair> st = new Stack<>();
        st.push(new SPair(root, 0));
        while(indx < data.length) {
            if(st.peek().state == 0) {
                if(data[indx].equals("null") == true) {
                    st.peek().state++;
                    indx++;
                } else {
                    TreeNode nn = new TreeNode(Integer.parseInt(data[indx]));
                    indx++;
                    st.peek().state++;
                    st.peek().node.left = nn;
                    st.push(new SPair(nn, 0));
                }
            } else if(st.peek().state == 1) {
                if(data[indx].equals("null") == true) {
                    st.peek().state++;
                    indx++;
                } else {
                    TreeNode nn = new TreeNode(Integer.parseInt(data[indx]));
                    indx++;
                    st.peek().state++;
                    st.peek().node.right = nn;
                    st.push(new SPair(nn, 0));
                }
            } else {
                st.pop(); // state is equal to 2
            }
        }
        return root;
    }

    // leetcode 199, https://leetcode.com/problems/binary-tree-right-side-view/
    public List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> qu = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        if(root == null) return list;
        qu.add(root);

        while(qu.size() > 0) {
            int sz = qu.size();
            TreeNode temp = null;
            while(sz-- > 0) {
                TreeNode rem = qu.remove();
                if(rem.left != null) qu.add(rem.left);
                if(rem.right != null) qu.add(rem.right);
                temp = rem;
            }
            list.add(temp.val);
        }
        return list;
    }

    // width of shadow of binary tree, portal
    static int lh = 0;
    static int rh = 0;

    private static void width_(TreeNode root, int count) {
        if(root == null) return;

        if(count < lh) {
            lh = count;
        } else if(count > rh) {
            rh = count;
        }

        width_(root.left, count - 1);
        width_(root.right, count + 1);
    }

    public static int width(TreeNode root) {
        if(root == null) return  0;
        lh = 0;
        rh = 0;
        width_(root, 0);
        return rh - lh + 1;
    }

    // leetcode 662, https://leetcode.com/problems/maximum-width-of-binary-tree/
    private class WPair {
        TreeNode node;
        int index;

        WPair(TreeNode node, int index) {
            this.node = node;
            this.index = index;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        Queue<WPair> qu = new LinkedList<>();
        qu.add(new WPair(root, 0));
        int overallWidth = 0;
        while(qu.size() > 0) {
            int sz = qu.size();
            int lmi = qu.peek().index;
            int rmi = 0;
            while(sz-- > 0) {
                WPair rem = qu.remove();
                rmi = rem.index;

                if(rem.node.left != null) qu.add(new WPair(rem.node.left, 2 * rem.index + 1));
                if(rem.node.right != null) qu.add(new WPair(rem.node.right, 2 * rem.index + 2));
            }
            overallWidth = Math.max(overallWidth, rmi - lmi + 1);
        }
        return overallWidth;
    }

    // vertical order 1, https://practice.geeksforgeeks.org/problems/print-a-binary-tree-in-vertical-order/1
    static class VPair {
        int state;
        Node node;

        public VPair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    static ArrayList<Integer> verticalOrder(Node root) {
        Queue<VPair> qu = new LinkedList<>();
        qu.add(new VPair(root, 0));
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        int lh = 0;
        int rh = 0;

        while(qu.size() > 0) {
            int sz = qu.size();
            while(sz-- > 0) {
                VPair rem = qu.remove();
                if(map.containsKey(rem.state)) {
                    map.get(rem.state).add(rem.node.data);
                } else {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(rem.node.data);
                    map.put(rem.state, list);
                }
                if(rem.state < lh) {
                    lh = rem.state;
                }
                if(rem.state > rh) {
                    rh = rem.state;
                }
                if(rem.node.left != null) qu.add(new VPair(rem.node.left, rem.state - 1));
                if(rem.node.right != null) qu.add(new VPair(rem.node.right, rem.state + 1));
            }
        }

        ArrayList<Integer> res = new ArrayList<>();
        for(int i = lh; i <= rh; i++) {
            for(int ele : map.get(i)) {
                res.add(ele);
            }
        }
        return res;
    }

    // leetcode 987, https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
    private class VTPair implements Comparable<VTPair> {
        int v_index;
        TreeNode node;

        VTPair(TreeNode node, int v_index) {
            this.node = node;
            this.v_index = v_index;
        }

        public int compareTo(VTPair o) {
            if(this.v_index == o.v_index) {
                return this.node.val - o.node.val;
            } else {
                return this.v_index - o.v_index;
            }
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        PriorityQueue<VTPair> ppq = new PriorityQueue<>(); // ppq -> parent priority queueu
        PriorityQueue<VTPair> cpq = new PriorityQueue<>(); // cpq -> child priority queueu
        lh = 0;
        rh = 0;
        width_(root, 0);
        ppq.add(new VTPair(root, Math.abs(lh)));
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i <= rh - lh; i++) {
            res.add(new ArrayList<>());
        }

        while(ppq.size() > 0) {
            int sz = ppq.size();
            while(sz-- > 0) {
                VTPair rem = ppq.remove();
                res.get(rem.v_index).add(rem.node.val);

                if(rem.node.left != null) cpq.add(new VTPair(rem.node.left, rem.v_index - 1));
                if(rem.node.right != null) cpq.add(new VTPair(rem.node.right, rem.v_index + 1));
            }
            ppq = cpq;
            cpq = new PriorityQueue<>();
        }
        return res;
    }

    // diagonal traversal of binary tree, https://practice.geeksforgeeks.org/problems/diagonal-traversal-of-binary-tree/1/
    public ArrayList<Integer> diagonal(Node root) {
        Queue<Node> que = new LinkedList<>();
        que.add(root);
        ArrayList<Integer> res = new ArrayList<>();
        while(que.size() > 0) {
            int sz = que.size();
            while(sz-- > 0) {
                Node rem = que.remove();
                while(rem != null) {
                    res.add(rem.data);
                    if(rem.left != null) que.add(rem.left);
                    rem = rem.right;
                }
            }
        }
        return res;
    }
    
    
    public static void main(String[] args) {

    }
}