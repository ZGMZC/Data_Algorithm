package binary_Tree;

import common_Utils.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
}
