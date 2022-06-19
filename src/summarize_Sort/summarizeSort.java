package summarize_Sort;

import java.util.LinkedList;
import java.util.List;

public class summarizeSort {
    /**
     * 归并排序模板
     */
    //用于辅助合并有序数组
    private int[] temp;

    public void sort(int[] nums, int lo, int hi) {
        temp = new int[nums.length];
        if (lo == hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(nums, lo, mid);
        sort(nums, mid + 1, hi);
        merge(nums, lo, mid, hi);
    }

    private void merge(int[] nums, int lo, int mid, int hi) {
        // 先把 nums[lo..hi] 复制到辅助数组中
        // 以便合并后的结果能够直接存入nums
        for (int i = lo; i <= hi; i++)
            temp[i] = nums[i];
        int i = lo, j = mid + 1;
        for (int p = lo; p <= hi; p++) {
            if (i == mid + 1) {
                //左半边数组已全部被合并
                nums[p] = temp[j++];
            } else if (j == hi + 1) {
                //右半边数组已全部被合并
                nums[p] = temp[i++];
            } else if (temp[i] > temp[j]) {
                nums[p] = temp[j++];
            } else {
                nums[p] = temp[i++];
            }
        }
    }

    /**
     * LeetCode 315
     * 给你一个整数数组 nums ，按要求返回一个新数组counts 。数组 counts 有该性质： counts[i] 的值是 nums[i] 右侧小于nums[i] 的元素的数量。
     *
     * @param nums
     * @return
     */
    // 归并排序所用的辅助数组
    private Pair[] temp_0;
    // 记录每个元素后面比自己小的元素个数
    private int[] count;

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        count = new int[n];
        temp_0 = new Pair[n];
        Pair[] arr = new Pair[n];
        // 记录元素原始的索引位置，以便在 count 数组中更新结果
        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(nums[i], i);
        }
        // 执行归并排序，本题结果被记录在 count 数组中
        sort(arr, 0, n - 1);
        List<Integer> res = new LinkedList<>();
        for (int c : count) res.add(c);
        return res;
    }

    private void sort(Pair[] arr, int lo, int hi) {
        if (lo == hi) return;
        int mid = lo + (hi - lo) / 2;
        sort(arr, lo, mid);
        sort(arr, mid + 1, hi);
        merge(arr, lo, mid, hi);
    }

    private void merge(Pair[] arr, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            temp_0[i] = arr[i];
        }
        int i = lo, j = mid + 1;
        for (int p = lo; p <= hi; p++) {
            if (i == mid + 1) {
                arr[p] = temp_0[j++];
            } else if (j == hi + 1) {
                arr[p] = temp_0[i++];
                // 更新 count 数组
                count[arr[p].id] += j - mid - 1;
            } else if (temp_0[i].val > temp_0[j].val) {
                arr[p] = temp_0[j++];
            } else {
                arr[p] = temp_0[i++];
                // 更新 count 数组
                count[arr[p].id] += j - mid - 1;
            }

        }
    }

    class Pair {
        int val, id;

        Pair(int val, int id) {
            // 记录数组的元素值
            this.val = val;
            // 记录元素在数组中的原始索引
            this.id = id;
        }
    }

    /**
     * LeetCode 493
     * 给定一个数组nums，如果i < j且nums[i] > 2*nums[j]我们就将(i, j)称作一个重要翻转对。
     * <p>
     * 你需要返回给定数组中的重要翻转对的数量。
     *
     * @param nums
     * @return
     */
    // 记录「翻转对」的个数
    int count_0 = 0;

    public int reversePairs(int[] nums) {
        // 执行归并排序
        sort(nums);
        return count_0;
    }

    private int[] temp_1;

    public void sort(int[] nums) {
        temp_1 = new int[nums.length];
        sort_1(nums, 0, nums.length - 1);
    }

    // 归并排序
    private void sort_1(int[] nums, int lo, int hi) {
        if (lo == hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort_1(nums, lo, mid);
        sort_1(nums, mid + 1, hi);
        merge_1(nums, lo, mid, hi);
    }

    private void merge_1(int[] nums, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            temp_1[i] = nums[i];
        }
        // 进行效率优化，维护左闭右开区间 [mid+1, end) 中的元素乘 2 小于 nums[i]
        // 为什么 end 是开区间？因为这样的话可以保证初始区间 [mid+1, mid+1) 是一个空区间
        int end = mid + 1;
        for (int i = lo; i <= mid; i++) {
            // nums 中的元素可能较大，乘 2 可能溢出，所以转化成 long
            while (end <= hi && (long) nums[i] > (long) nums[end] * 2) {
                end++;
            }
            count_0 += end - (mid + 1);
        }
        // 数组双指针技巧，合并两个有序数组
        int i = lo, j = mid + 1;
        for (int p = lo; p <= hi; p++) {
            if (i == mid + 1) {
                nums[p] = temp_1[j++];
            } else if (j == hi + 1) {
                nums[p] = temp_1[i++];
            } else if (temp_1[i] > temp_1[j]) {
                nums[p] = temp_1[j++];
            } else {
                nums[p] = temp_1[i++];
            }
        }
    }

    /**
     * LeetCode 327
     * 给你一个整数数组nums 以及两个整数lower 和 upper 。求数组中，值位于范围 [lower, upper] （包含 lower 和 upper）之内的 区间和的个数 。
     *
     * 区间和S(i, j)表示在nums中，位置从i到j的元素之和，包含i和j(i ≤ j)。
     *
     * @param nums
     * @param lower
     * @param upper
     * @return
     */
    private int lower, upper;
    public int countRangeSum(int[] nums, int lower, int upper) {
        this.lower = lower;
        this.upper = upper;
        // 构建前缀和数组，注意 int 可能溢出，⽤ long 存储
        long[] preSum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            preSum[i + 1] = (long)nums[i] + preSum[i];
        }
        // 对前缀和数组进⾏归并排序
        sort_2(preSum);
        return count_2;
    }
    private long[] temp_2;
    public void sort_2(long[] nums) {
        temp_2 = new long[nums.length];
        sort_2(nums, 0, nums.length - 1);
    }
    private void sort_2(long[] nums, int lo, int hi) {
        if (lo == hi) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort_2(nums, lo, mid);
        sort_2(nums, mid + 1, hi);
        merge_2(nums, lo, mid, hi);
    }
    private int count_2 = 0;
    private void merge_2(long[] nums, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; i++) {
            temp_2[i] = nums[i];
        }
        // 维护左闭右开区间 [start, end) 中的元素和 nums[i] 的差在 [lower, upper] 中
        int start = mid + 1, end = mid + 1;
        for (int i = lo; i <= mid; i++) {
            // 如果 nums[i] 对应的区间是 [start, end)，
            // 那么 nums[i+1] 对应的区间⼀定会整体右移，类似滑动窗⼝
            while (start <= hi && nums[start] - nums[i] < lower) {
                start++;
            }
            while (end <= hi && nums[end] - nums[i] <= upper) {
                end++;
            }
            count_2 += end - start;
        }
        // 数组双指针技巧，合并两个有序数组
        int i = lo, j = mid + 1;
        for (int p = lo; p <= hi; p++) {
            if (i == mid + 1) {
                nums[p] = temp[j++];
            } else if (j == hi + 1) {
                nums[p] = temp[i++];
            } else if (temp[i] > temp[j]) {
                nums[p] = temp[j++];
            } else {
                nums[p] = temp[i++];
            }
        }
    }
}
