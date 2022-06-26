package Kruskal;

import union_Find.unionFind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kruskal {
    /**
     * LeetCode 1584
     * @param points
     * @return
     */
    public int minCostConnectPoints(int[][] points) {
        int n=points.length;
        //生成所有边及权重
        List<int[]> edges=new ArrayList<>();
        for(int i=0;i<n;i++){
            for (int j=i+1;j<n;j++){
                int xi=points[i][0],yi=points[i][1];
                int xj=points[j][0],yj=points[j][1];
                //用坐标点在points中的索引表示坐标点
                edges.add(new int[]{
                        i,j,Math.abs(xi-xj)+Math.abs(yi-yj)
                });
            }
        }
        //按照边权重从小到大排序
        Collections.sort(edges,(a,b)->{
            return a[2]-b[2];
        });
        //执行Kruskal算法
        int mst=0;
        unionFind unionFind=new unionFind(n);
        for(int[] edge:edges){
            int u=edge[0];
            int v=edge[1];
            int weight=edge[2];
            if(unionFind.connected(u,v))
                continue;
            mst +=weight;
            unionFind.union(u,v);
        }
        return mst;
    }
}
