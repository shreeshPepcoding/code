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

        public Node(int data) {
            this.data = data;
            this.left = this.right = null;
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
    public int minCameraCover(TreeNode root) {
        
    }

    // leetcode 337. https://leetcode.com/problems/house-robber-iii/
    public int rob(TreeNode root) {
        
    }

    // leetcode 1372. https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/
    public int longestZigZag(TreeNode root) {
        
    }

    // leetcode 98. https://leetcode.com/problems/validate-binary-search-tree/
    

    public static void main(String[] args) {
        // trees level 2    
    }
}