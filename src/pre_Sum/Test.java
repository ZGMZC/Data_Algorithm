package pre_Sum;

public class Test {
    @org.junit.Test
    public void preSum(){
        preSum preSum=new preSum();
        int test[][]=new int[][]{{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}};
        System.out.println(preSum.pre_Sum_Matrix_One(test, 2, 1, 4, 3));
        System.out.println(preSum.pre_Sum_Matrix_Two(test, 2, 1, 4, 3));
    }
}
