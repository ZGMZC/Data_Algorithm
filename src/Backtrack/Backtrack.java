package Backtrack;

import java.util.*;

public class Backtrack {
    /**
     * LeetCode 46
     *
     * @param nums
     * @return
     */
    List<List<Integer>> res = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {
        LinkedList<Integer> path = new LinkedList<>();
        boolean[] flag = new boolean[nums.length];
        backtrack(nums, path, flag);
        return res;
    }

    public void backtrack(int[] nums, LinkedList<Integer> path, boolean[] flag) {
        if (nums.length == path.size()) {
            res.add(new LinkedList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (flag[i]) continue;
            flag[i] = true;
            path.add(nums[i]);
            backtrack(nums, path, flag);
            path.removeLast();
            flag[i] = false;
        }
    }

    /**
     * LeetCode 51
     *
     * @param n
     * @return
     */
    List<List<String>> res0;

    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(board[i], '.');
        }
        backtrack(board, 0);
        return res0;
    }

    // 路径：board 中小于 row 的那些行都已经成功放置了皇后
    // 选择列表：第 row 行的所有列都是放置皇后的选择
    // 结束条件：row 超过 board 的最后一行
    private void backtrack(char[][] board, int row) {
        if (row == board.length) {
            List<String> list = new ArrayList<>();
            for (char[] c : board) {
                list.add(String.copyValueOf(c));
            }
            res0.add(list);
        }

        int n = board[row].length;
        for (int col = 0; col < n; col++) {
            if (!isValid(board, row, col))
                continue;
            board[row][col] = 'Q';
            backtrack(board, row + 1);
            board[row][col] = '.';
        }
    }

    private boolean isValid(char[][] board, int row, int col) {
        int n = board.length;
        // 检查列是否有皇后冲突
        for (int i = 0; i < n; i++) {
            if (board[i][col] == 'Q')
                return false;
        }
        // 检查右上方是否有皇后冲突
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
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
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        //排除一些基本情况
        if (k > nums.length) return false;
        int sum = 0;
        for (int v : nums) sum += v;
        if (sum % k != 0) return false;
        int used = 0;
        int target = sum / k;
        return backtrack(k, 0, nums, 0, used, target);
    }

    HashMap<Integer, Boolean> memo = new HashMap<>();

    private boolean backtrack(int k, int bucket, int[] nums, int start, int used, int target) {
        if (k == 0) {
            return true;
        }
        if (bucket == target) {
            boolean res = backtrack(k - 1, 0, nums, 0, used, target);
            memo.put(used, res);
            return res;
        }

        for (int i = start; i < nums.length; i++) {
            //剪枝
            if (((used >> i) & 1) == 1) {
                //判断第i位是否为1
                //nums[i]以及被装入别的桶中
                continue;
            }
            if (nums[i] + bucket > target)
                continue;
            //做选择
            used |= 1 << i; //将第i位 置为1
            bucket += nums[i];
            //递归穷举下一个数字是否装入当前桶
            if (backtrack(k, bucket, nums, i + 1, used, target)) {
                return true;
            }
            //撤销选择
            used ^= 1 << i;
            bucket -= nums[i];
        }
        return false;
    }

    /**
     * LeetCode 78
     *
     * @param nums
     * @return
     */
    List<List<Integer>> res1;
    List<Integer> path;

    public List<List<Integer>> subsets(int[] nums) {
        res1 = new LinkedList<>();
        path = new LinkedList<>();
        backtrack(nums, 0);
        return res;
    }

    public void backtrack(int[] nums, int start) {
        res.add(new LinkedList<>(path));
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            backtrack(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }

    /**
     * LeetCode 77
     *
     * @param n
     * @param k
     * @return
     */
    List<List<Integer>> res2 = new LinkedList<>();
    LinkedList<Integer> path2 = new LinkedList<>();

    public List<List<Integer>> combine(int n, int k) {
        backtrack2(n, 1, k);
        return res2;
    }

    private void backtrack2(int n, int start, int k) {
        if (path2.size() == k) {
            res2.add(new LinkedList<>(path2));
            return;
        }
        for (int i = start; i <= n; i++) {
            path2.add(i);
            backtrack2(n, i + 1, k);
            path2.removeLast();
        }
    }

    /**
     * LeetCode 90
     *
     * @param nums
     * @return
     */
    List<List<Integer>> res3 = new LinkedList<>();
    LinkedList<Integer> path3 = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        backtrack3(nums, 0);
        return res3;
    }

    private void backtrack3(int[] nums, int start) {
        res3.add(new LinkedList<>(path3));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue;
            path3.add(nums[i]);
            backtrack(nums, i + 1);
            path3.removeLast();
        }
    }

    /**
     * LeetCode 40
     *
     * @param candidates
     * @param target
     * @return
     */
    List<List<Integer>> res4 = new LinkedList<>();
    LinkedList<Integer> path4 = new LinkedList<>();
    int sum4 = 0;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        backtrack(candidates, target, 0);
        return res4;
    }

    private void backtrack(int[] candidates, int target, int start) {
        if (sum4 == target) {
            res4.add(new LinkedList<>(path4));
            return;
        }
        if (sum4 > target) return;
        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) continue;
            path4.add(candidates[i]);
            sum4 += candidates[i];
            backtrack(candidates, target, i + 1);
            path4.removeLast();
            sum4 -= candidates[i];
        }
    }

    /**
     * LeetCode 47
     *
     * @param nums
     * @return
     */
    List<List<Integer>> res5 = new LinkedList<>();
    LinkedList<Integer> path5 = new LinkedList<>();
    boolean[] visit;

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        visit = new boolean[nums.length];
        backtrack(nums);
        return res5;
    }

    private void backtrack(int[] nums) {
        if (path5.size() == nums.length) {
            res5.add(new LinkedList<>(path5));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visit[i]) continue;
            if (i > 0 && nums[i] == nums[i - 1] && !visit[i - 1]) continue;
            path5.add(nums[i]);
            visit[i] = true;
            backtrack(nums);
            path5.removeLast();
            visit[i] = false;
        }
    }

    /**
     * LeetCode 494
     *
     * @param nums
     * @param target
     * @return
     */
    int ans = 0;

    public int findTargetSumWays(int[] nums, int target) {
        backtrack(nums, target, 0, 0);
        return ans;
    }

    private void backtrack(int[] nums,int target,int num,int start){
        if(start==nums.length){
            if(num==target) ans++;
            return;
        }
        backtrack(nums,target,num+nums[start],start+1);
        backtrack(nums,target,num-nums[start],start+1);
    }

    /**
     * LeetCode 37
     * @param board
     */
    public void solveSudoku(char[][] board) {
        backtrack(board,0,0);
    }
    private boolean backtrack(char[][] board,int i,int j){
        int n=9,m=9;
        if(j==m){
            return backtrack(board,i+1,0);
        }
        if(i==n) return true;
        if (board[i][j] != '.') {
            return backtrack(board, i, j + 1);
        }
        for (char ch = '1'; ch <= '9'; ch++) {
            // 如果遇到不合法的数字，就跳过
            if (!isValid(board, i, j, ch))
                continue;
            board[i][j] = ch;
            if (backtrack(board, i, j + 1)) {
                return true;
            }
            board[i][j] = '.';
        }
        return false;
    }
    private boolean isValid(char[][] board, int r, int c, char n){
        for (int i = 0; i < 9; i++) {
            // 判断行是否存在重复
            if (board[r][i] == n) return false;
            // 判断列是否存在重复
            if (board[i][c] == n) return false;
            // 判断 3 x 3 方框是否存在重复
            if (board[(r/3)*3 + i/3][(c/3)*3 + i%3] == n)
                return false;
        }
        return true;
    }

    /**
     * LeetCode
     */
    List<String> res6=new LinkedList<>();
    String path6="";
    int n;
    public List<String> generateParenthesis(int n) {
        this.n=2*n;
        backtrack(path6,0,0,0);
        return res6;
    }
    private void backtrack(String path,int left,int right,int step){
        if(right>left) return;
        if(step==n){
            if(left==right){
                res6.add(new String(path6));
                return;
            }else{
                return;
            }
        }
        backtrack(path6+"(",left+1,right,step+1);
        backtrack(path6+")",left,right+1,step+1);
    }

    /**
     * LeetCode 969
     * @param arr
     * @return
     */
    List<Integer> res7=new LinkedList<>();
    public List<Integer> pancakeSort(int[] arr) {
        sort(arr,arr.length);
        return res7;
    }
    private void sort(int[] cakes,int n){
        if(n==1) return;

        //寻找最大饼的索引
        int maxCake=0;
        int maxCakeIndex=0;
        for(int i=0;i<n;i++){
            if(cakes[i]>maxCake){
                maxCake=cakes[i];
                maxCakeIndex=i;
            }
        }
        //第一次反转,将最大饼翻到上面
        reverse(cakes,0,maxCakeIndex);
        res7.add(maxCakeIndex+1);
        //第二次翻转，将最大饼翻到最下面
        reverse(cakes,0,n-1);
        res7.add(n);
        sort(cakes,n-1);
    }
    private void reverse(int[] arr,int i,int j){
        while (i<j){
            int temp=arr[i];
            arr[i]=arr[j];
            arr[j]=temp;
            i++;
            j--;
        }
    }

}
