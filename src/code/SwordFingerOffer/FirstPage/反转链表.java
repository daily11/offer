package code.SwordFingerOffer.FirstPage;

import util.ListNode;

public class 反转链表 {
    public ListNode ReverseList(ListNode head) {
        if (head == null)
            return null;

        ListNode reversedHead = null;
        ListNode p = head;
        ListNode tmp = null;
        ListNode pre = null;

        while (p != null) {
            tmp = p.next;
            p.next = pre;
            if (tmp == null)
                reversedHead = p;
            pre = p;
            p = tmp;
        }

        return reversedHead;
    }
}
