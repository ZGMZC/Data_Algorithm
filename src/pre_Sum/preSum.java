package pre_Sum;

public class preSum {
    /* 核心思路是我们 new ⼀个新的数组 preSum 出来，preSum[i] 记录 nums[0..i-1] 的累加和
     想求索引区间 [1, 4] 内的所有元素之和，就可以通过 preSum[5] -preSum[1]得出*/

    /**
     * LeetCode 303
     * 给定一个整数数组nums，求出数组从索引i到j范围内的元素总和，包括了i、j两点
     * 核心思路一致，我写在了同一个方法里面
     *
     * @param nums
     * @param left
     * @param right
     * @return
     */
    public int pre_Sum(int[] nums, int left, int right) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        for (int i = 1; i <=n; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        return preSum[right + 1] - preSum[left];
    }

    /**
     * LeetCode 304
     * 本方法采用 一维前缀和
     * 给定一个二维矩阵 matrix，以下类型的多个请求：
     *
     * 计算其子矩形范围内元素的总和，该子矩阵的 左上角 为 (row1,col1) ，右下角 为 (row2,col2) 。
     * 实现 NumMatrix 类：
     *
     * NumMatrix(int[][] matrix)给定整数矩阵 matrix 进行初始化
     * int sumRegion(int row1, int col1, int row2, int col2)返回 左上角 (row1,col1)、右下角(row2,col2) 所描述的子矩阵的元素 总和 。
     * preSum[i][j],代表着 nums[i-1][0]....nums[i-1][j-1] 的累加和
     * @param matrix
     * @param row1
     * @param col1
     * @param row2
     * @param col2
     * @return
     */
    public int pre_Sum_Matrix_One(int[][] matrix, int row1, int col1, int row2, int col2) {
        int n = matrix.length;
        if (n < 1) return 0;
        int m = matrix[0].length;
        int[][] preSum=new int[n+1][m+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                preSum[i][j]=preSum[i][j-1]+matrix[i-1][j-1];
            }
        }
        int sum=0;
        for(int i=row1;i<=row2;i++)
            sum+=preSum[i+1][col2+1]-preSum[i+1][col1];
        return sum;
    }

    /**
     * LeetCode 304
     * 本方法采用  二维前缀和
     * preSum[i][j] 代表着  matrix 中子矩阵 [0, 0, i-1, j-1] 的累加和
     * @param matrix
     * @param row1
     * @param col1
     * @param row2
     * @param col2
     * @return
     */
    public int pre_Sum_Matrix_Two(int[][] matrix, int row1, int col1, int row2, int col2){
        int n = matrix.length;
        if (n < 1) return 0;
        int m = matrix[0].length;
        int[][] preSum=new int[n+1][m+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                preSum[i][j]=preSum[i-1][j]+preSum[i][j-1]+matrix[i-1][j-1]-preSum[i-1][j-1];
            }
        }
        return preSum[row2+1][col2+1]-preSum[row1][col2+1]-preSum[row2+1][col1]+preSum[row1][col1];
    }
}
