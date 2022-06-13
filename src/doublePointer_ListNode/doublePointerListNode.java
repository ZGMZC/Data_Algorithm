package doublePointer_ListNode;
import common_Utils.ListNode;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class doublePointerListNode {
    /**
     * LeetCode 21
     *将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //dummy 虚拟头
        ListNode dummy=new ListNode(Integer.MIN_VALUE),p=dummy;
        ListNode p1=list1,p2=list2;
        while (p1!=null&&p2!=null){
            if(p1.val> p2.val){
                p.next=p2;
                p2=p2.next;
            }else{
                p.next=p1;
                p1=p1.next;
            }
            p=p.next;
        }
        p.next= p1==null?p2:p1;
        return dummy.next;
    }

    /**
     * LeetCode 23
     * 给你一个链表数组，每个链表都已经按升序排列。
     * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
     *
     * @param lists
     * @return
     */
    /*方法一，遍历整个数组，进行 两两排序，即调用mergeTwoLists方法*/
    public ListNode mergeKLists_0(ListNode[] lists){
        ListNode p=new ListNode(Integer.MIN_VALUE);
        for(ListNode tmp:lists){
            p=mergeTwoLists(p,tmp);
        }
        return p.next;
    }
    /*方法二，使用优先级队列（二叉堆）把链表节点放入一个最小堆，就可以每次获得 k 个
节点中的最小节点*/
    public ListNode mergeKLists_1(ListNode[] lists){
        if(lists.length==0) return null;
        ListNode dummy=new ListNode(Integer.MIN_VALUE),p=dummy;
        PriorityQueue<ListNode> que=new PriorityQueue<>(lists.length,new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val-o2.val;
            }
        });
        for(ListNode head:lists){
            if(head!=null)
                que.offer(head);
        }
        while (!que.isEmpty()){
            ListNode node=que.poll();
            p.next=node;
            if(node.next!=null)
                que.offer(node.next);
            p=p.next;
        }
        return dummy.next;
    }

    /**
     * LeetCode 19
     * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n){
        /*在链表头部接一个虚拟节点 dummy 是为了避免删除倒数第一个元素时出现空指针异常
        在头部加入 dummy 节点并不影响尾部倒数第 k 个元素是什么*/
        ListNode dummy=new ListNode(-1);
        dummy.next=head;
        ListNode temp=dummy,res=dummy;
        //i<n+1 找到倒数第n+1个节点
        for(int i=0;i<n+1;i++){
            temp=temp.next;
        }
        while(temp!=null){
            temp=temp.next;
            res=res.next;
        }
        //res指向倒数第n+1个节点,即要删除节点的前一个节点
        res.next=res.next.next;
        return dummy.next;
    }

    /**
     * LeetCode 876
     * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
     *
     * 如果有两个中间结点，则返回第二个中间结点。
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head){
        ListNode slow=head,fast=head;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

    /**
     * LeetCode 141
     * 给你一个链表的头节点 head ，判断链表中是否有环。
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递。仅仅是为了标识链表的实际情况。
     * 如果链表中存在环，则返回 true 。 否则，返回 false 。
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head){
        ListNode slow=head,fast=head;
        while (fast!=null&& fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast)
                return true;
        }
        return false;
    }

    /**
     * LeetCode 142
     * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
     * @param head
     * @return
     */
    public ListNode detectCycle(ListNode head){
        ListNode slow=head,fast=head;
        while(fast!=null &&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast)
                break;
        }
        if(fast==null||fast.next==null) return null;
        fast=head;
        while(fast!=slow){
            fast=fast.next;
            slow=slow.next;
        }
        return fast;
    }

    /**
     * LeetCode 160
     * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 null 。
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB){
        ListNode p1=headA,p2=headB;
        while (p1!=p2){
            p1= p1==null?headB:p1.next;
            p2= p2==null?headA:p2.next;
        }
        return p1;
    }

    /**
     * LeetCode 83
     * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null) return head;
        ListNode slow=head,fast=head;
        while(fast!=null){
            if(fast.val!=slow.val){
                slow=slow.next;
                slow.val=fast.val;
            }
            fast=fast.next;
        }
        slow.next=null;
        return head;
    }
}
