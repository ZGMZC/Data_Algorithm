package listnode_Recursion;

import common_Utils.ListNode;

public class listnodeRecursion {
    /**
     * LeetCode 206
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     * 递归做法
     * @param head
     * @return
     */
    public ListNode reverseList_0(ListNode head){
        if(head==null||head.next==null)
            return head;
        ListNode last=reverseList_0(head.next);
        head.next.next=head;
        head.next=null;
        return last;
    }
    /**
     * LeetCode 206
     * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
     * 原地修改链表
     * @param head
     * @return
     */
    public ListNode reverseList_1(ListNode head){
        ListNode tmp=null,first=null;
        while (head!=null){
            tmp=head.next;
            head.next=first;
            first=head;
            head=tmp;
        }
        return first;
    }

    /**
     * 反转链表的前n个结点
     * @param head
     * @param n
     * @return
     */
    ListNode successor=null;
    public ListNode reverseN(ListNode head, int n){
        if(n==1){
            successor=head.next;
            return head;
        }
        ListNode last=reverseN(head.next,n-1);
        head.next.next=head;
        head.next=successor;
        return last;
    }
    /**
     * LeetCode 92
     * 给你单链表的头指针 head 和两个整数left 和 right ，其中left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
     * 递归做法
     * @param head
     * @param left
     * @param right
     * @return
     */
    public ListNode reverseBetween(ListNode head, int left, int right){
        if(left==1){
            return reverseN(head,right);
        }
        head.next=reverseBetween(head.next,left-1,right-1);
        return head;
    }
}
