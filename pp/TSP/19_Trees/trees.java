import java.util.*;

public class trees {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static class Node {
        Node left, right;
        int data;
        public ArrayList<Node> children;
        public Node(int data) {
            this.data = data;
            this.left = this.right = null;
            this.children = new ArrayList<>();
        }
    }

    // leetcode 105 https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
    private TreeNode constructPreIn(int[] pre, int[] in, int preSt, int preEnd, int inSt, int inEnd) {
        if(preSt > preEnd) return null;

        TreeNode root = new TreeNode(pre[preSt]);

        // find presence of rot node in inorder with index indx
        int indx = inSt;
        while(in[indx] != pre[preSt]) 
            indx++;
        
        int elementCount = indx - inSt;
        root.left = constructPreIn(pre, in, preSt + 1, preSt + elementCount, inSt, indx - 1);
        root.right = constructPreIn(pre, in, preSt + elementCount + 1, preEnd, indx + 1, inEnd);

        return root;
    }

    public TreeNode buildTree_1(int[] preorder, int[] inorder) {
        if(preorder.length == 0) return null;
        return constructPreIn(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    // leetcode 106 https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
    public TreeNode constructPostIn(int[] post, int[] in, int poSt, int poEnd, int inSt, int inEnd) {
        if(poSt > poEnd) return null;

        TreeNode root = new TreeNode(post[poEnd]);

        int indx = inSt;
        while(in[indx] != post[poEnd])
            indx++;

        int elementCount = indx - inSt;
        root.left = constructPostIn(post, in, poSt, poSt + elementCount - 1, inSt, indx - 1);
        root.right = constructPostIn(post, in, poSt + elementCount, poEnd - 1, indx + 1, inEnd);
        return root;
    }

    public TreeNode buildTree_2(int[] inorder, int[] postorder) {
        if(inorder.length == 0) return null;

        return constructPostIn(postorder, inorder, 0, postorder.length - 1, 0, inorder.length - 1);
    }

    // leetcode 889 https://leetcode.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/
    private TreeNode constructPrePost(int[] pre, int[] post, int preSt, int preEnd, int poSt, int poEnd) {
        if(preSt == preEnd) 
            return new TreeNode(pre[preSt]);

        if(preSt > preEnd) 
            return null;

        TreeNode root = new TreeNode(pre[preSt]);
        int ele = pre[preSt + 1];
        int indx = poSt;

        while(post[indx] != ele) {
            indx++;
        }
        int elementCount = indx - poSt + 1;
        root.left = constructPrePost(pre, post, preSt + 1, preSt + elementCount, poSt, indx);
        root.right = constructPrePost(pre, post, preSt + elementCount + 1, preEnd, indx + 1, poEnd - 1);
        return root;
    }

    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        if(pre.length == 0) return null;
        return constructPrePost(pre, post, 0, pre.length - 1, 0, post.length - 1);
    }

    // https://practice.geeksforgeeks.org/problems/construct-tree-from-inorder-and-levelorder/1
    public Node constructInLevel(int[] in, ArrayList<Integer> level, int inSt, int inEnd) {
        if(level.size() == 0) return null;

        Node root = new Node(level.get(0));
        int indx = inSt;
        HashSet<Integer> set = new HashSet<>();
        while(in[indx] != level.get(0)) {
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

        root.left = constructInLevel(in, llvl, inSt, indx - 1);
        root.right = constructInLevel(in, rlvl, indx + 1, inEnd);

        return root;
    }

    Node buildTree(int inord[], int lvl[]) {
        ArrayList<Integer> level = new ArrayList<>();
        for(int val : lvl)
            level.add(val);
        return constructInLevel(inord, level, 0, inord.length - 1);
    }

    // leetcode 108. Construct BST using inorder
    // https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
    public TreeNode constructBST_using_In(int[] in, int lo, int hi) {
        if(lo > hi) return null;

        int mid = lo + (hi - lo) / 2;
        TreeNode root = new TreeNode(in[mid]);

        root.left = constructBST_using_In(in, lo, mid - 1);
        root.right = constructBST_using_In(in, mid + 1, hi);
        return root;
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return constructBST_using_In(nums, 0, nums.length - 1);
    }

    // leetcode 1008 https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
    static int indx = 0;
    public TreeNode bstFromPreorder(int[] pre, int leftRange, int rightRange) {
        if(indx >= pre.length || pre[indx] < leftRange || rightRange < pre[indx]) {
            return null;
        }
        int val = pre[indx++];
        TreeNode root = new TreeNode(val);

        root.left = bstFromPreorder(pre, leftRange, val);
        root.right = bstFromPreorder(pre, val, rightRange);
        return root;
    }

    public TreeNode bstFromPreorder(int[] pre) {
        indx = 0;
        return bstFromPreorder(pre, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // construct bst from preorder https://practice.geeksforgeeks.org/problems/construct-bst-from-post-order/1
    public static Node bstFromPostorder(int[] post, int leftRange, int rightRange) {
        if(indx < 0 || post[indx] < leftRange || rightRange < post[indx]) {
            return null;
        }
        int val = post[indx--];
        Node root = new Node(val);

        root.right = bstFromPostorder(post, val, rightRange);
        root.left = bstFromPostorder(post, leftRange, val);
        return root;
    }
    
    public static Node constructTree(int post[],int n) {
        indx = n - 1;
        return bstFromPostorder(post, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    // leetcode 968. https://leetcode.com/problems/binary-tree-cameras/
    static int camera = 0;
    // state 0 -> Camera present
    // state 1 -> i'm cover
    // state 2 -> i'm unsafe
    public int minCamera(TreeNode root) {
        if(root == null) return 1;
        int lstate = minCamera(root.left);
        int rstate = minCamera(root.right);
        if(lstate == 1 && rstate == 1) {
            return 2;
        } else if(lstate == 2 || rstate == 2) {
            camera++;
            return 0;
        } else {
            return 1;
        }
    }

    public int minCameraCover(TreeNode root) {
        camera = 0;
        int state = minCamera(root);
        if(state == 2) camera++;

        return camera;
    }

    // leetcode 337. https://leetcode.com/problems/house-robber-iii/
    public class RPair {
        int withRob;
        int withoutRob;

        public RPair(int withRob, int withoutRob) {
            this.withRob = withRob;
            this.withoutRob = withoutRob;
        }
    }

    public RPair robberyInHouse(TreeNode node) {
        if(node == null) {
            return new RPair(0, 0);
        }

        RPair left = robberyInHouse(node.left);
        RPair right = robberyInHouse(node.right);

        int a = left.withRob;
        int a_ = right.withRob;
        int b = left.withoutRob;
        int b_ = right.withoutRob;
        int c = node.val;
        // robbery (Done) b + b' + c
        // without robbery -> (a + a') vs. (a + b') vs. (a' + b) vs (b + b')
        int withRob = b + b_ + c;
        // int withoutRob = Math.max(Math.max(a + a_, a + b_), Math.max(a_ + b, b + b_));
        // OR
        int withoutRob = Math.max(a,b) + Math.max(a_, b_);

        return new RPair(withRob, withoutRob);
    }

    public int rob(TreeNode root) {
        RPair res = robberyInHouse(root);
        return Math.max(res.withRob, res.withoutRob);
    }

    // leetcode 1372. https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/
    public class ZHelper { // zigzag helper
        int lStart; // if end  at left
        int rStart; // if end at right
        int omax; // 

        public ZHelper(int lStart, int rStart, int omax) {
            this.lStart = lStart;
            this.rStart = rStart;
            this.omax = omax;
        }
    }

    public ZHelper longest_ZigZag(TreeNode node) {
        if(node == null) return new ZHelper(-1, -1, 0);

        ZHelper left = longest_ZigZag(node.left);
        ZHelper right = longest_ZigZag(node.right);

        int startAtL = left.rStart + 1;
        int startAtR = right.lStart + 1;
        int omax = Math.max(Math.max(left.omax, right.omax), Math.max(startAtL, startAtR));

        return new ZHelper(startAtL, startAtR, omax);
    }

    public int longestZigZag(TreeNode root) {
        ZHelper res = longest_ZigZag(root);
        return res.omax;
    }

    // leetcode 98. https://leetcode.com/problems/validate-binary-search-tree/


    // leetcode 99. https://leetcode.com/problems/recover-binary-search-tree/
    // pointers[0] -> prev
    // pointers[1] -> curr
    // pointers[2] -> a
    // pointers[3] -> b
    public void recover_Tree(TreeNode root, TreeNode[] pointers) {
        if(root == null) return;

        recover_Tree(root.left, pointers);
        if(pointers[0] == null) {
            // prev == null
            pointers[0] = root;
        } else {
            pointers[1] = root;
            if(pointers[0].val > pointers[1].val) {
                // prev > curr
                if(pointers[3] == null) {
                    // first encounter
                    pointers[2] = pointers[0];
                    pointers[3] = pointers[1];
                } else {
                    // second encounter
                    pointers[3] = root;
                }
            }
            // move prev and curr
            pointers[0] = root;
        }
        recover_Tree(root.right, pointers);
    }

    public void recoverTree(TreeNode root) {
        TreeNode[] pointers = new TreeNode[4];
        recover_Tree(root, pointers);
        // swap value for a and b, i.e. pointers[2], pointers[3]
        int temp = pointers[2].val;
        pointers[2].val = pointers[3].val;
        pointers[3].val = temp;
    }

    // construct bst from level order -> https://practice.geeksforgeeks.org/problems/convert-level-order-traversal-to-bst/1
    public class LHelper {
        Node parent;
        int leftRange;
        int rightRange;
        
        public LHelper(Node parent, int leftRange, int rightRange) {
            this.parent = parent;
            this.leftRange = leftRange;
            this.rightRange = rightRange;
        }
    }

    public Node constructBST(int[] arr) {
        Queue<LHelper> qu = new LinkedList<>();
        qu.add(new LHelper(null, Integer.MIN_VALUE, Integer.MAX_VALUE));
        Node root = null;
        for(int i = 0; i < arr.length; i++) {
            Node nn = new Node(arr[i]);
            while(qu.peek().leftRange >= nn.data || qu.peek().rightRange <= nn.data) {
                qu.remove();
            }
            LHelper rem = qu.remove();
            qu.add(new LHelper(nn, rem.leftRange, nn.data));
            qu.add(new LHelper(nn, nn.data, rem.rightRange));

            if(rem.parent == null) {
                root = nn;
            } else if(rem.parent.data > nn.data) {
                rem.parent.left = nn;
            } else {
                rem.parent.right = nn;
            }
        }
        return root;
    }

    // leetcode 297.  https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
    // Encodes a tree to a single string.
    public void serialize_(TreeNode root, StringBuilder sb) {
        if(root == null) {
            sb.append("null#");
            return;
        }

        sb.append(root.val + "#");
        serialize_(root.left, sb);
        serialize_(root.right, sb);
    }

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize_(root, sb);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public class SPair {
        TreeNode node;
        int state;
        
        public SPair(TreeNode node, int state) {
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
                st.pop();
            }
        }
        return root;
    }

    // Encodes a tree to a single string.
    public static void serialize_1(Node node, StringBuilder sb) {
        sb.append(node.data + "#");
        for(Node child : node.children) {
            serialize_1(child, sb);
        }
        sb.append("null#");
    }

    public static String serialize(Node root) {
        if(root == null) return "null#";
        StringBuilder sb = new StringBuilder();
        serialize_1(root, sb);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public static Node deserialize_(String str) {
        if(str.equals("null#")) return null;

        String[] data = str.split("#");
        Node root = new Node(Integer.parseInt(data[0]));

        Stack<Node> st = new Stack<>();
        st.push(root);
        for(int i = 1; i < data.length; i++) {
            if(data[i].equals("null")) {
                st.pop();
            } else {
                Node nn = new Node(Integer.parseInt(data[i]));
                st.peek().children.add(nn);
                st.push(nn);
            }
        }
        return root;
    }

    // Width of shadow of binary tree
    static int lh = 0; // find min in left horizontal
    static int rh = 0; // find max in right horizontal

    private static void width(TreeNode root, int count) {
        if(root == null) return;

        if(count < lh) {
            lh = count;
        } else if(count > rh) {
            rh = count;
        }

        width(root.left, count - 1);
        width(root.right, count + 1);
    }

    public static int width(TreeNode root) {
        if(root == null) return 0;
        lh = 0;
        rh = 0;
        width(root, 0);
        return rh - lh + 1;
    }

    // vertical order traversal - I
    // https://practice.geeksforgeeks.org/problems/print-a-binary-tree-in-vertical-order/1
    static class Pair implements Comparable<Pair>{
        TreeNode node;
        int count;

        public Pair(TreeNode node, int count) {
            this.node = node;
            this.count = count;
        } 

        public int compareTo(Pair o) {
            return this.node.val - o.node.val;
        }
    }

    static ArrayList<Integer> verticalOrder(Node root) {
        Queue<Pair> qu = new LinkedList<>();
        qu.add(new Pair(root, 0));
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();

        int lh = 0; // left horizontal
        int rh = 0; // right horizontal 
        while(qu.size() > 0) {
            // 1. get + remove
            Pair rem = qu.remove();
            // 2. work
            if(map.containsKey(rem.count)) {
                map.get(rem.count).add(rem.node.data);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(rem.node.data);
                map.put(rem.count, list);
            }
            if(rem.count < lh) {
                lh = rem.count;
            } else if(rh < rem.count) {
                rh = rem.count;
            }
            // 3. add children
            if(rem.node.left != null) {
                qu.add(new Pair(rem.node.left, rem.count - 1));
            }
            if(rem.node.right != null) {
                qu.add(new Pair(rem.node.right, rem.count + 1));
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        for(int i = lh; i <= rh; i++) {
            for(int val : map.get(i)) {
                res.add(val);
            }
        }
        return res;
    }

    // method 2 without hashmap
    public static ArrayList<ArrayList<Integer>> verticalOrder2(TreeNode root) {
        lh = 0;
        rh = 0;
        int wd = width(root);
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        for(int i = 0; i < wd; i++)
            res.add(new ArrayList<>());

        Queue<Pair> qu = new LinkedList<>();
        qu.add(new Pair(root, Math.abs(lh)));

        while(qu.size() > 0) {
            // 1. get + remove
            Pair rem = qu.remove();
            // 2. work
            res.get(rem.count).add(rem.node.val);
            // 3. add children
            if(rem.node.left != null) {
                qu.add(new Pair(rem.node.left, rem.count - 1));
            }
            if(rem.node.right != null) {
                qu.add(new Pair(rem.node.right, rem.count + 1));
            }
        }
        return res;
    }


    // vertical order traversal - II
    // leetcode 987 https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        lh = 0;
        rh = 0;
        int wd = width(root);
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < wd; i++)
            res.add(new ArrayList<>());

        PriorityQueue<Pair> mainQ = new PriorityQueue<>();
            PriorityQueue<Pair> childQ = new PriorityQueue<>();
            
            mainQ.add(new Pair(root, Math.abs(lh)));

            while(mainQ.size() > 0) {
                while(mainQ.size() > 0) {
                    Pair rem = mainQ.remove();
                    res.get(rem.count).add(rem.node.val);

                    if(rem.node.left != null) {
                        childQ.add(new Pair(rem.node.left, rem.count - 1));
                    }
                    if(rem.node.right != null) {
                        childQ.add(new Pair(rem.node.right, rem.count + 1));
                    }
                }
                // swap the queues
                PriorityQueue<Pair> temp = mainQ;
                mainQ = childQ;
                childQ = temp;
            }
            return res;
        }

    // top view
    // link : https://practice.geeksforgeeks.org/problems/top-view-of-binary-tree/1
    static ArrayList<Integer> topView(Node root) {
        lh = 0;
        rh = 0;
        int wd = width(root);

        ArrayList<Integer> res = new ArrayList<>();
        for(int i = 0; i < wd; i++)
            res.add(null);

        Queue<Pair> qu = new LinkedList<>();
        qu.add(new Pair(root, Math.abs(lh)));

        while(qu.size() > 0) {
            // 1. get + remove
            Pair rem = qu.remove();
            // 2. work
            if(res.get(rem.count) == null)
                res.set(rem.count, rem.node.data);
            // 3. add children
            if(rem.node.left != null) {
                qu.add(new Pair(rem.node.left, rem.count - 1));
            }
            if(rem.node.right != null) {
                qu.add(new Pair(rem.node.right, rem.count + 1));
            }
        }
        return res;
    }

    // bottom view
    // link : https://practice.geeksforgeeks.org/problems/bottom-view-of-binary-tree/1
    public ArrayList <Integer> bottomView(Node root) {
        if(root == null) return new ArrayList<>();
        lh = 0;
        rh = 0;
        int wd = width(root);

        ArrayList<Integer> res = new ArrayList<>();
        for(int i = 0; i < wd; i++)
            res.add(null);

        Queue<Pair> qu = new LinkedList<>();
        qu.add(new Pair(root, Math.abs(lh)));

        while(qu.size() > 0) {
            // 1. get + remove
            Pair rem = qu.remove();
            // 2. work
            // if(res.get(rem.count) == null)
                res.set(rem.count, rem.node.data);
            // 3. add children
            if(rem.node.left != null) {
                qu.add(new Pair(rem.node.left, rem.count - 1));
            }
            if(rem.node.right != null) {
                qu.add(new Pair(rem.node.right, rem.count + 1));
            }
        }
        return res;
    }
   
    public static ArrayList<ArrayList<Integer>> diagonalOrder(TreeNode root) {
        Queue<TreeNode> qu = new LinkedList<>();
        qu.add(root);
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        while(qu.size() > 0) {
            int factorSize = qu.size();
            ArrayList<Integer> list = new ArrayList<>();
            while(factorSize-- > 0) {
                TreeNode factor = qu.remove();
                while(factor != null) {
                    list.add(factor.val);
                    if(factor.left != null)
                        qu.add(factor.left);

                    factor = factor.right;
                }
            }
            res.add(list);
        }
        return res;
    }  

    // diagonal anticlock-wise
    public static ArrayList<ArrayList<Integer>> diagonalOrderAntiClockWise(TreeNode root) {
        Queue<TreeNode> qu = new LinkedList<>();
        qu.add(root);
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if(root == null) return res;
        while(qu.size() > 0) {
            int factorSize = qu.size();
            ArrayList<Integer> list = new ArrayList<>();
            while(factorSize-- > 0) {
                TreeNode factor = qu.remove();
                while(factor != null) {
                    list.add(factor.val);
                    if(factor.right != null)
                        qu.add(factor.right);

                    factor = factor.left;
                }
            }
            res.add(list);
        }
        return res;
    }

    // diagonal order sum
    public static ArrayList<Integer> diagonalOrderSum(TreeNode root) {
        Queue<TreeNode> qu = new LinkedList<>();
        qu.add(root);
        ArrayList<Integer> res = new ArrayList<>();
        if(root == null) return res;
        while(qu.size() > 0) {
            int factorSize = qu.size();
            int sum = 0;
            while(factorSize-- > 0) {
                TreeNode factor = qu.remove();
                while(factor != null) {
                    sum += factor.val;
                    if(factor.left != null)
                        qu.add(factor.left);

                    factor = factor.right;
                }
            }
            res.add(sum);
        }
        return res;
    }
    
    // vertical order sum
    public static ArrayList<Integer> verticalOrderSum(TreeNode root) {
        if(root == null) return new ArrayList<>();
        lh = 0;
        rh = 0;
        int wd = width(root);

        ArrayList<Integer> res = new ArrayList<>();
        for(int i = 0; i < wd; i++)
            res.add(0);

        Queue<Pair> qu = new LinkedList<>();
        qu.add(new Pair(root, Math.abs(lh)));

        while(qu.size() > 0) {
            // 1. get + remove
            Pair rem = qu.remove();
            // 2. work
            // if(res.get(rem.count) == null)
                res.set(rem.count, res.get(rem.count) + rem.node.val);
            // 3. add children
            if(rem.node.left != null) {
                qu.add(new Pair(rem.node.left, rem.count - 1));
            }
            if(rem.node.right != null) {
                qu.add(new Pair(rem.node.right, rem.count + 1));
            }
        }
        return res;
    }

    // node to root path
    public static ArrayList<TreeNode> nodeToRootPath(TreeNode node, int data) {
        if(node == null) {
            return new ArrayList<>();
        }
        ArrayList<TreeNode> res = new ArrayList<>();
        if(node.val == data) {
            res.add(node);
            return res;
        }
        ArrayList<TreeNode> lres = nodeToRootPath(node.left, data);
        if(lres.size() > 0) {
            lres.add(node);
            return lres;
        }
        ArrayList<TreeNode> rres = nodeToRootPath(node.right, data);
        if(rres.size() > 0) {
            rres.add(node);
            return rres;
        }
        return res;
    }

    // leetcode 173 https://leetcode.com/problems/binary-search-tree-iterator/
    class BSTIterator {

        class Pair {
            TreeNode node;
            int state;
            
            public Pair(TreeNode node, int state) {
                this.node = node;
                this.state = state;
            }
        }
        
        private int itr_val = -1;
        
        private Stack<Pair> st;
        
        public BSTIterator(TreeNode root) {
            st = new Stack<>();
            st.push(new Pair(root, 0));
            next();
        }
        
        public int next() {
            int val2 = itr_val;
            itr_val = -1;
            while(st.size() > 0) {
                Pair top = st.peek();
                if(top.state == 0) {
                    if(top.node.left != null) {
                        st.push(new Pair(top.node.left, 0));
                    }
                    top.state++;
                } else if(top.state == 1) {
                    if(top.node.right != null) {
                        st.push(new Pair(top.node.right, 0));
                    }
                    top.state++;
                    itr_val = top.node.val;
                    break;
                } else {
                    st.pop();
                }
            }
            return val2;
        }
        
        public boolean hasNext() {
            if(itr_val == -1) return false;
            else return true;
        }
    }

    // method 2
    class BSTIterator2 {
        private Stack<TreeNode> st;

        private void addAllLeft(TreeNode node) {
            while(node != null) {
                st.push(node);
                node = node.left;
            }
        }

        public BSTIterator2(TreeNode root) {
            st = new Stack<>();
            addAllLeft(root);
        }
        
        public int next() {
            TreeNode rem = st.pop();
            if(rem.right != null) {
                addAllLeft(rem.right);
            }
            return rem.val;
        }
        
        public boolean hasNext() {
            return st.size() != 0;
        }
    }

    // root to all leaf
    private static void rootToAllLeafPath(TreeNode node, ArrayList<Integer> subres, 
    ArrayList<ArrayList<Integer>> res) {
        if(node == null) return;

        if(node.left == null && node.right == null) {
            // leaf
            ArrayList<Integer> duplicate = new ArrayList<>();
            for(int val : subres) {
                duplicate.add(val);
            }
            duplicate.add(node.val);
            res.add(duplicate);
            return;
        }

        subres.add(node.val);
        rootToAllLeafPath(node.left, subres, res);
        rootToAllLeafPath(node.right, subres, res);
        subres.remove(subres.size() - 1);
    }

    public static ArrayList<ArrayList<Integer>> rootToAllLeafPath(TreeNode root) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> subres = new ArrayList<>();
        rootToAllLeafPath(root, subres, res);
        return res;
    }

    // single child parent
    private static void exactlyOneChild(TreeNode root, ArrayList<Integer> ans) {
        if(root == null || (root.left == null && root.right == null)) {
            // is root null and leaf -> skip
            return;
        }

        if(root.left == null || root.right == null) {
            ans.add(root.val);
        }

        exactlyOneChild(root.left, ans);
        exactlyOneChild(root.right, ans);
    }

    public static ArrayList<Integer> exactlyOneChild(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        exactlyOneChild(root, ans);
        return ans;
    }

    // count of single child parent
    static int count = 0;

    public static void countExactlyOneChild_(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) {
            // is root null and leaf -> skip
            return;
        }

        if(root.left == null || root.right == null) {
            count++;
        }
        countExactlyOneChild_(root.left);
        countExactlyOneChild_(root.right);
    }

    public static int countExactlyOneChild1(TreeNode node) {
        count = 0;
        countExactlyOneChild_(node);
        return count;
    }

    // method 2 -> with int return type i.e. without static variable;
    public static int countExactlyOneChild(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) {
            // is root null and leaf -> skip
            return 0;
        }

        int ans = 0;
        if(root.left == null || root.right == null) {
            ans = 1;
        }
        ans += countExactlyOneChild(root.left);
        ans += countExactlyOneChild(root.right);
        return ans;
    }

    // morris inorder traversal
    public static TreeNode getRightMostNode(TreeNode temp, TreeNode curr) {
        while(temp.right != null && temp.right != curr) {
            temp = temp.right;
        }
        return temp;
    }

    public static ArrayList<Integer> morrisInTraversal(TreeNode node) {
        ArrayList<Integer> ans = new ArrayList<>();
        TreeNode curr = node;
        while(curr != null) {
            TreeNode leftNode = curr.left;
            if(leftNode != null) {
                TreeNode rightMostNode = getRightMostNode(leftNode, curr);
                if(rightMostNode.right != curr) {
                    // create a thread and move toward left tree
                    rightMostNode.right = curr; // thread creation
                    curr = curr.left;
                } else {
                    // if rightmostnode.right == curr that means left subtree is completely processed
                    // 1. print the value
                    ans.add(curr.val);
                    // 2. break thread
                    rightMostNode.right = null;
                    // 3. move toward right
                    curr = curr.right;
                }
            } else {
                // 1. print value
                ans.add(curr.val);
                // 2. move toward right
                curr = curr.right;
            }
        }
        return ans;
    }

    // morris preorder traversal
    public static ArrayList<Integer> morrisPreTraversal(TreeNode node) {
        ArrayList<Integer> res = new ArrayList<>();
        TreeNode curr = node;

        while(curr != null) {
            TreeNode leftNode = curr.left;
            if(leftNode != null) {
                TreeNode rightMostNode = getRightMostNode(leftNode, curr);
                if(rightMostNode.right != curr) {
                    res.add(curr.val);
                    rightMostNode.right = curr;
                    curr = curr.left;
                } else {
                    rightMostNode.right = null;
                    curr = curr.right;
                }
            } else {
                res.add(curr.val);
                curr = curr.right;
            }
        }
        return res;
    }

    // morris post order
    public static TreeNode getLeftMostNode(TreeNode temp, TreeNode curr) {
        while(temp.left != null && temp.left != curr) {
            temp = temp.left;
        }
        return temp;
    }

    public static ArrayList<Integer> morrisPostTraversal(TreeNode node) {
        ArrayList<Integer> res = new ArrayList<>();
        TreeNode curr = node;

        while(curr != null) {
            TreeNode rightNode = curr.right;
            if(rightNode != null) {
                TreeNode leftMostNode = getLeftMostNode(rightNode, curr);
                if(leftMostNode.left != curr) {
                    res.add(curr.val);
                    leftMostNode.left = curr;
                    curr = curr.right;
                } else {
                    leftMostNode.left = null;
                    curr = curr.left;
                }
            } else {
                res.add(curr.val);
                curr = curr.left;
            }
        }
        // reverse of res
        int left = 0;
        int right = res.size() - 1;

        while(left < right) {
            int val1 = res.get(left);
            int val2 = res.get(right);
            res.set(right, val1);
            res.set(left, val2);
        }
        return res;
    }

    // bst iterator using morris traversal
    class BSTIterator_3 {
        private TreeNode res = null;
        private TreeNode curr = null;
        public TreeNode getRightMostNode(TreeNode temp, TreeNode curr) {
            while(temp.right != null && temp.right != curr) {
                temp = temp.right;
            }
            return temp;
        }
        
        public void morrisInTraversal(TreeNode node) {
            this.curr = node;
            while(curr != null) {
                TreeNode leftNode = curr.left;
                if(leftNode != null) {
                    TreeNode rightMostNode = getRightMostNode(leftNode, curr);
                    if(rightMostNode.right != curr) {
                        rightMostNode.right = curr;
                        curr = curr.left;
                    } else {
                        this.res = curr;
                        rightMostNode.right = null;
                        curr = curr.right;
                        break;
                    }
                } else {
                    this.res = curr;
                    curr = curr.right;
                    break;
                }
            }
        }
        
        public BSTIterator_3(TreeNode root) {
            this.curr = root;
        }
        
        public int next() {
            morrisInTraversal(this.curr);
            int val = res.val;
            return val;
        }
        
        public boolean hasNext() {
            return this.curr != null;
        }
    }

    // leetcode 113, https://leetcode.com/problems/path-sum-ii/
    private void pathSum(TreeNode root, int targetSum, int ssf, List<Integer> psf, List<List<Integer>> res) {
        if(root == null) return;

        if(root.left == null && root.right == null) {
            if(ssf + root.val == targetSum) {
                // create a duplicate
                List<Integer> duplicate = new ArrayList<>();
                for(int val : psf)
                    duplicate.add(val);
                // insert root.val in duplicate
                duplicate.add(root.val);
                // add duplicate in res
                res.add(duplicate);
            }
            return;
        }

        psf.add(root.val);
        pathSum(root.left, targetSum, ssf + root.val, psf, res);
        pathSum(root.right, targetSum, ssf + root.val, psf, res);
        psf.remove(psf.size() - 1);
    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> subres = new ArrayList<>();
        pathSum(root, targetSum, 0, subres, res);
        return res;
    }

    // diameter of binary tree
    // leetcode 543 https://leetcode.com/problems/diameter-of-binary-tree/
    // method 1 -> with height call
    private int height(TreeNode node) {
        if(node == null) return -1;

        int lh = height(node.left);
        int rh = height(node.right);

        return Math.max(lh, rh) + 1;
    } 

    private int diameter1(TreeNode node) {
        if(node == null) return 0;

        int ld = diameter1(node.left);
        int rd = diameter1(node.right);

        int lh = height(node.left);
        int rh = height(node.right);
        int rootDia = lh + rh + 2;
        
        // return max of all three possibility
        return Math.max(rootDia, Math.max(ld, rd));
    }

    // method 2 -> with return height amd static diameter variable, travel and change stretagy
    static int diameter = 0;
    private int diamater2(TreeNode node) {
        if(node == null) return -1;

        int lh = diamater2(node.left);
        int rh = diamater2(node.right);

        diameter = Math.max(diameter, lh + rh + 2);

        return Math.max(lh, rh) + 1;
    } 

    // method 3 -> with wrapper class having diameter and height
    private class DiaPair {
        int height;
        int diamter;

        DiaPair(int height, int diameter) {
            this.height = height;
            this.diamter = diameter;
        }

        DiaPair() {
            this.height = -1;
            this.diamter = 0;
        }
    }

    private DiaPair diameter3(TreeNode node) {
        if(node == null) return new DiaPair();

        DiaPair lpair = diameter3(node.left);
        DiaPair rpair = diameter3(node.right);

        DiaPair mpair = new DiaPair();
        mpair.height = Math.max(lpair.height, rpair.height) + 1;
        mpair.diamter = Math.max(lpair.height + rpair.height + 2, 
                        Math.max(lpair.diamter, rpair.diamter));

        return mpair;
    }

    public int diameterOfBinaryTree(TreeNode root) {
        // int dia = diameter1(root);

        diameter = 0;
        diamater2(root);
        int dia = diameter;

        // int dia = diameter3(root).diamter;
        return dia;
    }


    // max path sum between two leaf https://practice.geeksforgeeks.org/problems/maximum-path-sum/1#
    static int maxPathSum = Integer.MIN_VALUE;

    private int maxPathSum1(Node node) {
        int sum = 0;
        if(node.left != null && node.right != null) {
            int lsum = maxPathSum1(node.left);
            int rsum = maxPathSum1(node.right);
            maxPathSum = Math.max(maxPathSum, lsum + rsum + node.data);
            sum = Math.max(lsum, rsum) + node.data;
        } else if(node.left != null) {
            int lsum = maxPathSum1(node.left);
            sum = lsum + node.data;
        } else if(node.right != null) {
            int rsum = maxPathSum1(node.right);
            sum = rsum + node.data;
        } else {
            sum = node.data;            
        }
        return sum;
    }

    int maxPathSum(Node node) { 
        if(node == null) return 0;
        maxPathSum = Integer.MIN_VALUE;
        if(node.left != null && node.right != null) {
            int lsum = maxPathSum1(node.left);
            int rsum = maxPathSum1(node.right);
            maxPathSum = Math.max(maxPathSum, lsum + rsum + node.data);
        } else if(node.left != null) {
            int lsum = maxPathSum1(node.left);
            maxPathSum = Math.max(maxPathSum, lsum + node.data);
        } else if(node.right != null) {
            int rsum = maxPathSum1(node.right);
            maxPathSum = Math.max(maxPathSum, rsum + node.data);
        } else {
            // maxPathSum = node.data;
        }
        return maxPathSum;
    } 

    // all nodes in k distance 
    private static ArrayList<TreeNode> nodeToRootPathNodeType(TreeNode node, int data) {
        ArrayList<TreeNode> mres = new ArrayList<>();
        if(node == null) return mres;

        if(node.val == data) {
            mres.add(node);
            return mres;
        }

        ArrayList<TreeNode> lres = nodeToRootPathNodeType(node.left, data);
        if(lres.size() > 0) {
            lres.add(node);
            return lres;
        }
        ArrayList<TreeNode> rres = nodeToRootPathNodeType(node.right, data);
        if(rres.size() > 0) {
            rres.add(node);
            return rres;
        }
        return mres;
    }

    private static void kdown(TreeNode node, TreeNode blockage, int k, ArrayList<Integer> res) {
        if(node == null || node == blockage) return;

        if(k == 0) {
            res.add(node.val);
            return;
        }

        kdown(node.left, blockage, k - 1, res);
        kdown(node.right, blockage, k - 1, res);
    }

    public static ArrayList<Integer> distanceK(TreeNode root, int target, int k) {
        ArrayList<TreeNode> n2rpath = nodeToRootPathNodeType(root, target);

        ArrayList<Integer> res = new ArrayList<>();
        TreeNode blockage = null;
        for(int i = 0; i < n2rpath.size() && k - i >= 0; i++) {
            TreeNode node = n2rpath.get(i);
            kdown(node, blockage, k - i, res);
            blockage = node;
        }
        return res;
    }

    // burning tree
    static int maxTime = 0;
    private static void burningTree_(TreeNode node, TreeNode blockage, int time) {
        if(node == null || node == blockage) return;

        maxTime = Math.max(maxTime, time);

        burningTree_(node.left, blockage, time + 1);
        burningTree_(node.right, blockage, time + 1);
    }

    public static int burning_Tree(TreeNode root, int fireNode) {
        ArrayList<TreeNode> n2rpath = nodeToRootPathNodeType(root, fireNode);
        maxTime = 0;
        TreeNode blockage = null;
        for(int t = 0; t < n2rpath.size(); t++) {
            TreeNode node = n2rpath.get(t);
            burningTree_(node, blockage, t);
            blockage = node;
        }
        return maxTime;
    }

    // burning tree 2
    private static void burningTree_1(TreeNode node, TreeNode blockage, 
                            int time, ArrayList<ArrayList<Integer>> res) {
        if(node == null || node == blockage) return;

        if(time < res.size()) {
            res.get(time).add(node.val);
        } else {
            // time == res.size()
            ArrayList<Integer> subres = new ArrayList<>();
            subres.add(node.val);
            res.add(subres);
        }

        burningTree_1(node.left, blockage, time + 1, res);
        burningTree_1(node.right, blockage, time + 1, res);
    }
    public static ArrayList<ArrayList<Integer>> burningTree(TreeNode root, int data) {
        ArrayList<TreeNode> n2rpath = nodeToRootPathNodeType(root, data);
        TreeNode blockage = null;
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
 
        for(int t = 0; t < n2rpath.size(); t++) {
            TreeNode node = n2rpath.get(t);
            burningTree_1(node, blockage, t, res);
            blockage = node;
        }
        return res;
    }

    // leetcode 662. https://leetcode.com/problems/maximum-width-of-binary-tree/
    private class WPair {
        TreeNode node;
        int indx;

        public WPair(TreeNode node, int indx) {
            this.node = node;
            this.indx = indx;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        Queue<WPair> qu = new LinkedList<>();
        qu.add(new WPair(root, 0));
        int maxWidth = 0;
        while(qu.size() > 0) {
            int size = qu.size();
            int lm = qu.peek().indx; // left most index
            int rm = qu.peek().indx; // right most index
            while(size-- > 0) {
                // 1. get + remove
                WPair rem = qu.remove();
                // 2. work  
                rm = rem.indx;
                // 3. add children with index
                if(rem.node.left != null) {
                    qu.add(new WPair(rem.node.left, 2 * rem.indx + 1));
                }
                if(rem.node.right != null) {
                    qu.add(new WPair(rem.node.right, 2 * rem.indx + 2));
                }
            }
            // width of current level
            int width = rm - lm + 1;
            // maximise overall width
            maxWidth = Math.max(maxWidth, width);
        }
        return maxWidth;
    }

    // convert binary serach tree sorted doubly linked list
    static Node prev = null;
    
    private static void bToDLL_(Node node) {
        if(node == null) return;
        
        bToDLL_(node.left);
        // in area
        prev.right = node;
        node.left = prev;
        prev = node;
        bToDLL_(node.right);
    }

    public static Node bToDLL(Node root) {
        Node dummy = new Node(-1);
        prev = dummy;
        bToDLL_(root);
        
        Node head = dummy.right;
        head.left = prev;
        prev.right = head;
        
        return head;
    }

    // convert sorted DLL to BST
    private static Node getMid1(Node head) {
        Node slow = head;
        Node fast = head.right;

        while(fast != null && fast.right != null) {
            slow = slow.right;
            fast = fast.right.right;
        }
        return slow;
    }

    private static Node creationBST(Node head) {
        if(head == null) return null;
        Node mid = getMid1(head);
        if(mid.left != null) {
            mid.left.right = null;
            mid.left = null;
        }
        Node head2 = mid.right;
        if(mid.right != null) {
            mid.right.left = null;
            mid.right = null;
        }
        if(mid != head) 
            mid.left = creationBST(head);
        mid.right = creationBST(head2);
        return mid;
    }

    public static Node SortedDLLToBST(Node head) {
        Node root = creationBST(head);
        return root;
    }

    // leetcode 112 https://leetcode.com/problems/path-sum/
    private boolean hasPathSum_(TreeNode node, int target, int ssf) {
        if(node == null) return false;
        if(node.left == null && node.right == null) {
            return ssf + node.val == target;
        }
        return hasPathSum_(node.left, target, ssf + node.val) || hasPathSum_(node.right, target, ssf + node.val);
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        return hasPathSum_(root, targetSum, 0);
    }

    // LCA without node to root path
    static TreeNode lca = null;

    private static boolean solveLCA(TreeNode node, int data1, int data2) {
        if(node == null) return false;
        boolean self = node.val == data1 || node.val == data2;
        boolean left = false, right = false;
        left = solveLCA(node.left, data1, data2);
        if(lca == null)
            right = solveLCA(node.right, data1, data2);
        if((self && left) || (self && right) || (left && right)) {
            lca = node;
        }
        return self || left || right;
    }

    public static TreeNode lowestCommonAncestor(TreeNode node, int p, int q) {
        lca = null;
        solveLCA(node, p, q);
        return lca;
    }

    // leetcode 124 https://leetcode.com/problems/binary-tree-maximum-path-sum/
    static int maxPath = 0;
    
    private int maxPathSum_(TreeNode root) {
        if(root == null) return (int)-1e9;

        int lsum = maxPathSum_(root.left);
        int rsum = maxPathSum_(root.right);

        int val = Math.max(root.val, Math.max(lsum + root.val, rsum + root.val));
        maxPath = Math.max(maxPath, Math.max(val, lsum + root.val + rsum));

        return val;
    }

    public int maxPathSum(TreeNode root) {
        maxPath = Integer.MIN_VALUE;   
        maxPathSum_(root);
        return maxPath;
    }
    public static void main(String[] args) {
        // trees level 2    
    }
}