package nSum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ZGMZC
 * @date 2022/7/11 22:21
 */
public class nSum {
    /*两数之和*/
    public List<List<Integer>> twoSum(int[] nums,int start,int target){
        List<List<Integer>> res=new ArrayList<>();
        int n=nums.length;
        Arrays.sort(nums);
        int left=start,right=n-1;
        while (left<right){
            int a=nums[left],b=nums[right];
            if(a+b==target){
                res.add(new ArrayList<>(Arrays.asList(a,b)));
                while (a<b&&nums[left]==a) left++;
                while (a<b&&nums[right]==b) right--;
            }else if(a+b<target){
                while (a<b&&nums[left]==a) left++;
            }else {
                while (a<b&&nums[right]==b) right--;
            }
        }
        return res;
    }
    /**
     * LeetCode 15
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums,int target) {
        int n=nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res=new ArrayList<>();
        for(int i=0;i<n;i++){
            if(nums[i]>0) break;
            if(i>0&&nums[i]==nums[i-1]) continue;
            int temp=target-nums[i];
            int left=i+1,right=n-1;
            while (left<right){
                int a=nums[left],b=nums[right];
                if(a+b==temp){
                    res.add(new ArrayList<>(Arrays.asList(nums[i],nums[left],nums[right])));
                    while (left<right&&nums[left]==a) left++;
                    while (left<right&&nums[right]==b) right--;
                }else if(a+b<temp){
                    while (left<right&&nums[left]==a) left++;
                }else {
                    while (left<right&&nums[right]==b) right--;
                }
            }
        }
        return res;
    }

    /**
     * LeetCode 18
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return nSum(nums,4,0,target);
    }
    public List<List<Integer>> nSum(int[] nums,int n,int start,int target){
        List<List<Integer>> res=new ArrayList<>();
        int length=nums.length;
        if(n<2||length<n) return res;
        if(n==2){
            int left=start,right=length-1;
            while (left<right){
                int a=nums[left],b=nums[right];
                if((long)a+b==target){
                    res.add(new ArrayList<>(Arrays.asList(a,b)));
                    while (left<right&&nums[left]==a) left++;
                    while (left<right&&nums[right]==b) right--;
                }else if((long)a+b<target){
                    while (left<right&&nums[left]==a) left++;
                }else {
                    while (left<right&&nums[right]==b) right--;
                }
            }
        }else {
            for(int i=start;i<length;i++){
                List<List<Integer>> temp=nSum(nums,n-1,i+1,target-nums[i]);
                for (List<Integer> list:temp){
                    list.add(nums[i]);
                    res.add(list);
                }
                while(i<length-1&& nums[i]==nums[i+1]) i++;
            }
        }
        return res;
    }
}
