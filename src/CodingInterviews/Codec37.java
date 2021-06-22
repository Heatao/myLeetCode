package CodingInterviews;

import test.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Codec37 {

    // 构造函数没有返回值！
    public Codec37(){
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return null;
        ArrayList<Integer> levelOrder = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                levelOrder.add(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            }
            else levelOrder.add(null);
        }
        return levelOrder.toString().replace(" ", "");
    }

    /**
     * 下面是错误的，因为题目要求的不是完全二叉树，所以不能用i/2这样的规律找到根节点，需要用队列来构建
     */
    // Decodes your encoded data to tree.
    public TreeNode my_deserialize(String data) {
        if (data == null) return null;
        String[] dataArray = data.substring(1, data.length()-1).split(",");
        if (dataArray.length == 0) return null;
        TreeNode root = new TreeNode(Integer.parseInt(dataArray[0]));
        TreeNode[] nodeArray = new TreeNode[dataArray.length+1];
        nodeArray[1] = root;
        for (int i = 2; i <= dataArray.length; i++) {
            TreeNode node;
            if (!dataArray[i-1].equals("null"))
                node = new TreeNode(Integer.parseInt(dataArray[i-1]));
            else node = null;
            nodeArray[i] = node;

            int fatherIndex = i / 2;
            if (nodeArray[fatherIndex] != null) {
                if (i % 2 == 0) nodeArray[fatherIndex].left = node;
                else nodeArray[fatherIndex].right = node;
            }
        }
        return root;
    }

    public TreeNode deserialize(String data) {
        if (data == null) return null;
        String[] vals = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        Queue<TreeNode> queue = new LinkedList<>() {{ add(root); }};
        int i = 1;
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(!vals[i].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(vals[i]));
                queue.add(node.left);
            }
            i++;
            if(!vals[i].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(vals[i]));
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }

    public static void main(String[] args) {
        Codec37 codec37 = new Codec37();

    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
