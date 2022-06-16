package binary_Tree;

import common_Utils.TreeNode;

import java.util.*;

public class binaryTree {
    /**
     * LeetCode 104
     * 给定一个二叉树，找出其最大深度。
     *
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     *
     * 说明: 叶子节点是指没有子节点的节点。
     * @param root
     * @return
     */
    /*回溯算法思路*/
    //记录最大深度
    int res=0;
    //记录遍历到的节点的深度
    int depth=0;
    public int maxDepth_0(TreeNode root) {
        traverse_0(root);
        return res;
    }
    //二叉树遍历框架
    public void traverse_0(TreeNode root){
        if(root==null) return;
        //前序位置
        depth++;
        if(root.left==null&&root.right==null)
            res=Math.max(res,depth);
        traverse_0(root.left);
        traverse_0(root.right);
        //后序位置
        depth--;
    }
    /*动态规划思路*/
    public int maxDepth_1(TreeNode root){
        if(root==null) return 0;
        // 利用定义，计算左右子树的最大深度
        int left=maxDepth_1(root.left);
        int right=maxDepth_1(root.right);
        // 整棵树的最大深度等于左右子树的最大深度取最大值，
        // 然后再加上根节点自己
        res=Math.max(left,right)+1;
        return res;
    }

    /**
     * LeetCode 144
     * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
     * @param root
     * @return
     */
    /*回溯算法思路*/
    List<Integer> list=new ArrayList<>();
    public List<Integer> preorderTraversal_0(TreeNode root) {
        if(root==null) return new ArrayList<Integer>();
        traverse_1(root);
        return list;
    }
    public void traverse_1(TreeNode root){
        if(root==null)
            return;
        list.add(root.val);
        traverse_1(root.left);
        traverse_1(root.right);
    }
    /*动态规划思路*/
    public List<Integer> preorderTraversal_1(TreeNode root){
        List<Integer> list=new ArrayList<>();
        if(root==null) return list;
        list.add(root.val);
        list.addAll(preorderTraversal_1(root.left));
        list.addAll(preorderTraversal_1(root.right));
        return list;
    }

    /**
     * LeetCode 543
     * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
     * @param root
     * @return
     */
    public int diameterOfBinaryTree_0(TreeNode root) {
        if(root==null) return 0;
        int left=maxDepth_0(root.left);
        int right=maxDepth_0(root.right);
        int res=left+right;
        return Math.max(res,Math.max(diameterOfBinaryTree_0(root.right),diameterOfBinaryTree_0(root.left)));
    }

    /**
     * LeetCode 226
     * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        traverse_2(root);
        return root;
    }
    private void traverse_2(TreeNode root){
        if(root==null) return ;
        TreeNode tmp=root.left;
        root.left=root.right;
        root.right=tmp;
        traverse_2(root.left);
        traverse_2(root.right);
    }

    /**
     * LeetCode 116
     * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。
     * @param root
     * @return
     */

    public Node connect(Node root) {
        traverse_3(root.left,root.right);
        return root;
    }
    private void traverse_3(Node node1,Node node2){
        if(node1==null||node2==null) return;
        node1.next=node2;
        traverse_3(node1.left,node1.right);
        traverse_3(node2.left,node2.right);
        traverse_3(node1.right,node2.left);
    }
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    /**
     * LeetCode 114
     * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
     *
     * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
     * @param root
     */
    public void flatten(TreeNode root){
        if(root==null) return ;
        // 利用定义，把左右子树拉平
        flatten(root.left);
        flatten(root.right);
        //后序遍历
        //1. 左右子树已经被拉平成一条链表
        TreeNode left=root.left;
        TreeNode right=root.right;
        //2. 将左子树作为右子数
        root.left=null;
        root.right=left;
        //3. 将右子数接到后面
        TreeNode p=root;
        while (p.right!=null)
            p=p.right;
        p.right=right;
    }

    /**
     * LeetCode 654
     * 给定一个不重复的整数数组nums 。最大二叉树可以用下面的算法从nums 递归地构建:
     *
     * 创建一个根节点，其值为nums 中的最大值。
     * 递归地在最大值左边的子数组前缀上构建左子树。
     * 递归地在最大值 右边 的子数组后缀上构建右子树。
     * 返回nums 构建的 最大二叉树 。
     *
     * @param nums
     * @return
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }
    private TreeNode build(int[] nums,int lo,int hi){
        if(lo>hi) return null;
        int index=-1,maxValue=Integer.MIN_VALUE;
        for(int i=lo;i<=hi;i++){
            if(maxValue<nums[i]) {
                index=i;
                maxValue = nums[i];
            }
        }
        TreeNode root=new TreeNode(maxValue);
        root.left=build(nums,lo,index-1);
        root.right=build(nums,index+1,hi);
        return root;
    }

    /**
     * LeetCode 105
     * 给定两个整数数组preorder 和 inorder，其中preorder 是二叉树的先序遍历， inorder是同一棵树的中序遍历，请构造二叉树并返回其根节点。
     *
     * @param preorder
     * @param inorder
     * @return
     */
    Map<Integer,Integer> map=new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for(int i=0;i<inorder.length;i++)
            map.put(inorder[i],i);
        return build(preorder,0,preorder.length-1,
                     inorder,0,inorder.length-1);
    }
    private TreeNode build(int[] preorder, int preStart, int preEnd,
                           int[] inorder, int inStart, int inEnd){
        if(preStart>preEnd) return null;
        TreeNode root=new TreeNode(preorder[preStart]);
        int index=map.get(preorder[preStart]);
        int leftsize=index-inStart;
        root.left=build(preorder,preStart+1,preStart+leftsize,
                        inorder,inStart,index-1);
        root.right = build(preorder, preStart + leftsize + 1, preEnd,
                inorder, index + 1, inEnd);
        return root;
    }
}
