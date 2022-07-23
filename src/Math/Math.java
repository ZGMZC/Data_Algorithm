package Math;

import common_Utils.ListNode;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author ZGMZC
 * @date 2022/7/11 19:32
 */
public class Math {
    /**
     * LeetCode 204
     * @param n
     * @return
     */
    public int countPrimes(int n) {
        boolean[] isPrime=new boolean[n];
        Arrays.fill(isPrime,true);
        for(int i=2;i * i < n;i++){
            if(isPrime[i]){
                for(int j=i*i;j<n;j+=i){
                    isPrime[j]=false;
                }
            }
        }
        int count=0;
        for (int i=2;i * i < n;i++)
            if (isPrime[i]) count++;
        return count;
    }

    /**
     * LeetCode 172
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        int res=0;
        int divisor=5;
        while (divisor<=n){
            res+= n/divisor;
            divisor*=5;
        }
        return res;
    }

    /**
     * LeetCode 793
     * @param k
     * @return
     */
    public int preimageSizeFZF(int k) {
        return (int)(right_bound(k)-left_bound(k)+1);
    }
    public long trailingZeroes(long n){
        long res=0;
        long divisor=5;
        while (divisor<=n){
            res+=n/divisor;
            divisor*=5;
        }
        return res;
    }
    private long left_bound(int k){
        long lo=0,hi=Long.MAX_VALUE;
        while (lo<=hi){
            long mid=lo+(hi-lo)/2;
            if(trailingZeroes(mid)<k){
                lo=mid+1;
            }else if(trailingZeroes(mid)>k){
                hi=mid-1;
            }else hi=mid-1;
        }
        return lo;
    }
    private long right_bound(int k){
        long lo=0,hi=Long.MAX_VALUE;
        while (lo<=hi){
            long mid=lo+(hi-lo)/2;
            if(trailingZeroes(mid)<k){
                lo=mid+1;
            }else if (trailingZeroes(mid)>k){
                hi=mid-1;
            }else lo=mid+1;
        }
        return hi;
    }

    /**
     * LeetCode 382
     * @return
     */
    public int getRandom(ListNode head) {
        Random r=new Random();
        int i=0,res=0;
        ListNode p=head;
        while (p!=null){
            i++;
            if(0==r.nextInt(i))
                res=p.val;
            p=p.next;
        }
        return res;
    }

    /**
     * LeetCode 398
     * @param target
     * @return
     */
    int[] nums;
    Random rand;
    public int pick(int target) {
        int count=0,res=-1;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=target)
                continue;
            count++;
            if(0==rand.nextInt(count))
                res=i;
        }
        return res;
    }

    /**
     * NowCoder 吃葡萄
     * 有三种葡萄，每种分别有 a, b, c 颗，现在有三个人，第一个人只吃第一种和第二种葡萄，第二个人只吃
     * 第二种和第三种葡萄，第三个人只吃第一种和第三种葡萄。
     * 现在给你输入 a, b, c 三个值，请你适当安排，让三个人吃完所有的葡萄，算法返回吃的最多的人最少要
     * 吃多少颗葡萄。
     * @param a
     * @param b
     * @param c
     * @return
     */
    public long solution(long a, long b, long c){
        long[] nums=new long[]{a,b,c};
        Arrays.sort(nums);
        long sum=a+b+c;
        if(nums[0]+nums[1]>nums[2]){
            return (sum+2)/3;
        }
        if(2*(nums[0]+nums[1])<nums[2]){
            return (nums[2]+1)/2;
        }
        return (sum+2)/3;
    }

    /**
     * LeetCode 645
     * @param nums
     * @return
     */
    public int[] findErrorNums(int[] nums) {
        int n=nums.length;
        int dup=-1;
        for(int i=0;i<n;i++){
            int index= java.lang.Math.abs(nums[i])-1;
            if(nums[index]<0)
                dup= java.lang.Math.abs(nums[i]);
            else nums[index]*=-1;
        }
        int missing = -1;
        for (int i = 0; i < n; i++)
            if (nums[i] > 0)
                // 将索引转换成元素
                missing = i + 1;
        return new int[]{dup, missing};
    }

    /**
     * LeetCode 372
     * @param a
     * @param b
     * @return
     */
    static final int MOD=1337;
    public int superPow(int a, int[] b) {
        int ans = 1;
        for (int i = b.length - 1; i >= 0; --i) {
            ans = (int) ((long) ans * pow(a, b[i]) % MOD);
            a = pow(a, 10);
        }
        return ans;
    }
    public int pow(int x, int n) {
        int res = 1;
        while (n != 0) {
            if (n % 2 != 0) {
                res = (int) ((long) res * x % MOD);
            }
            x = (int) ((long) x * x % MOD);
            n /= 2;
        }
        return res;
    }

    /**
     * LeetCode 319
     * @param n
     * @return
     */
    public int bulbSwitch(int n) {
        return (int)java.lang.Math.sqrt(n);
    }
}
