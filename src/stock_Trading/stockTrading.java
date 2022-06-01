package stock_Trading;

/**
 * 股票交易  模板
 */
public class stockTrading {
    /*
    状态定义：dp[i][k][s]
            0 <= i <= n-1, 1 <= k <= K, s in{0,1}(1 表示持有，0 表示没有)
            n 为天数，K为最多交易数
            dp[2][3][0]：第二天，最多可进行三次交易，没有股票
            此问题共 n × K × 2 种状态，全部穷举就能搞定
            for 0<=i<n
                for 1<=k<=K
                    for s in {0,1}
                        dp[i][k][s]=max(buy,sale,rest);
            return dp[n - 1][K][0];
     状态转移： dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
                            max( 选择 rest , 选择 sell )
              dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
                            max( 选择 rest , 选择 buy )
     边界条件：dp[-1][k][0] = dp[i][0][0] = 0
             dp[-1][k][1] = dp[i][0][1] = Integer.MIN_VALUE

     */

    /**
     * LeetCode 121
     * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格
     * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票
     * 即K=1,只能买一次，所以可以把k忽略
     * @param prices
     * @return
     */
    public int maxProfit_One(int[] prices){
        int n=prices.length;
        int[][] dp=new int[n][2];
        dp[0][0]=0;
        dp[0][1]=-prices[0];
        for (int i=1;i<n;i++){
            dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
            dp[i][1]=Math.max(dp[i-1][1],-prices[i]);// dp[i][1]= -prices[i] 证明只买了一次
        }
        return dp[n-1][0];
    }

    /**
     * LeetCode 122
     * 给你一个整数数组 prices ，其中prices[i] 表示某支股票第 i 天的价格。
     *
     * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候最多只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
     * 即K=Integer.MAX_VALUE，可以买无数次，所以可以把k忽略
     * @param prices
     * @return
     */
    public int maxProfit_MAX(int[] prices){
        int n= prices.length;
        int[][] dp=new int[n][2];
        dp[0][0]=0;
        dp[0][1]=-prices[0];
        for(int i=1;i<n;i++){
            dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
            dp[i][1]=Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);// dp[i][1]=dp[i-1][0]-prices[i]] 证明买了多次
        }
        return dp[n-1][0];
    }

    /**
     * LeetCode 309
     * 每次 sell 之后要等⼀天才能继续交易。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 即K=Integer.MAX_VALUE，可以买无数次，但是增加了冷静期，所以可以把k忽略
     * @param prices
     * @return
     */
    public int maxProfit_Cooldown(int[] prices){
        int n=prices.length;
        int[][] dp=new int[n][2];
        dp[0][0]=0;
        dp[0][1]=-prices[0];
        for (int i=1;i<n;i++){
            dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]);
            if(i>=2)
                dp[i][1]=Math.max(dp[i-1][1],dp[i-2][0]-prices[i]);
            else   //当i=1时，持有股票时 要么是i=0时所购买，要么是i=1时所购买
                dp[i][1]=Math.max(dp[i-1][1],-prices[i]);
        }
        return dp[n-1][0];
    }

    /**
     * LeetCode 714
     *给定一个整数数组prices，其中 prices[i]表示第i天的股票价格 ；整数fee 代表了交易股票的手续费用。
     * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
     * 返回获得利润的最大值。
     * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
     * 即K=Integer.MAX_VALUE，可以买无数次，但是增加了手续费，所以可以把k忽略
     * @param prices
     */
    public int maxProfit_Fee(int[] prices, int fee){
        int n=prices.length;
        int[][] dp=new int[n][2];
        dp[0][0]=0;
        dp[0][1]=-prices[0];
        for(int i=1;i<n;i++){
            dp[i][0]=Math.max(dp[i-1][0],dp[i-1][1]+prices[i]-fee);
            dp[i][1]=Math.max(dp[i-1][1],dp[i-1][0]-prices[i]);
        }
        return dp[n-1][0];
    }

    /**
     * LeetCode 123
     * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成两笔交易。
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 即K=2
     * @param prices
     * @return
     */
    public int maxProfit_Two(int[] prices){
        int n= prices.length;
        int k=2;
        int[][][] dp=new int[n][k][2];
        for(int i=0;i<k;i++){
            dp[0][i][0]=0;
            dp[0][i][1]=-prices[0];
        }
        for(int i=1;i<n;i++){
            for (int j=0;j<k;j++){
                if(j<1){
                    dp[i][j][0]=Math.max(dp[i-1][j][0],dp[i-1][j][1]+prices[i]);
                    dp[i][j][1]=Math.max(dp[i-1][j][1],-prices[i]);
                }
                else {
                    dp[i][j][0]=Math.max(dp[i-1][j][0],dp[i-1][j][1]+prices[i]);
                    dp[i][j][1]=Math.max(dp[i-1][j][1],dp[i-1][j-1][0]-prices[i]);
                }
            }
        }
        return dp[n-1][0][0];
    }

    /**
     * LeetCode 188
     * 给定一个整数数组prices ，它的第 i 个元素prices[i] 是一支给定的股票在第 i 天的价格。
     *
     * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
     *
     * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
     * 即K=Any_number
     * @param prices
     * @param k
     * @return
     */
    public int maxProfit_Any(int[] prices, int k){
        int n= prices.length;
        if(n<=0) return 0;
        int[][][] dp=new int[n][k][2];
        for(int i=0;i<k;i++){
            dp[0][i][0]=0;
            dp[0][i][1]=-prices[0];
        }
        for(int i=1;i<n;i++){
            for(int j=0;j<k;j++){
                if(j<1){
                    dp[i][j][0]=Math.max(dp[i-1][j][0],dp[i-1][j][1]+prices[i]);
                    dp[i][j][1]=Math.max(dp[i-1][j][1],-prices[i]);
                }
                else{
                    dp[i][j][0]=Math.max(dp[i-1][j][0],dp[i-1][j][1]+prices[i]);
                    dp[i][j][1]=Math.max(dp[i-1][j][1],dp[i-1][j-1][0]-prices[i]);
                }
            }
        }
        return dp[n-1][k-1][0];
    }
}
