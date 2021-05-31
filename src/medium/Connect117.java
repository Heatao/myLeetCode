package medium;

import test.Node;

public class Connect117 {
    public Node connect(Node root) {
        if (root == null)
            return null;

        Node leftMost = root;
        //因为是站在上一层的角度去构建下一层的next指针的
        while (findNext(leftMost) != null) {
            Node head = leftMost;
            while (head != null) {
                if (head.left != null) {
                    if (head.right != null) {
                        head.left.next = head.right;
                        head.right.next = findNext(head.next);
                    }
                    else head.left.next = findNext(head.next);
                }
                else if (head.right != null)
                    head.right.next = findNext(head.next);
                head = head.next;
            }
            leftMost = findNext(leftMost);
        }
        return root;
    }

    private Node findNext(Node node) {
        if (node == null)
            return null;
        if (node.left != null) return node.left;
        if (node.right != null) return node.right;
        if (node.next != null) {
            node = node.next;
            return findNext(node);
        }
        return null;
    }
}
