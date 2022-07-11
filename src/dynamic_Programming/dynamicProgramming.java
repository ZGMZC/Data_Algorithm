package dynamic_Programming;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

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
        // 定义：s1[0..i] 和 s2[0..j] 的最小编辑距离是 dp[i+1][j+1]
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

    /**
     * LeetCode 1143
     * @param text1
     * @param text2
     * @return
     */
    public int longestCommonSubsequence(String text1, String text2) {
        int n=text1.length(),m=text2.length();
        int[][] dp=new int[n+1][m+1];
        for(int i=1;i<=n;i++){
            for (int j=1;j<=m;j++){
                if(text1.charAt(i-1)==text2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                }else {
                    dp[i][j]=Math.max(dp[i][j-1],dp[i-1][j]);
                }
            }
        }
        return dp[n][m];
    }

    /**
     * LeetCode 583
     * @param word1
     * @param word2
     * @return
     */
    public int minDistance1(String word1, String word2){
        int n=word1.length(),m=word2.length();
        int[][] dp=new int[n+1][m+1];
        for(int i=1;i<=n;i++){
            for (int j=1;j<=m;j++){
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+1;
                }else {
                    dp[i][j]=Math.max(dp[i][j-1],dp[i-1][j]);
                }
            }
        }
        return n+m-2*dp[n][m];
    }

    /**
     * LeetCode 712
     * @param s1
     * @param s2
     * @return
     */
    public int minimumDeleteSum(String s1, String s2) {
        int n=s1.length(),m=s2.length();
        int[][] dp=new int[n+1][m+1];
        for(int i=1;i<=n;i++){
            dp[i][0]=dp[i-1][0]+ s1.charAt(i-1);
        }
        for(int j=1;j<=m;j++){
            dp[0][j]=dp[0][j-1]+ s2.charAt(j-1);
        }
        for(int i=1;i<=n;i++){
            for (int j=1;j<=m;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }else {
                    dp[i][j]=Math.min(dp[i][j-1]+s2.charAt(j-1),dp[i-1][j]+s1.charAt(i-1));
                }
            }
        }
        return dp[n][m];
/*      变换一下思路，最长公共子序列求的是长度，这题可以改成求公共子串的最大ascii码，
然后让两个的总和减去两倍的 dp[n][m] 即可
        int n=s1.length(),m=s2.length();
        int[][] dp=new int[n+1][m+1];
        int sum1=0,sum2=0;
        for(int i=0;i<n;i++){
            sum1+= s1.charAt(i);
        }
        for(int j=0;j<m;j++){
            sum2+= s2.charAt(j);
        }
        for(int i=1;i<=n;i++){
            for (int j=1;j<=m;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+s1.charAt(i-1);
                }else {
                    dp[i][j]=Math.max(dp[i][j-1],dp[i-1][j]);
                }
            }
        }
        return sum1+sum2-2*dp[n][m];*/
    }

    /**
     * LeetCode 10
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        int n=s.length(),m=p.length();
        boolean[][] dp=new boolean[n+1][m+1];
        dp[0][0]=true;
        for(int i=0;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(p.charAt(j-1)=='*'){
                    if(j >= 2 && i >= 1&&(p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.')) {
                        // 看"x*":p不动s前移一位
                        dp[i][j] = dp[i - 1][j];
                        if(j >= 2) {
                            dp[i][j] |= dp[i][j - 2];
                        }
                    }
                }else {
                    if (i >= 1 && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.')) {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        return dp[n][m];
    }

    /**
     * LeetCode 877
     * @param piles
     * @return
     */
    public boolean stoneGame(int[] piles) {
        int n=piles.length;
        int[][][] dp=new int[n][n][2];
        for(int i=0;i<n;i++){
            dp[i][i][0]=piles[i];
            dp[i][i][1]=0;
        }
        for(int i=n-2;i>=0;i--){
            for (int j=i+1;j<n;j++){
                int left=piles[i]+dp[i+1][j][1];
                int right=piles[j]+dp[i][j-1][1];
                if (left > right) {
                    dp[i][j][0] = left;
                    dp[i][j][1]= dp[i+1][j][0];
                } else {
                    dp[i][j][0]= right;
                    dp[i][j][1] = dp[i][j-1][0];
                }
            }
        }
        int res=dp[0][n-1][0]-dp[0][n-1][1];
        return res>0;
    }

    /**
     * LeetCode 64
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        int[][] dp=new int[n][m];
        dp[0][0]=grid[0][0];
        for(int i=1;i<n;i++)
            dp[i][0]=grid[i][0]+dp[i-1][0];
        for(int j=1;j<m;j++){
            dp[0][j]=grid[0][j]+dp[0][j-1];
        }
        for(int i=1;i<n;i++){
            for (int j=1;j<m;j++){
                dp[i][j]=grid[i][j]+Math.min(dp[i-1][j],dp[i][j-1]);
            }
        }
        return dp[n-1][m-1];
    }

    /**
     * LeetCode 887
     * @param k
     * @param n
     * @return
     */
    public int superEggDrop(int k, int n) {
        int[][] dp=new int[k+1][n+1];
        int m;
        for(m=1;dp[k][m-1]<n;m++){
            for(int i=1;i<=k;i++){
                dp[i][m]=dp[i][m-1]+dp[i-1][m-1]+1;
            }
        }
        return m-1;
    }

    /**
     * LeetCode 174
     * @param dungeon
     * @return
     */
    public int calculateMinimumHP(int[][] dungeon) {
        int n=dungeon.length;
        int m=dungeon[0].length;
        int[][] dp=new int[n+1][m+1];
        for (int i=0;i<=n;i++)
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        dp[n][m-1]=dp[n-1][m]=1;
        for(int i=n-1;i>=0;i--){
            for (int j=m-1;j>=0;j--){
                dp[i][j]=Math.max(Math.min(dp[i+1][j],dp[i][j+1])-dungeon[i][j],1);
            }
        }
        return dp[0][0];
    }

    /**
     * LeetCode 514
     * @param ring
     * @param key
     * @return
     */
    HashMap<Character,List<Integer>> charToIndex=new HashMap<>();
    int[][] memo;
    public int findRotateSteps(String ring, String key) {
        int n=ring.length();
        int m=key.length();
        memo=new int[n][m];
        for (int i=0;i<ring.length();i++){
            char c=ring.charAt(i);
            if (!charToIndex.containsKey(c)) {
                charToIndex.put(c, new LinkedList<>());
            }
            charToIndex.get(c).add(i);
        }
        return dp(ring,0,key,0);
    }
    private int dp(String ring,int i,String key,int j){
        if(j==key.length()){
            return 0;
        }
        if(memo[i][j]!=0) return memo[i][j];
        int n=ring.length();
        int res=Integer.MAX_VALUE;
        for(int k:charToIndex.get(key.charAt(j))){
            int delta = Math.abs(k - i);
            delta = Math.min(delta, n - delta);
            int subProblem = dp(ring, k, key, j + 1);
            res = Math.min(res, 1 + delta + subProblem);
        }
        memo[i][j]=res;
        return res;
    }

    /**
     * LeetCode 787
     * @param n
     * @param flights
     * @param src
     * @param dst
     * @param k
     * @return
     */
    int src,dst;
    HashMap<Integer,List<int[]>> indegree;
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        /*二维数组动态规划*/
        int[][] dp=new int[k+2][n];
        for(int i=0;i<k+2;i++)
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        dp[0][src]=0;
        for(int i=1;i<=k+1;i++){
            for (int[] flight:flights){
                int j=flight[0],m=flight[1],cost=flight[2];
                dp[i][m]=Math.min(dp[i][m],dp[i-1][j]+cost);
            }
        }
        int res=Integer.MAX_VALUE;
        for(int i=1;i<=k+1;i++){
            res=Math.min(res,dp[i][dst]);
        }
        return res==Integer.MAX_VALUE?-1:res;

        //从上到下的 动态规划
/*        k++;
        this.src=src;
        this.dst=dst;
        memo=new int[n][k+1];
        for(int[] row:memo){
            Arrays.fill(row,-11);
        }
        indegree=new HashMap<>();
        for(int[] f:flights){
            int from=f[0];
            int to=f[1];
            int price=f[2];
            indegree.putIfAbsent(to,new LinkedList<>());
            indegree.get(to).add(new int[]{from,price});
        }
        return dp(dst,k);*/

    }
    private int dp(int s,int k){
        if(s==src) return 0;
        if(k==0)  return -1;
        if(memo[s][k]!=-11) return memo[s][k];

        int res=Integer.MAX_VALUE;
        if(indegree.containsKey(s)){
            for(int[] v:indegree.get(s)){
                int from=v[0];
                int price=v[1];
                int subProblem=dp(from,k-1);
                if(subProblem!=-1)
                    res=Math.min(res,subProblem+price);
            }
        }
        memo[s][k]= res==Integer.MAX_VALUE?-1:res;
        return memo[s][k];
    }
}
