package medium;

import test.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 参考https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/cong-qian-xu-yu-zhong-xu-bian-li-xu-lie-gou-zao-9/
 * 这个题最好是在纸上画出下标和参数
 * 前序遍历 preLeft preLeft+1 ... pIndex-inLeft+preLeft pIndex-inLeft+preLeft+1...preRight
 * 中序遍历 inLeft ... pIndex-1 pIndex pIndex+1 ... inRight
 */
public class BuildTree105 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int preLen = preorder.length;
        int inLen = inorder.length;
        if (preLen != inLen)
            throw new RuntimeException("Incorrect input data.");

        //用一个hash表方便找到根节点的位置
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inLen; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preLen-1, map, 0, inLen-1);
    }

    private TreeNode buildTree(int[] preorder, int preLeft, int preRight,
                               Map<Integer, Integer> map, int inorderLeft, int inorderRight) {
        if (preLeft > preRight || inorderLeft > inorderRight)
            return null;

        int rootVal = preorder[preLeft];
        TreeNode root = new TreeNode(rootVal);
        int pIndex = map.get(rootVal);

        // 递归地构造左子树，并连接到根节点
        root.left = buildTree(preorder, preLeft+1, pIndex-inorderLeft+preLeft, map, inorderLeft, pIndex-1);
        // 递归地构造右子树，并连接到根节点
        root.right = buildTree(preorder, pIndex-inorderLeft+preLeft+1, preRight, map, pIndex+1, inorderRight);

        return root;
    }
}
