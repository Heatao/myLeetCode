package interviewer;

import java.util.HashMap;
import java.util.Scanner;


class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode() {}
    public TreeNode(int val) { this.val = val; }
    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


public class Main5_0808 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] line1 = in.nextLine().split(" ");
        int n = Integer.parseInt(line1[0]);
        int m = Integer.parseInt(line1[1]);
        int k = Integer.parseInt(line1[2]);

        TreeNode root = new TreeNode(k);
        HashMap<Integer, TreeNode> hashMap = new HashMap<>();
        hashMap.put(0, root);

        int cmt = 1;
        for (int i = 0; i < n; i++) {
            String[] line = in.nextLine().split(" ");
            int valuel = Integer.parseInt(line[0]);
            TreeNode nodel = new TreeNode(Integer.parseInt(line[0]));
            if (valuel != 0) {
                hashMap.get(i).left = nodel;
                hashMap.put(cmt, nodel);
            }
            else hashMap.get(i).left = null;
            cmt++;

            TreeNode noder = new TreeNode(Integer.parseInt(line[1]));
            if (valuel != 0) {
                hashMap.get(i).right = nodel;
                hashMap.put(cmt, noder);
            }
            else hashMap.get(i).right = null;
            cmt++;
        }

        String[] lineLast = in.nextLine().split(" ");
        int[] changeNode = new int[lineLast.length];
        for (int i = 0; i < lineLast.length; i++) {
            changeNode[i] = Integer.parseInt(lineLast[i]);
        }

        // m用作交换
        int len = changeNode.length;
        for (int i = 0; i < len; i++) {
            int index = changeNode[i];
            TreeNode tmp;
            tmp = hashMap.get(index).left;
            hashMap.get(index).left = hashMap.get(index).right;
            hashMap.get(index).right = tmp;
        }
    }

    private static void inOrder(TreeNode node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.println(node.val);
        inOrder(node.right);
    }
}
