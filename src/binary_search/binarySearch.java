package binary_search;

/**
 * 二分查找 模板
 */
public class binarySearch {
    /**
     * 普通的二分查找 [1,2,5,5,5,7,8] 返回索引3
     * @param nums
     * @param target
     * @return
     */
    public int binary_search(int[] nums,int target){
        int left=0,right=nums.length-1;
        while(left<=right){
            int mid=left+((right-left)>>1);
            if(nums[mid]<target)
                left=mid+1;
            else if(nums[mid]==target)
                return mid;
            else if(nums[mid]>target)
                right=mid-1;
        }
        return -1;
    }

    /**
     * 二分查找  目标的最左边界 [1,2,5,5,5,7,8] 返回索引2
     * @param nums
     * @param target
     * @return
     */
    public int left_bound(int nums[],int target){
        int left=0,right=nums.length-1;
        while (left<=right){
            int mid=left+((right-left)>>1);
            if(nums[mid]<target)
                left=mid+1;
            else if(nums[mid]==target)
                right=mid-1;
            else if(nums[mid]>target)
                right=mid-1;
        }
        if(left>=nums.length||nums[left]!=target) return -1;
        return left;
    }

    /**
     * 二分查找  目标的最右边界 [1,2,5,5,5,7,8] 返回索引4
     * @param nums
     * @param target
     * @return
     */
    public int right_bound(int[] nums,int target){
        int left=0,right= nums.length-1;
        while(left<=right){
            int mid=left+((right-left)>>1);
            if(nums[mid]<target)
                left=mid+1;
            else if(nums[mid]==target)
                left=mid+1;
            else if(nums[mid]>target)
                right=mid-1;
        }
        if(right<0||nums[right]!=target) return -1;
        return right;
    }

}
