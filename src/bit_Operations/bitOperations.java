package bit_Operations;

/**
 * @Author ZGMZC
 * @Date 2022/7/22 23:01
 */
public class bitOperations {
    /**
     * LeetCode 191
     * @param n
     * @return
     */
    public int hammingWeight(int n){
        int res=0;
        while (n!=0){
            n=n&(n-1);
            res++;
        }
        return res;
    }

    /**
     * LeetCode 231
     * @param n
     * @return
     */
    public boolean isPowerOfTwo(int n){
        if(n<=0) return false;
        return (n&(n-1))==0;
    }

    /**
     * LeeetCode 136
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int res=0;
        for(int a:nums){
            res^=a;
        }
        return res;
    }

    /**
     * LeetCode 268
     * @param nums
     * @return
     */
    public int missingNumber(int[] nums){
        int n=nums.length;
        long expect=(0+n)*(n+1)/2;
        long sum=0;
        for(int x:nums){
            sum+=x;
        }
        return (int)(expect-sum);
    }
}
