package summarize_Sort;

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
}
