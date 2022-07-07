package Backpack;

import java.util.Arrays;

/**
 * @author ZGMZC
 * @date 2022/7/7 20:33
 */
public class Backpack {
    //dp[i][w] 的定义如下：对于前 i 个物品，当前背包的容量为 w，这种情况下可以装的最大价值是 dp[i][w]
    public int knapsack(int N,int W,int[] wt,int[] val){
        int[][] dp=new int[N+1][W+1];
        for(int i=1;i<=N;i++){
            for(int j=1;j<=W;j++){
                if(j-wt[i-1]<0){
                    //这种情况下只能选择不装入背包
                    dp[i][j]=dp[i-1][j];
                }else{
                    dp[i][j]=Math.max(dp[i-1][j-wt[i-1]]+val[i-1],dp[i-1][j]);
                }
            }
        }
        return dp[N][W];
    }

    /**
     * LeetCode 518
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        int n=coins.length;
        int[][] dp=new int[n+1][amount+1];
        for(int i=0;i<=n;i++){
            dp[i][0]=1;
        }
        for(int i=1;i<=n;i++){
            for (int j=1;j<=amount;j++){
                if(j-coins[i-1]>=0){
                    dp[i][j]=dp[i-1][j]+dp[i][j-coins[i-1]];
                }else {
                    dp[i][j]=dp[i-1][j];
                }
            }
        }
        return dp[n][amount];
    }

    /**
     * LeetCode 416
     * @param nums
     * @return
     */
    public boolean canPartition(int[] nums) {
        int sum= Arrays.stream(nums).sum();
        if(sum%2!=0) return false;
        int n=nums.length;
        sum=sum/2;
        boolean[][] dp=new boolean[n+1][sum+1];
        for(int i=0;i<=n;i++)
            dp[i][0]=true;
        for(int i=1;i<=n;i++){
            for (int j=1;j<=sum;j++){
                if(j-nums[i-1]<0)
                    dp[i][j]=dp[i-1][j];
                else dp[i][j]=dp[i-1][j]||dp[i - 1][j - nums[i - 1]];
            }
        }
        return dp[n][sum];
    }
}
