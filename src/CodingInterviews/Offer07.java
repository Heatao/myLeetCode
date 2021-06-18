package CodingInterviews;

import medium.InorderTraversal94;
import test.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Offer07 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length != inorder.length)
            return null;
        int len = preorder.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, map, 0, len-1, 0, len-1);
    }

    private TreeNode buildTree(int[] preorder, Map<Integer, Integer> map,
                           int preLeft, int preRight, int inLeft, int inRight) {
        // 第二次写的时候，发现下面这个判断条件才是最难记住的orz
        if (preLeft > preRight || inLeft > inRight)
            return null;
        int pIndex = map.get(preorder[preLeft]);
        TreeNode root = new TreeNode(preorder[preLeft]);
        root.left = buildTree(preorder, map, preLeft+1, pIndex+preLeft-inLeft, inLeft, pIndex-1);
        root.right = buildTree(preorder, map, preRight+pIndex+1-inRight, preRight, pIndex+1, inRight );
        return root;
    }

    public static void main(String[] args) {
        Offer07 offer07 = new Offer07();
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        InorderTraversal94 inorderTraversal94 = new InorderTraversal94();
        System.out.println(inorderTraversal94.inorderTraversal(offer07.buildTree(preorder, inorder)).toString());
    }
}
