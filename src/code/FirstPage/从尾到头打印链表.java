package code.FirstPage;

import util.ListNode;

import java.util.ArrayList;

public class 从尾到头打印链表 {
    ArrayList list = new ArrayList();

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if (listNode != null) {
            printListFromTailToHead(listNode.next);
            list.add(listNode.val);
        }
        return list;
    }
}

