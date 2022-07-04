package dynamic_Programming;

import java.util.Arrays;
import java.util.Map;

/**
 * @author ZGMZC
 * @date 2022/7/4 22:41
 */
public class dynamicProgramming {
    /**
     * LeetCode 509
     * @param n
     * @return
     */
    public int fib(int n) {
        if(n==0) return 0;
        int[] dp=new int[n+1];
        dp[0]=0;
        dp[1]=1;
        for(int i=2;i<=n;i++){
            dp[i]=dp[i-1]+dp[i-2];
        }
        return dp[n];
    }

    /**
     * LeetCode 322
     * @param coins
     * @param amount
     * @return
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp=new int[amount+1];
        Arrays.fill(dp,amount+1);
        //base case
        dp[0]=0;
        //外层for循环在遍历所有状态的取值
        for(int i=0;i<dp.length;i++){
            //内层for循环在求所有选择的最小值
            for(int coin:coins){
                if(i-coin<0) continue;
                dp[i]=Math.min(dp[i],dp[i-coin]+1);
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }

    /**
     * LeetCode 931
     * @param matrix
     * @return
     */
    public int minFallingPathSum(int[][] matrix){
        int n=matrix.length;
        int m=matrix[0].length;
        int[][] dp=new int[n][m];
        int res=Integer.MAX_VALUE;
        for (int j=0;j<m;j++)   dp[0][j]=matrix[0][j];
        for(int i=1;i<n;i++){
            for(int j=0;j<m;j++){
                int dp_j_1=Integer.MAX_VALUE;
                int dp_j=dp[i-1][j];
                int dp_j_2=Integer.MAX_VALUE;
                //如果坐标符合条件，则将其修改为相邻值，不符合条件则作为最大值，即计算时抛出
                if(j-1>=0){
                    dp_j_1=dp[i-1][j-1];
                }
                if(j+1<m){
                    dp_j_2=dp[i-1][j+1];
                }
                dp[i][j]=min(dp_j_1,dp_j,dp_j_2)+matrix[i][j];
            }
        }
        for(int j=0;j<m;j++)
            res=Math.min(dp[n-1][j],res);
        return res;
    }
    private int min(int a,int b,int c){
        return Math.min(a,Math.min(b,c));
    }
}
