package binary_search;

public class Test {
    @org.junit.Test
    public void binarysearch(){
        binarySearch binsearch=new binarySearch();
        int target=5;
        int[] nums=new int[]{1,3,4,5,5,5,7,8,51,32};
        System.out.println(binsearch.binary_search(nums,target));
        System.out.println(binsearch.left_bound(nums,target));
        System.out.println(binsearch.right_bound(nums,target));
    }
}
