package dichotomous_Figure;

import java.util.*;

public class dichotomousFigure {
    /**
     * LeetCode 785
     * @param graph
     * @return
     */
    /*DFS*/
    private boolean ok=true;
    private boolean[] color;
    private boolean[] visited;
    public boolean isBipartite_0(int[][] graph) {
        int n=graph.length;
        color=new boolean[n];
        visited=new boolean[n];
        for(int i=0;i<n;i++){
            if(!visited[i])
                traverse(graph,i);
        }
        return ok;
    }
    private void traverse(int[][] graph,int v){
        if(!ok) return;
        visited[v]=true;
        for(int w:graph[v]){
            if(!visited[w]){
                color[w]=!color[v];
                traverse(graph,w);
            }else{
                if(color[v]==color[w])
                    ok=false;
            }
        }
    }
    /*BFS*/
    public boolean isBipartite_1(int[][] graph){
        int n=graph.length;
        color=new boolean[n];
        visited=new boolean[n];
        for(int i=0;i<n;i++){
            if(!visited[i])
                traverse_1(graph,i);
        }
        return ok;
    }
    private void traverse_1(int[][] graph,int i){
        Deque<Integer> q=new ArrayDeque<>();
        visited[i]=true;
        q.offer(i);
        while (!q.isEmpty()&&ok){
            int cur=q.poll();
            for(int w:graph[cur]){
                if(!visited[w]){
                    color[w]=!color[cur];
                    visited[w]=true;
                    q.offer(w);
                }else{
                    if(color[w]==color[cur])
                        ok=false;
                }
            }
        }
    }

    /**
     * LeetCode 886
     * 给定一组n人（编号为1, 2, ..., n），我们想把每个人分进任意大小的两组。每个人都可能不喜欢其他人，那么他们不应该属于同一组。
     *
     * 给定整数 n和数组 dislikes，其中dislikes[i] = [ai, bi]，表示不允许将编号为 ai和bi的人归入同一组。当可以用这种方法将所有人分进两组时，返回 true；否则返回 false。
     * @param n
     * @param dislikes
     * @return
     */
    public boolean possibleBipartition(int n, int[][] dislikes) {
        color = new boolean[n + 1];
        visited = new boolean[n + 1];
        List<Integer>[] graph = buildGraph(n, dislikes);

        for (int v = 1; v <= n; v++) {
            if(!visited[v])
                traverse_2(graph,v);
        }
        return ok;
    }
    private void traverse_2(List<Integer>[] graph,int v){
        if (!ok) return;
        visited[v] = true;
        for (int w : graph[v]) {
            if (!visited[w]) {
                color[w] = !color[v];
                traverse_2(graph, w);
            } else {
                if (color[w] == color[v]) {
                    ok = false;
                }
            }
        }
    }
    private List<Integer>[] buildGraph(int n, int[][] dislikes){
        List<Integer>[] graph = new LinkedList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] edge : dislikes) {
            int v = edge[1];
            int w = edge[0];
            //无向图相当于双向图
            graph[v].add(w);
            graph[w].add(v);
        }
        return graph;
    }

}
