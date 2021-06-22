package CodingInterviews;

import test.Node;

/**
 * 要求原地
 */
public class Offer36 {
    /**
     * 看了一眼评论区做出来了
     */
    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        inOrder(root);
        // 到最后的时候preNode就是最后一个节点
        head.left = preNode;
        preNode.right = head;
        return head;
    }

    private Node preNode, head;
    private void inOrder(Node node) {
        if (node.left != null) inOrder(node.left);
        if (head == null) {
            head = node;
        }
        else if (preNode != null) {
            preNode.right = node;
            node.left = preNode;
        }
        preNode = node;
        if (node.right != null) inOrder(node.right);
    }
}
