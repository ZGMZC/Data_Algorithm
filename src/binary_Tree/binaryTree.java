package binary_Tree;

import common_Utils.TreeNode;

import java.util.*;

public class binaryTree {
    /**
     * LeetCode 104
     * 给定一个二叉树，找出其最大深度。
     * <p>
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * <p>
     * 说明: 叶子节点是指没有子节点的节点。
     *
     * @param root
     * @return
     */
    /*回溯算法思路*/
    //记录最大深度
    int res = 0;
    //记录遍历到的节点的深度
    int depth = 0;

    public int maxDepth_0(TreeNode root) {
        traverse_0(root);
        return res;
    }

    //二叉树遍历框架
    public void traverse_0(TreeNode root) {
        if (root == null) return;
        //前序位置
        depth++;
        if (root.left == null && root.right == null)
            res = Math.max(res, depth);
        traverse_0(root.left);
        traverse_0(root.right);
        //后序位置
        depth--;
    }

    /*动态规划思路*/
    public int maxDepth_1(TreeNode root) {
        if (root == null) return 0;
        // 利用定义，计算左右子树的最大深度
        int left = maxDepth_1(root.left);
        int right = maxDepth_1(root.right);
        // 整棵树的最大深度等于左右子树的最大深度取最大值，
        // 然后再加上根节点自己
        res = Math.max(left, right) + 1;
        return res;
    }

    /**
     * LeetCode 144
     * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
     *
     * @param root
     * @return
     */
    /*回溯算法思路*/
    List<Integer> list = new ArrayList<>();

    public List<Integer> preorderTraversal_0(TreeNode root) {
        if (root == null) return new ArrayList<Integer>();
        traverse_1(root);
        return list;
    }

    public void traverse_1(TreeNode root) {
        if (root == null)
            return;
        list.add(root.val);
        traverse_1(root.left);
        traverse_1(root.right);
    }

    /*动态规划思路*/
    public List<Integer> preorderTraversal_1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        list.add(root.val);
        list.addAll(preorderTraversal_1(root.left));
        list.addAll(preorderTraversal_1(root.right));
        return list;
    }

    /**
     * LeetCode 543
     * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
     *
     * @param root
     * @return
     */
    public int diameterOfBinaryTree_0(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth_0(root.left);
        int right = maxDepth_0(root.right);
        int res = left + right;
        return Math.max(res, Math.max(diameterOfBinaryTree_0(root.right), diameterOfBinaryTree_0(root.left)));
    }

    /**
     * LeetCode 226
     * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
     *
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        traverse_2(root);
        return root;
    }

    private void traverse_2(TreeNode root) {
        if (root == null) return;
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        traverse_2(root.left);
        traverse_2(root.right);
    }

    /**
     * LeetCode 116
     * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。
     *
     * @param root
     * @return
     */

    public Node connect(Node root) {
        traverse_3(root.left, root.right);
        return root;
    }

    private void traverse_3(Node node1, Node node2) {
        if (node1 == null || node2 == null) return;
        node1.next = node2;
        traverse_3(node1.left, node1.right);
        traverse_3(node2.left, node2.right);
        traverse_3(node1.right, node2.left);
    }

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

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
     * <p>
     * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
     * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
     *
     * @param root
     */
    public void flatten(TreeNode root) {
        if (root == null) return;
        // 利用定义，把左右子树拉平
        flatten(root.left);
        flatten(root.right);
        //后序遍历
        //1. 左右子树已经被拉平成一条链表
        TreeNode left = root.left;
        TreeNode right = root.right;
        //2. 将左子树作为右子数
        root.left = null;
        root.right = left;
        //3. 将右子数接到后面
        TreeNode p = root;
        while (p.right != null)
            p = p.right;
        p.right = right;
    }

    /**
     * LeetCode 654
     * 给定一个不重复的整数数组nums 。最大二叉树可以用下面的算法从nums 递归地构建:
     * <p>
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

    private TreeNode build(int[] nums, int lo, int hi) {
        if (lo > hi) return null;
        int index = -1, maxValue = Integer.MIN_VALUE;
        for (int i = lo; i <= hi; i++) {
            if (maxValue < nums[i]) {
                index = i;
                maxValue = nums[i];
            }
        }
        TreeNode root = new TreeNode(maxValue);
        root.left = build(nums, lo, index - 1);
        root.right = build(nums, index + 1, hi);
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
    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++)
            map.put(inorder[i], i);
        return build(preorder, 0, preorder.length - 1,
                inorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int preStart, int preEnd,
                           int[] inorder, int inStart, int inEnd) {
        if (preStart > preEnd) return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        int index = map.get(preorder[preStart]);
        int leftsize = index - inStart;
        root.left = build(preorder, preStart + 1, preStart + leftsize,
                inorder, inStart, index - 1);
        root.right = build(preorder, preStart + leftsize + 1, preEnd,
                inorder, index + 1, inEnd);
        return root;
    }

    /**
     * LeetCode 106
     * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历， postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
     *
     * @param inorder
     * @param postorder
     * @return
     */
    Map<Integer, Integer> map_0 = new HashMap<>();

    public TreeNode buildTree_0(int[] inorder, int[] postorder) {
        for (int i = 0; i < inorder.length; i++)
            map_0.put(inorder[i], i);
        return build_0(inorder, 0, inorder.length - 1,
                postorder, 0, postorder.length - 1);
    }

    private TreeNode build_0(int[] inorder, int inStart, int inEnd,
                             int[] postorder, int postStart, int postEnd) {
        if (inStart > inEnd) {
            return null;
        }
        // root 节点对应的值就是后序遍历数组的最后一个元素
        int rootVal = postorder[postEnd];
        // rootVal 在中序遍历数组中的索引
        int index = map_0.get(rootVal);
        // 左子树的节点个数
        int leftSize = index - inStart;
        TreeNode root = new TreeNode(rootVal);
        // 递归构造左右子树
        root.left = build(inorder, inStart, index - 1,
                postorder, postStart, postStart + leftSize - 1);
        root.right = build(inorder, index + 1, inEnd,
                postorder, postStart + leftSize, postEnd - 1);
        return root;
    }

    /**
     * LeetCode 889
     * 给定两个整数数组，preorder和 postorder ，其中 preorder 是一个具有 无重复 值的二叉树的前序遍历，postorder 是同一棵树的后序遍历，重构并返回二叉树。
     * <p>
     * 如果存在多个答案，您可以返回其中 任何 一个。
     *
     * @param preorder
     * @param postorder
     * @return
     */
    Map<Integer, Integer> map_1 = new HashMap<>();

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        for (int i = 0; i < postorder.length; i++) {
            map_1.put(postorder[i], i);
        }
        return build(preorder, 0, preorder.length - 1,
                postorder, 0, postorder.length - 1);
    }

    private TreeNode build_1(int[] preorder, int preStart, int preEnd,
                             int[] postorder, int postStart, int postEnd) {
        if (preStart > preEnd) {
            return null;
        }
        if (preStart == preEnd) {
            return new TreeNode(preorder[preStart]);
        }
        // root 节点对应的值就是前序遍历数组的第⼀个元素
        int rootVal = preorder[preStart];
        // root.left 的值是前序遍历第⼆个元素
        // 通过前序和后序遍历构造⼆叉树的关键在于通过左⼦树的根节点
        // 确定 preorder 和 postorder 中左右⼦树的元素区间
        int leftRootVal = preorder[preStart + 1];
        // leftRootVal 在后序遍历数组中的索引
        int index = map_1.get(leftRootVal);
        // 左子树的元素个数
        int leftSize = index - postStart + 1;
        // 先构造出当前根节点
        TreeNode root = new TreeNode(rootVal);
        // 递归构造左右子树
        // 根据左子树的根节点索引和元素个数推导左右子树的索引边界
        root.left = build(preorder, preStart + 1, preStart + leftSize,
                postorder, postStart, index);
        root.right = build(preorder, preStart + leftSize + 1, preEnd,
                postorder, index + 1, postEnd - 1);
        return root;
    }

    /**
     * LeetCode 297
     * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
     *
     * @param root
     * @return
     */
    // Encodes a tree to a single string.
    String SEP = ",";
    String NULL = "#";
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        sb.deleteCharAt(sb.length()-1).toString();
        return sb.deleteCharAt(sb.length()-1).toString();
    }
    private void serialize(TreeNode root,StringBuilder sb){
        if(root==null) {
            sb.append(NULL).append(SEP);
            return;
        }
        sb.append(root.val).append(SEP);
        serialize(root.left,sb);
        serialize(root.right,sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        List<String> nodes=new ArrayList<>();
        for(String s:data.split(SEP))
            nodes.add(s);
        return deserialize(nodes);
    }
    private TreeNode deserialize(List<String> nodes){
        if(nodes.isEmpty()) return null;
        String first=nodes.remove(0);
        if(first.equals(NULL)) return null;
        else {
            TreeNode root=new TreeNode(Integer.parseInt(first));
            root.left=deserialize(nodes);
            root.right=deserialize(nodes);
            return root;
        }
    }

    /**
     * LeetCode 652
     * 给定一棵二叉树 root，返回所有重复的子树。
     *
     * 对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
     *
     * 如果两棵树具有相同的结构和相同的结点值，则它们是重复的。
     *
     * @param root
     * @return
     */
    HashMap<String,Integer> memo=new HashMap<>();
    List<TreeNode> res_0=new ArrayList<>();
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        traverse(root);
        return res_0;
    }
    private String traverse(TreeNode root){
        if(root==null) return "#";
        String left=traverse(root.left);
        String right=traverse(root.right);
        String s=left+","+right+","+root.val;
        int freq=memo.getOrDefault(s,0);
        if(freq==1){
            res_0.add(root);
        }
        else{
            memo.put(s,freq+1);
        }
        return s;
    }
}
