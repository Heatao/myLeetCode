package medium;

import test.Node;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class CopyRandomList138 {
    public Node copyRandomList(Node head) {
        if (head == null)
            return null;
        Node newHead = new Node(head.val);
        Node node = head, newNode = newHead;
        HashMap<Node, Node> newNodeMap = new HashMap<>();
        newNodeMap.put(head, newHead);
        while (node.next != null) {
            newNode.next =new Node(node.next.val);
            node = node.next;
            newNode = newNode.next;
            newNodeMap.put(node, newNode);
        }

        node = head;
        newNode = newHead;
        while (node !=null) {
            if (node.random != null) {
                newNode.random = newNodeMap.get(node.random);
            }
            node = node.next;
            newNode = newNode.next;
        }
        return newHead;
    }
}
