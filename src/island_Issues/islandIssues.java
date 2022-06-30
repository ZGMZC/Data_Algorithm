package island_Issues;

public class islandIssues {
    /**
     * 二维矩阵遍历框架
     */
    public void dfs(int[][] grid, int i, int j, boolean[][] visit) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n) {
            //超出索引边界
            return;
        }
        if (visit[i][j]) {
            //已遍历过（i，j）
            return;
        }

        /*进入节点（i，j） TODO*/

        visit[i][j] = true;
        dfs(grid, i - 1, j, visit);
        dfs(grid, i + 1, j, visit);
        dfs(grid, i, j - 1, visit);
        dfs(grid, i, j + 1, visit);
    }

    /**
     * LeetCode 200
     *
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int res = 0;
        int n = grid.length;
        int m = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    res++;
                    dfs(grid, i, j);
                }
            }
        }
        return res;
    }

    private void dfs(char[][] grid, int i, int j) {
        int n = grid.length, m = grid[0].length;
        if (i < 0 || j < 0 || i >= n || j >= m) {
            return;
        }
        if (grid[i][j] == '0')
            return;
        grid[i][j] = '0';
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }

    public int closedIsland(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        for (int j = 0; j < m; j++) {
            // 把靠上边的岛屿淹掉
            dfs(grid, 0, j);
            // 把靠下边的岛屿淹掉
            dfs(grid, n - 1, j);
        }
        for (int i = 0; i < n; i++) {
            // 把靠左边的岛屿淹掉
            dfs(grid, i, 0);
            // 把靠右边的岛屿淹掉
            dfs(grid, i, m - 1);
        }
        // 遍历 grid，剩下的岛屿都是封闭岛屿
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    res++;
                    dfs(grid, i, j);
                }
            }
        }
        return res;
    }

    private void dfs(int[][] grid, int i, int j) {
        int n = grid.length, m = grid[0].length;
        if (i < 0 || j < 0 || i >= n || j >= m) {
            return;
        }
        if (grid[i][j] == 1) {
            return;
        }
        grid[i][j] = 1;
        dfs(grid, i + 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i - 1, j);
        dfs(grid, i, j - 1);
    }

    /**
     * LeetCode 1020
     *
     * @param grid
     * @return
     */
    public int numEnclaves(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        // 淹掉靠边的陆地
        for (int i = 0; i < n; i++) {
            dfs0(grid, i, 0);
            dfs0(grid, i, m - 1);
        }
        for (int j = 0; j < m; j++) {
            dfs0(grid, 0, j);
            dfs0(grid, n - 1, j);
        }
        // 数一数剩下的陆地
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    res += 1;
                }
            }
        }
        return res;
    }

    private void dfs0(int[][] grid, int i, int j) {
        int n = grid.length, m = grid[0].length;
        if (i < 0 || j < 0 || i >= n || j >= m) {
            return;
        }
        if (grid[i][j] == 0) {
            return;
        }
        grid[i][j] = 0;
        dfs0(grid, i + 1, j);
        dfs0(grid, i, j + 1);
        dfs0(grid, i - 1, j);
        dfs0(grid, i, j - 1);
    }

    /**
     * LeetCode 695
     *
     * @param grid
     * @return
     */
    public int maxAreaOfIsland(int[][] grid) {
        // 记录岛屿的最大面积
        int res = 0;
        int n = grid.length, m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    // 淹没岛屿，并更新最大岛屿面积
                    res = Math.max(res, dfs1(grid, i, j));
                }
            }
        }
        return res;
    }

    private int dfs1(int[][] grid, int i, int j) {
        int n = grid.length, m = grid[0].length;
        if (i < 0 || j < 0 || i >= n || j >= m) {
            return 0;
        }
        if (grid[i][j] == 0) {
            return 0;
        }
        grid[i][j]=0;
        return dfs1(grid, i + 1, j)
                + dfs1(grid, i, j + 1)
                + dfs1(grid, i - 1, j)
                + dfs1(grid, i, j - 1) + 1;
    }

    /**
     * LeetCode 1905
     * @param grid1
     * @param grid2
     * @return
     */
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int n = grid1.length, m = grid1[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid1[i][j] == 0 && grid2[i][j] == 1) {
                    // 这个岛屿肯定不是子岛，淹掉
                    dfs2(grid2, i, j);
                }
            }
        }
        // 现在 grid2 中剩下的岛屿都是子岛
        int res = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                if (grid2[i][j] == 1){
                    res++;
                    dfs2(grid2,i,j);
                }
            }
        }
        return res;
    }
    private void dfs2(int[][] grid,int i,int j){
        int n = grid.length, m = grid[0].length;
        if(i<0||j<0||i>=n||j>=m){
            return;
        }
        if(grid[i][j]==0){
            return;
        }
        grid[i][j]=0;
        dfs2(grid, i + 1, j);
        dfs2(grid, i, j + 1);
        dfs2(grid, i - 1, j);
        dfs2(grid, i, j - 1);
    }

    /**
     * LeetCode 694
     * @param grid
     * @return
     */
    public int numDistinctIslands(int[][] grid){

    }
}
