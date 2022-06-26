package union_Find;

public class unionFind {
    private int count; //连通分量个数
    private int[] parent;//存储一棵树
    private int[] size;//记录树的重量

    //n为图中节点的个数
    public unionFind(int n){
        this.count=n;
        parent=new int[n];
        size=new int[n];
        for(int i=0;i<n;i++){
            size[i]=1;
            parent[i]=i;
        }
    }
    //将节点p和节点q连通
    public void union(int p,int q){
        int rootP=find(p);
        int rootQ=find(q);
        if(rootP==rootQ) return;

        //将小树接到大树下面
        if(size[rootP]>size[rootQ]){
            parent[rootQ]=rootP;
            size[rootP] +=size[rootQ];
        }else{
            parent[rootP]=rootQ;
            size[rootQ]=size[rootP];
        }
        count--;
    }
    // 判断节点 p 和节点 q 是否连通
    public boolean connected(int p,int q){
        int rootP=find(p);
        int rootQ=find(q);
        return rootP==rootQ;
    }
    //返回节点x的连通分量根节点
    private int find(int x){
        while (parent[x]!=x){
            parent[x] =parent[parent[x]];
            x=parent[x];
        }
        return x;
    }
    //返回图中的连通分量个数
    public int count(){
        return count;
    }
}
