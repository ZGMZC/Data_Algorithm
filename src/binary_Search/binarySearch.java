package binary_Search;

import java.util.Arrays;

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

    /**
     * 二叉搜索的 泛化套路
     * @param nums
     * @param target
     */
    public void binary_search_T(int[] nums,int target){
        if(nums.length==0) return;
        // 自变量x的最小值
        int left=Integer.MIN_VALUE;
        //自变量x的最大值
        int right=Integer.MAX_VALUE;

        while (left<=right){
            int mid=left+((right-left)>>1);
            if(f(mid)<target){
                /*怎么让f（x） 增大*/
                //TODO
            }else if(f(mid)==target){
                /*题目 求左边界还是右边界*/
                //TODO
            }else if(f(mid)>target){
                /*怎么让f（x） 减小*/
                //TODO
            }
        }
        return ;
    }
    // 函数f是关于自变量x的单调函数
    private int f(int x){
        //TODO
        return x;
    }

    /**
     * LeetCode 875
     * 珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有piles[i]根香蕉。警卫已经离开了，将在 h 小时后回来。
     *
     * 珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
     *
     * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
     *
     * 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
     * @param piles
     * @param h
     * @return
     */
    /*确定x,f(x),target
    * 题目要求什么，就把什么设为自变量x，即 速度为x，f（x）定义为吃完香蕉的时间
    * */
    public int minEatingSpeed(int[] piles, int h){
        //最小速度为1
        int left=1;
        //最大速度为piles数组元素中的最大值
        int right=0;
        for(int pile:piles){
            if(right<pile)
                right=pile;
        }
        while (left<=right){
            int mid=left+((right-left)>>1);
            /*求最小速度，则左边界*/
            if(eat(piles,mid)>h){   //吃完香蕉的时间大于规定时间，需要增加速度
                left=mid+1;
            }else if(eat(piles,mid)==h){
                right=mid-1;
            }else if(eat(piles,mid)<h){
                right=mid-1;
            }
        }
        return left;
    }
    private int eat(int[] piles,int x){
        int hours=0;
        for(int i=0;i<piles.length;i++){
            hours+=piles[i]/x;
            if(piles[i]%x>0)
                hours++;
        }
        return hours;
    }

    /**
     * LeetCode 1011
     * 传送带上的包裹必须在 days 天内从一个港口运送到另一个港口。
     *
     * 传送带上的第 i个包裹的重量为weights[i]。每一天，我们都会按给出重量（weights）的顺序往传送带上装载包裹。我们装载的重量不会超过船的最大运载重量。
     *
     * 返回能在 days 天内将传送带上的所有包裹送达的船的最低运载能力。
     * @param weights
     * @param days
     * @return
     */
    /*设运载能力为x，f（x）运载完货物所需要的天数为*/
    public int shipWithinDays(int[] weights, int days){
        //最低运载能力
        int left=0;
        for(int weight:weights){
            if(weight>left)
                left=weight;
        }
        //最高运载能力
        int right= Arrays.stream(weights).sum();
        while (left<=right){
            int mid=left+((right-left)>>1);
            if(days(weights,mid)>days){ //若需要的天数比要求天数多，需要增加运载能力
                left=mid+1;
            }else if(days(weights,mid)==days){
                right=mid-1;
            }else if(days(weights,mid)<days){
                right=mid-1;
            }
        }
        return left;
    }
    private int days(int[] weights, int x){
        int days=0;
        for(int i=0;i<weights.length;){
            int cap=x;
            while (i<weights.length){
                if(cap<weights[i]) break;
                else cap-=weights[i];
                i++;
            }
            days++;
        }
        return days;
    }

    /**
     * LeetCode 410
     * 给定一个非负整数数组 nums 和一个整数 m ，你需要将这个数组分成 m 个非空的连续子数组。
     * 设计一个算法使得这 m 个子数组各自和的最大值最小。
     * 该题目与LeetCode 1011 相似，不过是换了一种说法，题目的设计。
     * @param nums
     * @param m
     * @return
     */
    public int splitArray(int[] nums, int m){
        int left=0;
        for(int num:nums){
            if(num>left)
                left=num;
        }
        int right= Arrays.stream(nums).sum();
        while (left<=right){
            int mid=left+((right-left)>>1);
            if(sum(nums,mid)>m){
                left=mid+1;
            }else if(sum(nums,mid)==m){
                right=mid-1;
            }else if(sum(nums,mid)<m){
                right=mid-1;
            }
        }
        return left;
    }
    private int sum(int[] nums,int x){
        int res=0;
        for(int i=0;i<nums.length;){
            int tmp=x;
            while(i<nums.length){
                if(nums[i]<tmp) break;
                else tmp-=nums[i];
                i++;
            }
            res++;
        }
        return res;
    }
}
