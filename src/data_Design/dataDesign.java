package data_Design;

import common_Utils.DoubleListNode;

import java.util.HashMap;
import java.util.LinkedHashSet;

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
    private HashMap<Integer,DoubleListNode> map;
    private DoubleList cache;
    private int cap;
    public LRUCache(int capacity) {
        this.cap=capacity;
        this.cache=new DoubleList();
        this.map=new HashMap<>();
    }

    public int get(int key) {
        if(!map.containsKey(key)) return -1;

        DoubleListNode tmp=map.get(key);
        cache.remove(tmp);
        cache.addLast(tmp);
        return map.get(key).val;
    }
    public void put(int key, int value) {
        if(map.containsKey(key)){
            DoubleListNode tmp=map.get(key);
            cache.remove(tmp);
        }
        if(cache.getSize()==cap){
            DoubleListNode tmp=cache.removeFirst();
            map.remove(tmp.key);
        }
        DoubleListNode add=new DoubleListNode(key,value);
        cache.addLast(add);
        map.put(key,add);
    }
}
class DoubleList{
    private DoubleListNode head,tail;
    private int size;
    public DoubleList(){
        head=new DoubleListNode(0,0);
        tail=new DoubleListNode(0,0);
        head.next=tail;
        tail.prev=head;
        size=0;
    }
    public void addLast(DoubleListNode x){
        x.prev=tail.prev;
        x.next=tail;
        tail.prev.next=x;
        tail.prev=x;
        size++;
    }
    public void remove(DoubleListNode x){
        x.prev.next=x.next;
        x.next.prev=x.prev;
        size--;
    }
    public DoubleListNode removeFirst(){
        if(head.next==tail)
            return null;
        DoubleListNode first=head.next;
        remove(first);
        return first;
    }
    public int getSize(){
        return size;
    }
}

/**
 * LeetCode 460
 * 请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。
 *
 * 实现 LFUCache 类：
 *
 * LFUCache(int capacity) - 用数据结构的容量capacity 初始化对象
 * int get(int key)- 如果键key 存在于缓存中，则获取键的值，否则返回 -1 。
 * void put(int key, int value)- 如果键key 已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量capacity 时，则应该在插入新项之前，移除最不经常使用的项。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最近最久未使用 的键。
 * 为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。
 *
 * 当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。
 *
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 */
class LFUCache {
    //key到val的映射，KV表
    HashMap<Integer,Integer> keyToVal;
    //key到freq的映射，KF表
    HashMap<Integer,Integer> keyToFreq;
    //freq到key列表的映射，我们成FK表
    HashMap<Integer, LinkedHashSet<Integer>> freqToKeys;
    //记录最小的频次
    int minFreq;
    //记录LFU缓存的最大容量
    int cap;
    public LFUCache(int capacity) {
        keyToVal=new HashMap<>();
        keyToFreq=new HashMap<>();
        freqToKeys=new HashMap<>();
        this.cap=capacity;
        this.minFreq=0;
    }

    public int get(int key) {
        if(!keyToVal.containsKey(key))
            return -1;

        /*增加Freq的操作*/
        increaseFreq(key);

        return keyToVal.get(key);
    }

    public void put(int key, int value) {
        if(this.cap<=0) return;

        /*若key已经存在，修改对应的val即可*/
        if(keyToVal.containsKey(key)){
            keyToVal.put(key,value);
            increaseFreq(key);
            return;
        }
        /*若key不存在，需要插入*/
        /*容量已满的话需要淘汰一个freq最小的key*/
        if(this.cap<=keyToVal.size())
            removeMinFreqKey();

        /*插入key和value，对应的freq+1*/
        //插入KV表
        keyToVal.put(key,value);
        //插入KF表
        keyToFreq.put(key,1);
        //插入FK表
        freqToKeys.putIfAbsent(1, new LinkedHashSet<>());
        freqToKeys.get(1).add(key);
        // 插入新 key 后最小的 freq 肯定是 1
        this.minFreq = 1;

    }
    private void increaseFreq(int key) {
        int freq = keyToFreq.get(key);
        /* 更新 KF 表 */
        keyToFreq.put(key, freq + 1);
        /* 更新 FK 表 */
        // 将 key 从 freq 对应的列表中删除
        freqToKeys.get(freq).remove(key);
        // 将 key 加入 freq + 1 对应的列表中
        freqToKeys.putIfAbsent(freq + 1, new LinkedHashSet<>());
        freqToKeys.get(freq + 1).add(key);
        // 如果 freq 对应的列表空了，移除这个 freq
        if (freqToKeys.get(freq).isEmpty()) {
            freqToKeys.remove(freq);
            // 如果这个 freq 恰好是 minFreq，更新 minFreq
            if (freq == this.minFreq) {
                this.minFreq++;
            }
        }
    }
    private void removeMinFreqKey() {
        // freq 最小的 key 列表
        LinkedHashSet<Integer> keyList = freqToKeys.get(this.minFreq);
        // 其中最先被插入的那个 key 就是该被淘汰的 key
        int deletedKey = keyList.iterator().next();
        /* 更新 FK 表 */
        keyList.remove(deletedKey);
        if (keyList.isEmpty()) {
            freqToKeys.remove(this.minFreq);
            // 问：这里需要更新 minFreq 的值吗？
        }
        /* 更新 KV 表 */
        keyToVal.remove(deletedKey);
        /* 更新 KF 表 */
        keyToFreq.remove(deletedKey);
    }
}
