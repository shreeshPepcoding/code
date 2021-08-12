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

    public TreeNode buildTree(int[] inorder, int[] postorder) {
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

    public static void main(String[] args) {
        // trees level 2    
    }
}