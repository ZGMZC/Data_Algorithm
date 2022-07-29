package data_Design;

import common_Utils.DoubleListNode;

import java.util.*;

public class dataDesign {

    /**
     * LeetCode 224 基本计算器
     * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
     * <p>
     * 注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
     *
     * @param s
     * @return
     */
    public int calculate_0(String s) {
        // 存放所有的数字
        Deque<Integer> nums = new ArrayDeque<>();
        // 为了防止第一个数为负数，先往 nums 加个 0
        nums.addLast(0);
        // 将所有的空格去掉
        s = s.replaceAll(" ", "");
        // 存放所有的操作，包括 +/-
        Deque<Character> ops = new ArrayDeque<>();
        int n = s.length();
        char[] cs = s.toCharArray();
        for (int i = 0; i < n; i++) {
            char c = cs[i];
            if (c == '(') {
                ops.addLast(c);
            } else if (c == ')') {
                // 计算到最近一个左括号为止
                while (!ops.isEmpty()) {
                    char op = ops.peekLast();
                    if (op != '(') {
                        calc_0(nums, ops);
                    } else {
                        ops.pollLast();
                        break;
                    }
                }
            } else {
                if (isNum_0(c)) {
                    int u = 0;
                    int j = i;
                    // 将从 i 位置开始后面的连续数字整体取出，加入 nums
                    while (j < n && isNum_0(cs[j])) u = u * 10 + (int) (cs[j++] - '0');
                    nums.addLast(u);
                    i = j - 1;
                } else {
                    if (i > 0 && (cs[i - 1] == '(' || cs[i - 1] == '+' || cs[i - 1] == '-')) {
                        nums.addLast(0);
                    }
                    // 有一个新操作要入栈时，先把栈内可以算的都算了
                    while (!ops.isEmpty() && ops.peekLast() != '(') calc_0(nums, ops);
                    ops.addLast(c);
                }
            }
        }
        while (!ops.isEmpty()) calc_0(nums, ops);
        return nums.peekLast();
    }

    void calc_0(Deque<Integer> nums, Deque<Character> ops) {
        if (nums.isEmpty() || nums.size() < 2) return;
        if (ops.isEmpty()) return;
        int b = nums.pollLast(), a = nums.pollLast();
        char op = ops.pollLast();
        nums.addLast(op == '+' ? a + b : a - b);
    }

    boolean isNum_0(char c) {
        return Character.isDigit(c);
    }

    /**
     * LeetCode 227
     * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
     * <p>
     * 整数除法仅保留整数部分。
     * <p>
     * 你可以假设给定的表达式总是有效的。所有中间结果将在 [-231, 231 - 1] 的范围内。
     * <p>
     * 注意：不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
     */
    // 使用 map 维护一个运算符优先级
    // 这里的优先级划分按照「数学」进行划分即可
    Map<Character, Integer> map = new HashMap<Character, Integer>() {{
        put('-', 1);
        put('+', 1);
        put('*', 2);
        put('/', 2);
        put('%', 2);
        put('^', 3);
    }};

    public int calculate_1(String s) {
        // 将所有的空格去掉
        s = s.replaceAll(" ", "");
        char[] cs = s.toCharArray();
        int n = s.length();
        // 存放所有的数字
        Deque<Integer> nums = new ArrayDeque<>();
        // 为了防止第一个数为负数，先往 nums 加个 0
        nums.addLast(0);
        // 存放所有「非数字以外」的操作
        Deque<Character> ops = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = cs[i];
            if (c == '(') {
                ops.addLast(c);
            } else if (c == ')') {
                // 计算到最近一个左括号为止
                while (!ops.isEmpty()) {
                    if (ops.peekLast() != '(') {
                        calc_1(nums, ops);
                    } else {
                        ops.pollLast();
                        break;
                    }
                }
            } else {
                if (isNum_1(c)) {
                    int u = 0;
                    int j = i;
                    // 将从 i 位置开始后面的连续数字整体取出，加入 nums
                    while (j < n && isNum_1(cs[j])) u = u * 10 + (cs[j++] - '0');
                    nums.addLast(u);
                    i = j - 1;
                } else {
                    if (i > 0 && (cs[i - 1] == '(' || cs[i - 1] == '+' || cs[i - 1] == '-')) {
                        nums.addLast(0);
                    }
                    // 有一个新操作要入栈时，先把栈内可以算的都算了
                    // 只有满足「栈内运算符」比「当前运算符」优先级高/同等，才进行运算
                    while (!ops.isEmpty() && ops.peekLast() != '(') {
                        char prev = ops.peekLast();
                        if (map.get(prev) >= map.get(c)) {
                            calc_1(nums, ops);
                        } else {
                            break;
                        }
                    }
                    ops.addLast(c);
                }
            }
        }
        // 将剩余的计算完
        while (!ops.isEmpty()) calc_1(nums, ops);
        return nums.peekLast();
    }

    void calc_1(Deque<Integer> nums, Deque<Character> ops) {
        if (nums.isEmpty() || nums.size() < 2) return;
        if (ops.isEmpty()) return;
        int b = nums.pollLast(), a = nums.pollLast();
        char op = ops.pollLast();
        int ans = 0;
        if (op == '+') ans = a + b;
        else if (op == '-') ans = a - b;
        else if (op == '*') ans = a * b;
        else if (op == '/') ans = a / b;
        else if (op == '^') ans = (int) Math.pow(a, b);
        else if (op == '%') ans = a % b;
        nums.addLast(ans);
    }

    boolean isNum_1(char c) {
        return Character.isDigit(c);
    }

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
    private HashMap<Integer, DoubleListNode> map;
    private DoubleList cache;
    private int cap;

    public LRUCache(int capacity) {
        this.cap = capacity;
        this.cache = new DoubleList();
        this.map = new HashMap<>();
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;

        DoubleListNode tmp = map.get(key);
        cache.remove(tmp);
        cache.addLast(tmp);
        return tmp.val;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            DoubleListNode tmp = map.get(key);
            cache.remove(tmp);
        }
        if (cache.getSize() == cap) {
            DoubleListNode tmp = cache.removeFirst();
            map.remove(tmp.key);
        }
        DoubleListNode add = new DoubleListNode(key, value);
        cache.addLast(add);
        map.put(key, add);
    }
}

class DoubleList {
    private DoubleListNode head, tail;
    private int size;

    public DoubleList() {
        head = new DoubleListNode(0, 0);
        tail = new DoubleListNode(0, 0);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    public void addLast(DoubleListNode x) {
        x.prev = tail.prev;
        x.next = tail;
        tail.prev.next = x;
        tail.prev = x;
        size++;
    }

    public void remove(DoubleListNode x) {
        x.prev.next = x.next;
        x.next.prev = x.prev;
        size--;
    }

    public DoubleListNode removeFirst() {
        if (head.next == tail)
            return null;
        DoubleListNode first = head.next;
        remove(first);
        return first;
    }

    public int getSize() {
        return size;
    }
}

/**
 * LeetCode 460
 * 请你为 最不经常使用（LFU）缓存算法设计并实现数据结构。
 * <p>
 * 实现 LFUCache 类：
 * <p>
 * LFUCache(int capacity) - 用数据结构的容量capacity 初始化对象
 * int get(int key)- 如果键key 存在于缓存中，则获取键的值，否则返回 -1 。
 * void put(int key, int value)- 如果键key 已存在，则变更其值；如果键不存在，请插入键值对。当缓存达到其容量capacity 时，则应该在插入新项之前，移除最不经常使用的项。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，应该去除 最近最久未使用 的键。
 * 为了确定最不常使用的键，可以为缓存中的每个键维护一个 使用计数器 。使用计数最小的键是最久未使用的键。
 * <p>
 * 当一个键首次插入到缓存中时，它的使用计数器被设置为 1 (由于 put 操作)。对缓存中的键执行 get 或 put 操作，使用计数器的值将会递增。
 * <p>
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 */
class LFUCache {
    //key到val的映射，KV表
    HashMap<Integer, Integer> keyToVal;
    //key到freq的映射，KF表
    HashMap<Integer, Integer> keyToFreq;
    //freq到key列表的映射，我们成FK表
    HashMap<Integer, LinkedHashSet<Integer>> freqToKeys;
    //记录最小的频次
    int minFreq;
    //记录LFU缓存的最大容量
    int cap;

    public LFUCache(int capacity) {
        keyToVal = new HashMap<>();
        keyToFreq = new HashMap<>();
        freqToKeys = new HashMap<>();
        this.cap = capacity;
        this.minFreq = 0;
    }

    public int get(int key) {
        if (!keyToVal.containsKey(key))
            return -1;

        /*增加Freq的操作*/
        increaseFreq(key);

        return keyToVal.get(key);
    }

    public void put(int key, int value) {
        if (this.cap <= 0) return;

        /*若key已经存在，修改对应的val即可*/
        if (keyToVal.containsKey(key)) {
            keyToVal.put(key, value);
            increaseFreq(key);
            return;
        }
        /*若key不存在，需要插入*/
        /*容量已满的话需要淘汰一个freq最小的key*/
        if (this.cap <= keyToVal.size())
            removeMinFreqKey();

        /*插入key和value，对应的freq+1*/
        //插入KV表
        keyToVal.put(key, value);
        //插入KF表
        keyToFreq.put(key, 1);
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

/**
 * LeetCode 380
 * 实现RandomizedSet 类：
 * <p>
 * RandomizedSet() 初始化 RandomizedSet 对象
 * bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
 * bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
 * int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
 * 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1)
 */
class RandomizedSet {
    Map<Integer, Integer> valToIndex;
    List<Integer> nums;
    Random random;

    public RandomizedSet() {
        valToIndex = new HashMap<>();
        nums = new ArrayList<>();
    }

    public boolean insert(int val) {
        if (valToIndex.containsKey(val)) return false;
        valToIndex.put(val, nums.size());
        nums.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!valToIndex.containsKey(val)) return false;
        int index = valToIndex.get(val);
        int last = nums.get(nums.size() - 1);
        valToIndex.put(last, index);
        nums.set(index, last);
        nums.remove(nums.size() - 1);
        valToIndex.remove(val);
        return true;
    }

    public int getRandom() {
        int randomIndex = random.nextInt(nums.size());
        return nums.get(randomIndex);
    }
}

/**
 * LeetCode 710
 * 给定一个整数 n 和一个 无重复 黑名单整数数组blacklist。设计一种算法，从 [0, n - 1] 范围内的任意整数中选取一个未加入黑名单blacklist的整数。任何在上述范围内且不在黑名单blacklist中的整数都应该有 同等的可能性 被返回。
 * <p>
 * 优化你的算法，使它最小化调用语言 内置 随机函数的次数。
 * <p>
 * 实现Solution类:
 * <p>
 * Solution(int n, int[] blacklist)初始化整数 n 和被加入黑名单blacklist的整数
 * int pick()返回一个范围为 [0, n - 1] 且不在黑名单blacklist 中的随机整数
 * <p>
 * <p>
 * 我们将黑名单分成两部分，第一部分 X 的数都小于 N - len(B)，需要进行映射；第二部分 Y 的数都大于等于 N - len(B)，这些数不需要进行映射，因为并不会随机到它们。
 * 我们先用 Y 构造出 W，表示大于等于 N - len(B) 且在白名单中的数，X 和 W 的长度一定是相等的。随后遍历 X 和 W，构造一个映射表（HashMap）M，将 X 和 W 中的数构造一一映射。
 * 在 [0, N - len(B)) 中随机生成整数 a 时，如果 a 出现在 M 中，则将它的映射返回，否则直接返回 a。
 */
class Solution {
    int wlen;
    Random random = new Random();
    Map<Integer, Integer> map = new HashMap<>();

    public Solution(int n, int[] blacklist) {
        //白名单中的元素个数
        wlen = n - blacklist.length;
        //白名单
        Set<Integer> w = new HashSet<>();

        for (int i = wlen; i < n; i++) {
            w.add(i);
        }
        for (int x : blacklist) {
            w.remove(x);
        }
        Iterator<Integer> wi = w.iterator();
        for (int x : blacklist) {
            if (x < wlen)
                map.put(x, wi.next());
        }
    }

    public int pick() {
        int k = random.nextInt(wlen);
        return map.getOrDefault(k, k);
    }
}

/**
 * LeetCode 295
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 * <p>
 * 例如，
 * <p>
 * [2,3,4]的中位数是 3
 * <p>
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * <p>
 * 设计一个支持以下两种操作的数据结构：
 * <p>
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 */
class MedianFinder {
    //大顶堆
    private PriorityQueue<Integer> large;
    //小顶堆
    private PriorityQueue<Integer> small;

    public MedianFinder() {
        large = new PriorityQueue<>(((o1, o2) -> (o2 - o1)));
        small = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (small.size() >= large.size()) {
            small.offer(num);
            large.offer(small.poll());
        } else {
            large.offer(num);
            small.offer(large.poll());
        }
    }

    public double findMedian() {
        if (large.size() < small.size()) {
            return small.peek();
        } else if (large.size() > small.size()) {
            return large.size();
        } else return (large.size() + small.size()) / 2;
    }
}

class Calcultor {
    //Map 维护一个运算符优先级
    //这里的优先级划分按照'数字'进行划分即可
    Map<Character, Integer> map = new HashMap<Character, Integer>() {{
        put('+', 1);
        put('-', 1);
        put('*', 2);
        put('/', 2);
        put('%', 3);
        put('^', 3);
    }};

    public int calculate(String s) {
        //将所有的空格去掉
        s = s.replaceAll(" ", "");
        char[] cs = s.toCharArray();
        int n = s.length();
        //存放所有的数字
        Deque<Integer> nums = new ArrayDeque<>();
        //为了防止第一个数为负数，先往nums加个0
        nums.addLast(0);
        //存放所有非数字以外的操作
        Deque<Character> ops = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char c = cs[i];
            if (c == '(')
                ops.addLast(c);
            else if (c == ')') {
                while (!ops.isEmpty()) {
                    if (ops.peekLast() != '(') {
                        calc(nums, ops);
                    } else {
                        ops.pollLast();
                        break;
                    }
                }
            } else {
                if (isNumber(c)) {
                    int num = 0;
                    int j = i;
                    while (j < n && isNumber(cs[j])) {
                        num = num * 10 + (cs[j] - '0');
                        j++;
                    }
                    nums.addLast(num);
                    i = j - 1;
                } else {
                    if (i > 0 && cs[i - 1] == '(' || cs[i - 1] == '+' || cs[i - 1] == '-')
                        nums.addLast(0);
                    // 有一个新操作要入栈时，先把栈内可以算的都算了
                    // 只有满足「栈内运算符」比「当前运算符」优先级高/同等，才进行运算
                    while (!ops.isEmpty() && ops.peekLast() != '(') {
                        char prev = ops.peekLast();
                        if (map.get(prev) >= map.get(c)) {
                            calc(nums, ops);
                        } else break;
                    }
                    ops.addLast(c);
                }
            }
        }
        // 将剩余的计算完
        while (!ops.isEmpty())
            calc(nums, ops);
        return nums.peekLast();
    }

    private void calc(Deque<Integer> nums, Deque<Character> ops) {
        if (nums.isEmpty() || nums.size() < 2) return;
        if (ops.isEmpty()) return;
        int b = nums.pollLast(), a = nums.pollLast();
        char op = ops.pollLast();
        int res = 0;
        if (op == '+') res = a + b;
        else if (op == '-') res = a - b;
        else if (op == '*') res = a * b;
        else if (op == '/') res = a / b;
        else if (op == '^') res = (int) Math.pow(a, b);
        else if (op == '%') res = a % b;
        nums.addLast(res);
    }

    private boolean isNumber(char c) {
        return Character.isDigit(c);
    }
}

/**
 * LeetCode 855
 */
class ExamRoom {
    int N;
    TreeSet<Integer> students;

    public ExamRoom(int n) {
        this.N = n;
        students = new TreeSet<>();
    }

    public int seat() {
        int student = 0;
        if (students.size() > 0) {
            int dist = students.first();
            Integer prev = null;
            for (Integer s : students) {
                if (prev != null) {
                    int d = (s - prev) / 2;
                    if (d > dist) {
                        dist = d;
                        student = prev + d;
                    }
                }
                prev = s;
            }
            if (N - 1 - students.last() > dist) {
                student = N - 1;
            }
        }
        students.add(student);
        return student;
    }
    public void leave(int p) {
        students.remove(p);
    }
}

