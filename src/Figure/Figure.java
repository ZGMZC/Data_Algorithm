package Figure;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Figure {
    /**
     * LeetCode 797
     * 给你一个有n个节点的 有向无环图（DAG），请你找出所有从节点 0到节点 n-1的路径并输出（不要求按特定顺序）
     *
     * graph[i]是一个从节点 i 可以访问的所有节点的列表（即从节点 i 到节点graph[i][j]存在一条有向边）。
     * @param graph
     * @return
     */
    List<List<Integer>> res=new LinkedList<>();
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        //维护递归过程中经过的路径
        LinkedList<Integer> path=new LinkedList<>();
        traverse(graph,0,path);
        return res;
    }
    private void traverse(int[][] graph,int s,LinkedList<Integer> path){
        path.add(s);
        int n= graph.length;
        //到达终点
        if(s==n-1){
            res.add(new LinkedList<>(path));
        }
        for(int v:graph[s]){
            traverse(graph,v,path);
        }
        path.removeLast();
    }

    /**
     * LeetCode 207
     * 你这个学期必须选修 numCourses 门课程，记为0到numCourses - 1 。
     *
     * 在选修某些课程之前需要一些先修课程。 先修课程按数组prerequisites 给出，其中prerequisites[i] = [ai, bi] ，表示如果要学习课程ai 则 必须 先学习课程 bi 。
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    /*DFS*/
    boolean[] visit;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph=buildGraph(numCourses,prerequisites);
        visit=new boolean[numCourses];
        onPath=new boolean[numCourses];
        for(int i=0;i<numCourses;i++) {
            traverse_0(graph, i);
        }
        return !hasCycle;
    }
    boolean[] onPath;
    boolean hasCycle=false;
    private void traverse_0(List<Integer>[] graph,int s){
        if(onPath[s]) hasCycle=true;
        if(visit[s]||hasCycle) return;
        visit[s]=true;
        onPath[s]=true;
        for(int t:graph[s]){
            traverse_0(graph,t);
        }
        onPath[s]=false;
    }
    private List<Integer>[] buildGraph(int numCourses,int[][] prerequisites){
        //图中共有numCourses个节点
        List<Integer>[] graph=new LinkedList[numCourses];
        for(int i=0;i<numCourses;i++){
            graph[i]=new LinkedList<>();
        }
        for(int[] edge:prerequisites){
            int from=edge[1],to=edge[0];
            graph[from].add(to);
        }
        return graph;
    }
    /**
     * LeetCode 210
     * 现在你总共有 numCourses 门课需要选，记为0到numCourses - 1。给你一个数组prerequisites ，其中 prerequisites[i] = [ai, bi] ，表示在选修课程 ai 前 必须 先选修bi
     返回你为了学完所有课程所安排的学习顺序。可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
     */
    List<Integer> postorder;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph=buildGraph(numCourses,prerequisites);
        onPath=new boolean[numCourses];
        visit=new boolean[numCourses];
        postorder=new LinkedList<>();
        hasCycle=false;
        for(int i=0;i<numCourses;i++)
            traverse(graph,i);
        if(hasCycle) return new int[]{};
        Collections.reverse(postorder);
        int[] res=new int[numCourses];
        for(int i=0;i<numCourses;i++)
            res[i]=postorder.get(i);
        return res;
    }
    private void traverse(List<Integer>[] graph,int s){
        if(onPath[s]) hasCycle=true;
        if(visit[s]||hasCycle) return;
        onPath[s]=true;
        visit[s]=true;
        for(int t:graph[s]){
            traverse(graph,t);
        }
        postorder.add(s);
        onPath[s]=false;
    }
}
