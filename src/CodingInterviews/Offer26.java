package CodingInterviews;

import test.TreeNode;

public class Offer26 {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) return false;
        preOrder(A, B);
        return tag;
    }

    /**
     * 下面的做法是错的，因为val的值不一样
     */
    boolean tag = false;
    private void preOrder(TreeNode A, TreeNode B) {
        if (tag || B == null) {
            tag = true;
            return;
        }
        if (A == null) return;
        if (A.val == B.val) {
            preOrder(A.left, B.left);
            preOrder(A.right, B.right);
        }
        else {
            preOrder(A.left, B);
            preOrder(A.right, B);
        }
    }

    /**
     * 不停的判断子结构是否成立
     */
    public boolean others_isSubStructure(TreeNode A, TreeNode B) {
        return (A != null && B != null) && (recur(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }
    boolean recur(TreeNode A, TreeNode B) {
        if(B == null) return true;
        if(A == null || A.val != B.val) return false;
        return recur(A.left, B.left) && recur(A.right, B.right);
    }

}
