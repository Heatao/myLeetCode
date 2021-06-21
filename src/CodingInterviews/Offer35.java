package CodingInterviews;

import test.Node;

import java.util.HashMap;

public class Offer35 {
    /**
     * 虽然感觉做过，但是完全不记得之前怎么做的了orz
     * 现在的想法是用一个Hash表记录原节点到新节点的映射
     */
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        HashMap<Node, Node> hashMap = new HashMap<>();
        Node curNode = head;
        Node prevNewNode = new Node(0);
        while (curNode != null) {
            Node newNode = new Node(curNode.val);
            prevNewNode.next = newNode;
            prevNewNode = prevNewNode.next;

            hashMap.put(curNode, newNode);
            curNode = curNode.next;
        }

        curNode = head;
        while (curNode != null) {
            Node newNode = hashMap.get(curNode);
            if (curNode.random != null)
                newNode.random = hashMap.get(curNode.random);
            else newNode.random = null;
            curNode = curNode.next;
        }
        return hashMap.get(head);
    }
}
