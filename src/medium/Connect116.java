package medium;

import test.Node;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 这个题要是不要求用常量级额外空间的话，用层次遍历倒是挺简单的
 * 忽然觉得因为是完美二叉树，所以可否直接前序遍历拿到列表，然后有某种规律？
 */
public class Connect116 {
    public Node connect(Node root) {
        if (root == null)
            return null;
        levelOrder(root);
        return root;
    }

    private void levelOrder(Node node) {
        Queue<Node> queue = new ArrayDeque<>();
        Node lNode = new Node(), rNode;

        //其实不用两个Len，在内存个的for循环之前判断一下长度就可以啦～
        int len = 1;
        int newLen = 1;
        queue.offer(node);
        while (!queue.isEmpty()) {
            len = newLen;
            newLen = 0;
            int i;
            for (i = 0; i < len; i++) {
                rNode = queue.poll();
                if (i != 0)
                    lNode.next = rNode;

                lNode = rNode;
                if (lNode.left != null) {
                    queue.offer(lNode.left);
                    newLen++;
                }
                if (lNode.right != null) {
                    queue.offer(lNode.right);
                    newLen++;
                }
            }
            lNode.next = null;
        }
    }

    public Node official_connect(Node root) {
        if (root == null) {
            return root;
        }

        // 从根节点开始
        Node leftmost = root;

        while (leftmost.left != null) {

            // 遍历这一层节点组织成的链表，为下一层的节点更新 next 指针
            Node head = leftmost;

            while (head != null) {

                // CONNECTION 1
                head.left.next = head.right;

                // CONNECTION 2
                if (head.next != null) {
                    head.right.next = head.next.left;
                }

                // 指针向后移动
                head = head.next;
            }

            // 去下一层的最左的节点
            leftmost = leftmost.left;
        }

        return root;
    }
}
