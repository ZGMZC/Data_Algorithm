package binarysearch_Tree;

import common_Utils.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class binarysearchTree {
    /**
     * LeetCode 230
     * 给定一个二叉搜索树的根节点 root ，和一个整数 k ，请你设计一个算法查找其中第 k 个最小元素（从 1 开始计数）。
     * @param root
     * @param k
     * @return
     */
    public int kthSmallest(TreeNode root, int k) {
        traverse(root,k);
        return res;
    }
    int res=0; //记录结果
    int rank=0;//记录当前元素的排名
    private void traverse(TreeNode root,int k){
        if(root==null) return;
        traverse(root.left,k);
        rank++;
        if(rank==k)
            res=root.val;
        traverse(root.right,k);
    }

    /**
     * LeetCode 538/1038
     * 给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node的新值等于原树中大于或等于node.val的值之和。
     * @param root
     * @return
     */
    public TreeNode convertBST(TreeNode root) {
        traverse_0(root);
        return root;
    }
    int sum=0;
    private void traverse_0(TreeNode root){
        if(root==null) return;
        traverse_0(root.right);
        root.val+=sum;
        sum=root.val;
        traverse_0(root.left);
    }

    /**
     * LeetCode 98
     * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root,null,null);
    }
    private boolean isValidBST(TreeNode root,TreeNode min,TreeNode max){
        if(root==null) return true;
        if(min!=null&&root.val<=min.val) return false;
        if(max!=null&&root.val>=max.val) return false;
        return isValidBST(root.left,min,root)&&isValidBST(root.right,root,max);
    }

    /**
     * LeetCode 700
     * 给定二叉搜索树（BST）的根节点root和一个整数值val。
     *
     * 你需要在 BST 中找到节点值等于val的节点。 返回以该节点为根的子树。 如果节点不存在，则返回null。
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if(root==null) return null;
        if(root.val>val) return searchBST(root.left,val);
        if(root.val<val) return searchBST(root.right,val);
        return root;
    }

    /**
     * LeetCode 701
     * 给定二叉搜索树（BST）的根节点root和要插入树中的值value，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据 保证 ，新值和原始二叉搜索树中的任意节点值都不同。
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if(root==null) return new TreeNode(val);
        if(root.val>val) root.left=insertIntoBST(root.left,val);
        if(root.val<val) root.right=insertIntoBST(root.right,val);
        return root;
    }

    /**
     * LeetCode 450
     * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的key对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
     *
     * @param root
     * @param key
     * @return
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val == key) {
        // 这两个 if 把情况 1 和 2 都正确处理了
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
        // 处理情况 3
        // 获得右子树最小的节点
            TreeNode minNode = getMin(root.right);
        // 删除右子树最小的节点
            root.right = deleteNode(root.right, minNode.val);
        // 用右子树最小的节点替换 root 节点
            minNode.left = root.left;
            minNode.right = root.right;
            root = minNode;
        }else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }
    TreeNode getMin(TreeNode node) {
        // BST 最左边的就是最小的
        while (node.left != null) node = node.left;
        return node;
    }

    /**
     * LeetCode 96
     * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？返回满足题意的二叉搜索树的种数。
     * @param n
     * @return
     */
    public int numTrees(int n) {
        memo=new int[n+1][n+1];
        return count(1,n);
    }
    int[][] memo;
    private int count(int lo, int hi){
        if(lo>hi) return 1;
        if(memo[lo][hi]!=0) return memo[lo][hi];

        int res=0;
        for(int i=lo;i<=hi;i++){
            int left=count(lo,i-1);
            int right=count(i+1,hi);
            res+=left*right;
        }
        memo[lo][hi]=res;
        return res;
    }

    /**
     * LeetCode 95
     * 给你一个整数 n ，请你生成并返回所有由 n 个节点组成且节点值从 1 到 n 互不相同的不同 二叉搜索树 。可以按 任意顺序 返回答案。
     * @param n
     * @return
     */
    public List<TreeNode> generateTrees(int n) {
        if(n==0) return new LinkedList<>();
        return build(1,n);
    }
    private List<TreeNode> build(int lo,int hi){
        List<TreeNode> res=new LinkedList<>();
        if(lo>hi) {
            res.add(null);
            return res;
        }
        for(int i=lo;i<=hi;i++){
            List<TreeNode> leftTree= build(lo,i-1);
            List<TreeNode> rightTree=build(i+1,hi);
            for (TreeNode left : leftTree) {
                for (TreeNode right : rightTree) {
                    // i 作为根节点 root 的值
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }

    /**
     * LeetCode 1373
     * 给你一棵以 root 为根的 二叉树 ，请你返回 任意 二叉搜索子树的最大键值和。
     * @param root
     * @return
     */
    int maxSum;
    public int maxSumBST(TreeNode root) {
        traverse_1(root);
        return maxSum;
    }
    /*res[0] 记录以 root 为根的二叉树是否是 BST，若为 1 则说明是 BST，若为 0 则说明不是 BST；
res[1] 记录以 root 为根的二叉树所有节点中的最小值；
res[2] 记录以 root 为根的二叉树所有节点中的最大值；
res[3] 记录以 root 为根的二叉树所有节点值之和。*/
    private int[] traverse_1(TreeNode root){
        if(root==null){
            return new int[]{
                    1,Integer.MAX_VALUE,Integer.MIN_VALUE,0
            };
        }
        int[] left=traverse_1(root.left);
        int[] right=traverse_1(root.right);
        int[] res=new int[4];
        if(left[0]==1&&right[0]==1&&root.val>left[2]&&root.val<right[1]){
            res[0]=1;
            res[1]=Math.min(left[1], root.val);
            res[2]=Math.max(right[2],root.val);
            res[3]=left[3]+right[3]+root.val;
            maxSum=Math.max(maxSum,res[3]);
        }else {
            res[0] = 0;
        }
        return res;
    }
}
