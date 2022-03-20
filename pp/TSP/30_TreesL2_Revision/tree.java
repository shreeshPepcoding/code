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

    public class Node {
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

    public static void main(String[] args) {

    }
}