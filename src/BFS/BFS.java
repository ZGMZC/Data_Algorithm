package BFS;

import common_Utils.TreeNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class BFS {
    /*BFS 框架*/
/*    public int BFS(Node start,Node target){
        Queue<Node> q;//核心数据结构
        Set<Node> visit;//避免走回头路
        q.offer(start);//将起点加入队列
        visit.add(start);
        int step=0;//记录扩散的步数
        while (!q.isEmpty()){
            int sz=q.size();
            *//* 将当前队列中的所有节点向四周扩散 *//*
            for(int i=0;i<sz;i++){
                Node cur=q.poll();
                *//*这里判断是否达到终点*//*
                if(cur is target){
                    return step;
                }
                *//*将cur的相邻节点加入队列*//*
                for(Node x:cur.adj()){
                    if(x not in visit){
                        q.offer(x);
                        visit.add(x);
                    }
                }
            }
            *//*更新步数*//*
            step++;
        }
    }*/

    /**
     * LeetCode 111
     * @param root
     * @return
     */
    public int minDepth(TreeNode root) {
        if(root==null) return 0;
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(root);
        int depth=1;
        while (!q.isEmpty()){
            int sz = q.size();
            for(int i=0;i<sz;i++){
                TreeNode cur=q.poll();
                /* 判断是否到达终点 */
                if (cur.left == null && cur.right == null)
                    return depth;
                /* 将 cur 的相邻节点加入队列 */
                if(cur.left!=null) q.offer(cur.left);
                if(cur.right!=null) q.offer(cur.right);
            }
            depth++;
        }
        return depth;
    }

    /**
     * LeetCOde 752
     * @param deadends
     * @param target
     * @return
     */
    public int openLock(String[] deadends, String target){
        //记录需要跳过的死亡密码
        Set<String> deads=new HashSet<>();
        for(String s:deadends) deads.add(s);
        //记录已经穷举过的密码，防止走回头路
        Set<String> visit=new HashSet<>();
        Queue<String> q=new LinkedList<>();
        //从起点开始启动广度优先搜索;
        int step=0;
        q.offer("0000");
        visit.add("0000");
        while (!q.isEmpty()){
            int sz=q.size();
            for(int i=0;i<sz;i++){
                String cur=q.poll();
                /*判断是否达到终点*/
                if(deads.contains(cur))
                    continue;
                if(cur.equals(target))
                    return step;
                /*将一个节点的未遍历相邻节点加入队列*/
                for(int j=0;j<4;j++){
                    String up=plusOne(cur,j);
                    if(!visit.contains(up)){
                        q.offer(up);
                        visit.add(up);
                    }
                    String down=minusOne(cur,j);
                    if(!visit.contains(down)){
                        q.offer(down);
                        visit.add(down);
                    }
                }
            }
            step++;
        }
        return -1;
    }
    private String plusOne(String s,int j){
        char[] ch=s.toCharArray();
        if(ch[j]=='9')
            ch[j]='0';
        else
            ch[j]+=1;
        return new String(ch);
    }
    private String minusOne(String s,int j){
        char[] ch=s.toCharArray();
        if(ch[j]=='0')
            ch[j]='9';
        else
            ch[j]-=1;
        return new String(ch);
    }

    /**
     * LeetCode 773
     * @param board
     * @return
     */
    public int slidingPuzzle(int[][] board) {
        int n=2,m=3;
        StringBuilder sb=new StringBuilder();
        String target="123450";
        for(int i=0;i<n;i++){
            for (int j=0;j<m;j++){
                sb.append(board[i][j]);
            }
        }
        String start = sb.toString();
        // 记录一维字符串的相邻索引
        int[][] neighbor = new int[][]{
                {1, 3},
                {0, 4, 2},
                {1, 5},
                {0, 4},
                {3, 1, 5},
                {4, 2}
        };
        /******* BFS 算法框架开始 *******/
        Queue<String> q = new LinkedList<>();
        HashSet<String> visited = new HashSet<>();
        // 从起点开始 BFS 搜索
        q.offer(start);
        visited.add(start);
        int step=0;
        while (!q.isEmpty()){
            int sz = q.size();
            for (int i = 0; i < sz; i++){
                String cur = q.poll();
                // 判断是否达到目标局面
                if (target.equals(cur)) {
                    return step;
                }
                // 找到数字 0 的索引
                int idx = 0;
                for (; cur.charAt(idx) != '0'; idx++) ;
                // 将数字 0 和相邻的数字交换位置
                for (int adj : neighbor[idx]){
                    String new_board = swap(cur.toCharArray(), adj, idx);
                    // 防止走回头路
                    if (!visited.contains(new_board)) {
                        q.offer(new_board);
                        visited.add(new_board);
                    }
                }
            }
            step++;
        }
        return step;
    }
    private String swap(char[] chars, int i, int j){
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }
}
