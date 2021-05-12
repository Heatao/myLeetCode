package medium;

import test.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 105和106在开始画图的时候不要涉及具体的数字！
 */
public class BuildTree106 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int inLen = inorder.length;
        int postLen = postorder.length;
        if (inLen != postLen)
            throw new RuntimeException("Incorrect input data.");
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inLen; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(postorder, 0, postLen-1, map, 0, inLen-1);
    }

    // 编程习惯：in在post前面
    private TreeNode buildTree(int[] postorder, int inLeft, int inRight,
                               Map<Integer, Integer> map, int postLeft, int postRight) {
        if (postLeft > postRight || inLeft > inRight)
            return null;
        int rootVal = postorder[postRight];
        TreeNode root = new TreeNode(rootVal);

        int pIndex = map.get(rootVal);
        root.left = buildTree(postorder, inLeft, pIndex-1, map, postLeft, pIndex-1-inLeft+postLeft);
        root.right = buildTree(postorder, pIndex+1, inRight, map, postRight+pIndex-inRight, postRight-1);
        return root;
    }

    public static void main(String[] args) {
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        BuildTree106 buildTree106 = new BuildTree106();
        buildTree106.buildTree(inorder, postorder);
    }
}
