package house_Robber;

import java.util.HashMap;
import java.util.Map;

public class houseRobber {
    /**
     * LeetCode 198
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额
     *很典型的动态规划问题
     * @param nums
     * @return
     */
    public int rob_0(int[] nums){
        int n=nums.length;
        int[] dp=new int[n];
        dp[0]=nums[0];
        if(n>1)
            dp[1]=Math.max(nums[0],nums[1]);
        for(int i=2;i<n;i++){
            dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i]);
        }
        return dp[n-1];
    }

    /**
     * LeetCode 213
     * 这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的。
     * @param nums
     * @return
     */
    public int rob_1(int[] nums){
        int n=nums.length;
        return Math.max(robRange(nums,0,n-1),robRange(nums,1,n));
    }
    private int robRange(int[] nums,int start,int end){
        int n=end;
        int[] dp=new int[end-start];
        if(end-start<1) return nums[0];

        dp[0]=nums[start];
        if(end-start>1)
        dp[1]=Math.max(nums[start],nums[start+1]);
        for(int i=2;i<end-start;i++){
            dp[i]=Math.max(dp[i-1],dp[i-2]+nums[i+start]);
        }
        return dp[end-start-1];
    }



    /**
     * LeetCode 337
     * 小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为root。
     *
     * 除了root之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 两个直接相连的房子在同一天晚上被打劫 ，房屋将自动报警。
     *
     * 给定二叉树的root。返回在不触动警报的情况下，小偷能够盗取的最高金额。
     * 递归+回溯
     * @param root
     * @return
     */
    Map<TreeNode,Integer> map=new HashMap<>();
    public int rob_2(TreeNode root){
        if(root==null) return 0;
        if(map.containsKey(root)) return map.get(root);

        int doit=root.val+ (root.left==null? 0:rob_2(root.left.left)+rob_2(root.left.right))
                +(root.right==null? 0:rob_2(root.right.left)+rob_2(root.right.right));
        int notdoit=rob_2(root.left)+rob_2(root.right);
        int res=Math.max(doit,notdoit);
        map.put(root,res);

        return res;
    }
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
}
