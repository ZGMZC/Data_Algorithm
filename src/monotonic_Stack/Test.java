package monotonic_Stack;

public class Test {
    @org.junit.Test
    public void test(){
        monotonicStack monotonicStack=new monotonicStack();
        int[] nums=new int[]{-7,-8,7,5,7,1,6,0};
        monotonicStack.maxSlidingWindow(nums,4);

    }
}
