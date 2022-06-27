package Dijkstra;

import java.util.*;

public class Dijkstra {
    /**
     * LeetCode 743
     * @param times
     * @param n
     * @param k
     * @return
     */
    public int networkDelayTime(int[][] times, int n, int k){
        //节点编号是从1开始的，所以要一个大小为n+1的邻接表
        List<int[]>[] graph=new LinkedList[n+1];
        for (int i=1;i<=n;i++){
            graph[i]=new LinkedList<>();
        }
        //构造图
        for(int[] edge:times){
            int from=edge[0];
            int to=edge[1];
            int weight=edge[2];
            //from->List<(to,weight)>
            //邻接表存储图结构，同时存储权重信息
            graph[from].add(new int[]{to,weight});
        }
        int[] distTo=dijkstra(k,graph);
        //找到最长的那一条最短路径
        int res=0;
        for(int i=1;i<distTo.length;i++){
            if(distTo[i]==Integer.MAX_VALUE){
                return -1;
            }
            res=Math.max(res,distTo[i]);
        }
        return res;
    }
    //输入一个起点 start,计算从start到其他节点的最短距离
    public int[] dijkstra(int start,List<int[]>[] graph){
        // 定义：distTo[i] 的值就是起点 start 到达节点 i 的最短路径权重
        int[] distTo = new int[graph.length];
        Arrays.fill(distTo, Integer.MAX_VALUE);
        // base case，start 到 start 的最短距离就是 0
        distTo[start] = 0;
        // 优先级队列，distFromStart 较小的排在前面
        Queue<State> pq = new PriorityQueue<>((a, b) -> {
            return a.distFromStart - b.distFromStart;
        });
        // 从起点 start 开始进行 BFS
        pq.offer(new State(start, 0));
        while (!pq.isEmpty()) {
            State curState = pq.poll();
            int curNodeID = curState.id;
            int curDistFromStart = curState.distFromStart;
            if (curDistFromStart > distTo[curNodeID]) {
                continue;
            }
            // 将 curNode 的相邻节点装入队列
            for (int[] neighbor : graph[curNodeID]) {
                int nextNodeID = neighbor[0];
                int distToNextNode = distTo[curNodeID] + neighbor[1];
                // 更新 dp table
                if (distTo[nextNodeID] > distToNextNode) {
                    distTo[nextNodeID] = distToNextNode;
                    pq.offer(new State(nextNodeID, distToNextNode));
                }
            }
        }
        return distTo;
    }

    /**
     * LeetCode 1631
     * @param heights
     * @return
     */
    // Dijkstra 算法，计算 (0, 0) 到 (m - 1, n - 1) 的最小体力消耗
    public int minimumEffortPath(int[][] heights){
        int m = heights.length, n = heights[0].length;
        // 定义：从 (0, 0) 到 (i, j) 的最小体力消耗是 effortTo[i][j]
        int[][] effortTo = new int[m][n];
        // dp table 初始化为正无穷
        for (int i = 0; i < m; i++) {
            Arrays.fill(effortTo[i], Integer.MAX_VALUE);
        }
        // base case，起点到起点的最小消耗就是 0
        effortTo[0][0] = 0;
        // 优先级队列，effortFromStart 较小的排在前面
        Queue<State0> pq = new PriorityQueue<>((a, b) -> {
            return a.effortFromStart - b.effortFromStart;
        });
        // 从起点 (0, 0) 开始进行 BFS
        pq.offer(new State0(0, 0, 0));
        while (!pq.isEmpty()){
            State0 curState = pq.poll();
            int curX = curState.x;
            int curY = curState.y;
            int curEffortFromStart = curState.effortFromStart;
            // 到达终点提前结束
            if (curX == m - 1 && curY == n - 1) {
                return curEffortFromStart;
            }
            if (curEffortFromStart > effortTo[curX][curY]) {
                continue;
            }
            // 将 (curX, curY) 的相邻坐标装入队列
            for (int[] neighbor : adj(heights, curX, curY)) {
                int nextX = neighbor[0];
                int nextY = neighbor[1];
                // 计算从 (curX, curY) 达到 (nextX, nextY) 的消耗
                int effortToNextNode = Math.max(
                        effortTo[curX][curY],
                        Math.abs(heights[curX][curY] - heights[nextX][nextY])
                );
                // 更新 dp table
                if (effortTo[nextX][nextY] > effortToNextNode) {
                    effortTo[nextX][nextY] = effortToNextNode;
                    pq.offer(new State0(nextX, nextY, effortToNextNode));
                }
            }
        }
        // 正常情况不会达到这个 return
        return -1;
    }
    // 方向数组，上下左右的坐标偏移量
    int[][] dirs = new int[][]{{0,1}, {1,0}, {0,-1}, {-1,0}};
    // 返回坐标 (x, y) 的上下左右相邻坐标
    List<int[]> adj(int[][] matrix, int x, int y){
        int m = matrix.length, n = matrix[0].length;
        // 存储相邻节点
        List<int[]> neighbors = new ArrayList<>();
        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if (nx >= m || nx < 0 || ny >= n || ny < 0) {
                // 索引越界
                continue;
            }
            neighbors.add(new int[]{nx, ny});
        }
        return neighbors;
    }

    /**
     * LeetCode 1514
     * @param n
     * @param edges
     * @param succProb
     * @param start
     * @param end
     * @return
     */
    double maxProbability(int n, int[][] edges, double[] succProb, int start,
                          int end){
        List<double[]>[] graph = new LinkedList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new LinkedList<>();
        }
        // 构造邻接表结构表示图
        for (int i = 0; i < edges.length; i++) {
            int from = edges[i][0];
            int to = edges[i][1];
            double weight = succProb[i];
            // 无向图就是双向图；先把 int 统⼀转成 double，待会再转回来
            graph[from].add(new double[]{(double)to, weight});
            graph[to].add(new double[]{(double)from, weight});
        }
        return dijkstra(start, end, graph);
    }
    double dijkstra(int start, int end, List<double[]>[] graph){
        // 定义：probTo[i] 的值就是节点 start 到达节点 i 的最大概率
        double[] probTo = new double[graph.length];
        // dp table 初始化为⼀个取不到的最小值
        Arrays.fill(probTo, -1);
        // base case，start 到 start 的概率就是 1
        probTo[start] = 1;
        // 优先级队列，probFromStart 较大的排在前面
        Queue<State1> pq = new PriorityQueue<>((a, b) -> {
            return Double.compare(b.probFromStart, a.probFromStart);
        });
        // 从起点 start 开始进行 BFS
        pq.offer(new State1(start, 1));
        while (!pq.isEmpty()){
            State1 curState = pq.poll();
            int curNodeID = curState.id;
            double curProbFromStart = curState.probFromStart;
            // 遇到终点提前返回
            if (curNodeID == end) {
                return curProbFromStart;
            }
            if (curProbFromStart < probTo[curNodeID]) {
            // 已经有一条概率更大的路径到达 curNode 节点了
                continue;
            }
            // 将 curNode 的相邻节点装入队列
            for (double[] neighbor : graph[curNodeID]) {
                int nextNodeID = (int)neighbor[0];
                // 看看从 curNode 达到 nextNode 的概率是否会更大
                double probToNextNode = probTo[curNodeID] * neighbor[1];
                if (probTo[nextNodeID] < probToNextNode) {
                    probTo[nextNodeID] = probToNextNode;
                    pq.offer(new State1(nextNodeID, probToNextNode));
                }
            }
        }
        // 如果到达这里，说明从 start 开始⽆法到达 end，返回 0
        return 0.0;
    }
}
class State{
    //图节点的id
    int id;
    //从start节点到当前节点的距离
    int distFromStart;
    public State(int id,int distFromStart){
        this.id=id;
        this.distFromStart=distFromStart;
    }
}
class State0 {
    // 矩阵中的⼀个位置
    int x, y;
    // 从起点 (0, 0) 到当前位置的最⼩体⼒消耗（距离）
    int effortFromStart;
    State0(int x, int y, int effortFromStart) {
        this.x = x;
        this.y = y;
        this.effortFromStart = effortFromStart;
    }
}
class State1 {
    // 图节点的 id
    int id;
    // 从 start 节点到达当前节点的概率
    double probFromStart;
    State1(int id, double probFromStart) {
        this.id = id;
        this.probFromStart = probFromStart;
    }
}
