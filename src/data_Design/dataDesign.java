package data_Design;

import java.util.HashMap;

public class dataDesign {


}

/**
 * LeetCode 146
 * 请你设计并实现一个满足 LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value)如果关键字key 已经存在，则变更其数据值value ；如果不存在，则向缓存中插入该组key-value 。如果插入操作导致关键字数量超过capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 */
class LRUCache {
    private HashMap<Integer,Node> map;
    private DoubleList cache;
    private int cap;
    public LRUCache(int capacity) {
        this.cap=capacity;
        this.cache=new DoubleList();
        this.map=new HashMap<>();
    }

    public int get(int key) {
        if(!map.containsKey(key)) return -1;

        Node tmp=map.get(key);
        cache.remove(tmp);
        cache.addLast(tmp);
        return map.get(key).val;
    }
    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node tmp=map.get(key);
            cache.remove(tmp);
        }
        if(cache.getSize()==cap){
            Node tmp=cache.removeFirst();
            map.remove(tmp.key);
        }
        Node add=new Node(key,value);
        cache.addLast(add);
        map.put(key,add);
    }
}
class Node{
    public int key,val;
    public Node next,prev;
    public Node(int k,int v){
        this.key=k;
        this.val=v;
    }
}
class DoubleList{
    private Node head,tail;
    private int size;
    public DoubleList(){
        head=new Node(0,0);
        tail=new Node(0,0);
        head.next=tail;
        tail.prev=head;
        size=0;
    }
    public void addLast(Node x){
        x.prev=tail.prev;
        x.next=tail;
        tail.prev.next=x;
        tail.prev=x;
        size++;
    }
    public void remove(Node x){
        x.prev.next=x.next;
        x.next.prev=x.prev;
        size--;
    }
    public Node removeFirst(){
        if(head.next==tail)
            return null;
        Node first=head.next;
        remove(first);
        return first;
    }
    public int getSize(){
        return size;
    }
}
