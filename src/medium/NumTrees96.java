package medium;

/**
 * 心路历程：我也知道要用DP啊，但是状态转义方程式是啥呢～
 * 公式看这个吧：https://leetcode-cn.com/problems/unique-binary-search-trees/solution/hua-jie-suan-fa-96-bu-tong-de-er-cha-sou-suo-shu-b/
 *
 * 假设 n 个节点存在二叉排序树的个数是 G (n)，令 f(i) 为以 i 为根的二叉搜索树的个数，则
 * G(n)=f(1)+f(2)+f(3)+f(4)+...+f(n)
 * 当 i 为根节点时，其左子树节点个数为 i-1 个，右子树节点为 n-i，则
 * f(i) = G(i-1)*G(n-i)
 * G(n)=G(0)∗G(n−1)+G(1)∗G(n−2)+...+G(n−1)∗G(0)
 */
public class NumTrees96 {
    public int numTrees(int n) {
        int[] G = new int[n+1];
        // 0和1的时候都只有一种情况
        G[0] = 1;
        G[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                G[i] += G[j]*G[i-1-j];
            }
        }
        return G[n];
    }

    public static void main(String[] args) {
        NumTrees96 numTrees96 = new NumTrees96();
        System.out.println(numTrees96.numTrees(3));
    }
}
