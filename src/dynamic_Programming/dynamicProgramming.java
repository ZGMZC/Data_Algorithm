package dynamic_Programming;

import java.util.Arrays;
import java.util.Comparator;
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

    /**
     * LeetCode 300
     * @param nums
     * @return
     */
    public int lengthOfLIS(int[] nums) {
        //dp[i] 表示以 nums[i] 这个数结尾的最长递增子序列的长度
        int[] dp=new int[nums.length];
        Arrays.fill(dp,1);
        for(int i=0;i<nums.length;i++){
            for(int j=0;j<i;j++){
                if(nums[i]>nums[j])
                dp[i]=Math.max(dp[i],dp[j]+1);
            }
        }
        int res=0;
        for(int i=0;i<nums.length;i++)
            res=Math.max(res,dp[i]);
        return res;
    }

    /**
     * LeetCode 354
     * @param envelopes
     * @return
     */
    public int maxEnvelopes(int[][] envelopes) {
        int n=envelopes.length;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]==o2[0]? o2[1]-o1[1]:o1[0]-o2[0];
            }
        });
        int[] height=new int[n];
        for (int i=0;i<n;i++){
            height[i]=envelopes[i][1];
        }
        return lengthOfLIS_1(height);
    }
    private int lengthOfLIS_1(int[] nums){
        int[] top=new int[nums.length];
        int piles=0;
        for(int i=0;i<nums.length;i++){
            int poker=nums[i];
            int left=0,right=piles;
            while (left<right){
                int mid=(left+right)/2;
                if(top[mid]>poker) right=mid;
                else if(top[mid]<poker) left=mid+1;
                else right=mid;
            }
            if(right==piles) piles++;
            top[left]=poker;
        }
        return piles;
    }

    /**
     * LeetCode 53
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int n=nums.length;
        int[] dp=new int[n];
        int res=nums[0];
        dp[0]=nums[0];
        for(int i=1;i<n;i++){
            dp[i]=Math.max(nums[i],dp[i-1]+nums[i]);
            res=Math.max(res,dp[i]);
        }
        return res;
    }

    /**
     * LeetCode 72
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance(String word1, String word2) {
        int n=word1.length(),m=word2.length();
        // 定义：s1[0..i] 和 s2[0..j] 的最⼩编辑距离是 dp[i+1][j+1]
        int[][] dp=new int[n+1][m+1];
        for (int i = 1; i <= m; i++)
            dp[i][0] = i;
        for (int j = 1; j <= n; j++)
            dp[0][j] = j;
        // 自底向上求解
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = min(
                            dp[i - 1][j] + 1,
                            dp[i][j - 1] + 1,
                            dp[i - 1][j - 1] + 1
                    );
                }
            }
        }
        // 储存着整个 s1 和 s2 的最小编辑距离
        return dp[m][n];
    }
}
