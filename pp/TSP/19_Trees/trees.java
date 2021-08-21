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

    public static void main(String[] args) {
        // trees level 2    
    }
}