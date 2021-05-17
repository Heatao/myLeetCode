package easy;

import test.TreeNode;

public class SortedArrayToBST108 {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums.length == 0)
            throw new RuntimeException("data format error.");
        return buildTree(nums, 0, nums.length-1);
    }

    private TreeNode buildTree(int[] nums, int start, int end) {
        if (start > end)
            return null;
        int mid = (end - start) / 2 + start;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = buildTree(nums, start, mid-1);
        root.right = buildTree(nums, mid+1, end);
        return root;
    }
}
