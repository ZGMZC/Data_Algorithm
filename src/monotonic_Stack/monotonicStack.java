package monotonic_Stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class monotonicStack {

    /**
     * 单调栈 模板
     * 输入⼀个数组 nums，请你返回一个等长的结果数组，结果数组中对应索引存储着下
     * 一个更大元素，如果没有更大的元素，就存 -1
     * @param nums
     */
    public int[] Frame(int[] nums){
        int n=nums.length;
        //存放答案的数组
        int[] res=new int[n];
        Deque<Integer> sta=new ArrayDeque<>();
        // 倒着往栈里放
        for (int i = n - 1; i >= 0; i--) {
            // 判定个子高矮
            while (!sta.isEmpty() && sta.peek() <= nums[i]) {
                // 矮个起开，反正也被挡着了。。。
                sta.pop();
            }
            // nums[i] 身后的更大元素
            res[i] = sta.isEmpty() ? -1 : sta.peek();
            sta.push(nums[i]);
        }
        return res;
    }

    /**
     * LeetCode 496
     * nums1中数字x的 下一个更大元素 是指x在nums2 中对应位置 右侧 的 第一个 比x大的元素。
     *
     * 给你两个 没有重复元素 的数组nums1 和nums2 ，下标从 0 开始计数，其中nums1是nums2的子集。
     *
     * 对于每个 0 <= i < nums1.length ，找出满足 nums1[i] == nums2[j] 的下标 j ，并且在 nums2 确定 nums2[j] 的 下一个更大元素 。如果不存在下一个更大元素，那么本次查询的答案是 -1 。
     *
     * 返回一个长度为nums1.length 的数组 ans 作为答案，满足 ans[i] 是如上所述的 下一个更大元素 。
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2){
        int[] greater=find(nums2);
        HashMap<Integer,Integer> greaterMap=new HashMap<>();
        for(int i=0;i<nums2.length;i++){
            greaterMap.put(nums2[i],greater[i]);
        }
        int[] res=new int[nums1.length];
        for(int i=0;i<nums1.length;i++){
            res[i]=greaterMap.get(nums1[i]);
        }
        return res;
    }
    private int[] find(int[] nums){
        int n=nums.length;
        int[] res=new int[n];
        Deque<Integer> sta=new ArrayDeque<>();
        for(int i=n-1;i>=0;i--){
            while (!sta.isEmpty()&& sta.peek()<=nums[i]){
                sta.pop();
            }
            res[i]= (sta.isEmpty()?-1:sta.peek());
            sta.push(nums[i]);
        }
        return res;
    }

    /**
     * LeetCode 739
     *给定一个整数数组temperatures，表示每天的温度，返回一个数组answer，其中answer[i]是指在第 i 天之后，
     * 才会有更高的温度。如果气温在这之后都不会升高，请在该位置用0来代替。
     * @param temperatures
     * @return
     */
    public int[] dailyTemperatures(int[] temperatures){
        int n=temperatures.length;
        //存放元素的索引，而不是元素
        int[] res=new int[n];
        Deque<Integer> sta=new ArrayDeque<>();
        for (int i=n-1;i>=0;i--){
            while (!sta.isEmpty()&& temperatures[sta.peek()] <= temperatures[i])
                sta.pop();
            res[i]=(sta.isEmpty()?0:(sta.peek()-i));
            sta.push(i);
        }
        return res;
    }

    /**
     * LeetCode 503
     * 给定一个循环数组nums（nums[nums.length - 1]的下一个元素是nums[0]），返回nums中每个元素的 下一个更大元素 。
     *
     * 数字 x的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums){
        int n=nums.length;
        int[] res=new int[n];
        Deque<Integer> sta=new ArrayDeque<>();
        for(int i=2*n-1;i>=0;i--){
            while (!sta.isEmpty()&&sta.peek()<=nums[i%n])
                sta.pop();
            res[i%n]= (sta.isEmpty()?-1:sta.peek());
            sta.push(nums[i%n]);
        }
        return res;
    }
}
