package Backtrack;

import java.util.*;

public class Backtrack {
    /**
     * LeetCode 46
     * @param nums
     * @return
     */
    List<List<Integer>> res=new LinkedList<>();
    public List<List<Integer>> permute(int[] nums){
        LinkedList<Integer> path=new LinkedList<>();
        boolean[] flag=new boolean[nums.length];
        backtrack(nums,path,flag);
        return res;
    }
    public void backtrack(int[] nums,LinkedList<Integer> path,boolean[] flag){
        if(nums.length==path.size()){
            res.add(new LinkedList<>(path));
            return;
        }
        for(int i=0;i<nums.length;i++){
            if(flag[i]) continue;
            flag[i]=true;
            path.add(nums[i]);
            backtrack(nums,path,flag);
            path.removeLast();
            flag[i]=false;
        }
    }

    /**
     * LeetCode 51
     * @param n
     * @return
     */
    List<List<String>> res0;
    public List<List<String>> solveNQueens(int n){
        char[][] board=new char[n][n];
        for(int i=0;i<n;i++){
            Arrays.fill(board[i],'.');
        }
        backtrack(board,0);
        return res0;
    }
    // 路径：board 中小于 row 的那些行都已经成功放置了皇后
    // 选择列表：第 row 行的所有列都是放置皇后的选择
    // 结束条件：row 超过 board 的最后一行
    private void backtrack(char[][] board,int row){
        if(row==board.length){
            List<String> list=new ArrayList<>();
            for(char[] c:board){
                list.add(String.copyValueOf(c));
            }
            res0.add(list);
        }

        int n=board[row].length;
        for(int col=0;col<n;col++){
            if(!isValid(board,row,col))
                continue;
            board[row][col]='Q';
            backtrack(board,row+1);
            board[row][col]='.';
        }
    }
    private boolean isValid(char[][] board,int row,int col){
        int n=board.length;
        // 检查列是否有皇后冲突
        for(int i=0;i<n;i++){
            if(board[i][col]=='Q')
                return false;
        }
        // 检查右上方是否有皇后冲突
        for (int i = row - 1, j = col + 1; i >=0 && j < n; i--, j++) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        // 检查左上方是否有皇后冲突
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    /**
     * LeetCode 698
     * @param nums
     * @param k
     * @return
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        //排除一些基本情况
        if(k>nums.length) return false;
        int sum=0;
        for(int v:nums) sum+=v;
        if(sum%k!=0) return false;
        int used=0;
        int target=sum/k;
        return backtrack(k,0,nums,0,used,target);
    }
    HashMap<Integer,Boolean> memo=new HashMap<>();
    private boolean backtrack(int k,int bucket,int[] nums,int start,int used,int target){
        if(k==0){
            return true;
        }
        if(bucket==target){
            boolean res=backtrack(k-1,0,nums,0,used,target);
            memo.put(used,res);
            return res;
        }

        for(int i=start;i<nums.length;i++){
            //剪枝
            if(((used>>i)&1)==1){
                //判断第i位是否为1
                //nums[i]以及被装入别的桶中
                continue;
            }
            if(nums[i]+bucket>target)
                continue;
            //做选择
            used |=1<<i; //将第i位 置为1
            bucket+=nums[i];
            //递归穷举下一个数字是否装入当前桶
            if(backtrack(k,bucket,nums,i+1,used,target)){
                return true;
            }
            //撤销选择
            used ^=1<<i;
            bucket -=nums[i];
        }
        return false;
    }
}
