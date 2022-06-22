package quick_Sort;

public class quickSort {
    /*快速排序*/
    public void sort(int[] nums,int lo,int hi){
        if(lo>=hi) return;
        // 对 nums[lo..hi] 进行切分
        // 使得 nums[lo..p-1] <= nums[p] < nums[p+1..hi]
        int p=partition(nums,lo,hi);
        sort(nums,lo,p-1);
        sort(nums,p+1,hi);
    }
    private int partition(int[] nums,int lo,int hi){
        int pivot = nums[lo];
        // 关于区间的边界控制需格外小心，稍有不慎就会出错
        // 我这里把 i, j 定义为闭区间，同时定义：
        // [lo, i] < pivot；[j, hi] > pivot
        // 之后都要正确维护这个边界区间的定义
        int i = lo, j = hi;
        while (i < j) {
            while (i < hi && nums[i] <= pivot) {
                i++;
                // 此 while 结束时恰好 nums[i] > pivot
            }
            while (j > lo && nums[j] >= pivot) {
                j--;
                // 此 while 结束时恰好 nums[j] < pivot
            }
            if (i >= j) break;
            swap(nums, i, j);
        }
        // 将 pivot 放到合适的位置，即 pivot 左边元素较小，右边元素较大
        swap(nums, j, lo);
        return j;
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    /**
     * LeetCode 215
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     *
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        int lo=0,hi=nums.length-1;
        k=hi+1-k; //排名第k的元素
        while (lo<=hi){
            int p = partition(nums, lo, hi);
            if (p < k) {
                // 第 k 大的元素在 nums[p+1..hi] 中
                lo = p + 1;
            } else if (p > k) {
                // 第 k 大的元素在 nums[lo..p-1] 中
                hi = p - 1;
            } else {
                // 找到第 k 大元素
                return nums[p];
            }
        }
        return -1;
    }
}
