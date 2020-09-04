package medium;

import java.math.BigInteger;

/**
 * LeetCode2.两数相加
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 *  * Definition for singly-linked list.
 *  * public class ListNode {
 *  *     int val;
 *  *     ListNode next;
 *  *     ListNode(int x) { val = x; }
 *  * }
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * */
public class twoNumPlus {
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

    public ListNode insert(ListNode head, int thisNum){
        ListNode n = new ListNode(thisNum);
        n.next = head;
        head = n;
        return head;
    }

    public ListNode mySolution_addTwoNumbers(ListNode l1, ListNode l2) {
        /**
         * 最基本的思路是，先拿到两个数，加起来，再按照规定的格式返回
         * 会不会大数字溢出？
         * 实际证明会。。。
         * 最后尝试long也不够，所以采用了BigInteger，这个对象不限制长度
         * */
        BigInteger firstNum = new BigInteger("0");
        BigInteger i = new BigInteger("1");
        //注意这里的判断不需要thisNode.next!=null，自己想清楚为什么
        for (ListNode thisNode=l1; thisNode!=null; thisNode=thisNode.next){
            BigInteger temp = i.multiply(BigInteger.valueOf(thisNode.val));
            firstNum = firstNum.add(temp);
            i = i.multiply(BigInteger.valueOf(10));
        }
        BigInteger secondNum = new BigInteger("0");
        i = BigInteger.valueOf(1);
        for (ListNode thisNode=l2; thisNode!=null; thisNode=thisNode.next){
            BigInteger temp = i.multiply(BigInteger.valueOf(thisNode.val));
            secondNum = secondNum.add(temp);
            i = i.multiply(BigInteger.valueOf(10));
        }

        //用字符串做避免判断sum有多少位
        //采用头插法
        BigInteger sum = firstNum.add(secondNum);
        String s = sum.toString();
        char [] c = s.toCharArray();
        int intNum;
        ListNode head = null;
        for (char cc : c){
            intNum = Integer.parseInt(String.valueOf(cc));
            head = insert(head, intNum);
        }
        return head;
    }

    public static ListNode official_addTwoNumbers(ListNode l1, ListNode l2) {   //这里加static是这个方法没有用这个类的任何东西，而且static往往更快
        /**
         * 这个思路是模仿加法进位的形式，让两个链表的每一位相加，再设置一个进位数
         */
        if (l1 == null && l2 == null) return null;              //考虑corner case是一个好习惯
        ListNode dummyNode = new ListNode(0);                //LinkedList常用做法，dummyNode，值不重要
        ListNode head = dummyNode;                              //因为dummyNode会往后移动，然后赋值，所以需要一个始终指向头部的head

        int addNum = 0;
        while(l1!=null || l2!=null || addNum!=0){
            int val1 = l1 == null ? 0:l1.val;
            int val2 = l2 == null ? 0:l2.val;

            int sum = val1 + val2 + addNum;
            dummyNode.next = new ListNode(sum % 10);

            //我自己本来想这么写，但是这样的话最后一个node不好处理，如果是赋值给next，那冗余的位置就在第一个，head直接next就没了
//            dummyNode.val = sum % 10;
//            dummyNode.next = new ListNode(0);
            dummyNode = dummyNode.next;

            addNum = sum / 10;
            if (l1!=null) l1 = l1.next;
            if (l2!=null) l2 = l2.next;
        }
        return head.next;
    }
}
