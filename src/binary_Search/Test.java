package binary_Search;

public class Test {
    @org.junit.Test
    public void binarysearch() {
        binarySearch binsearch = new binarySearch();
        int target = 5;
        int[] nums = new int[]{1, 3, 4, 5, 5, 5, 7, 8, 51, 32};
        System.out.println(binsearch.binary_search(nums, target));
        System.out.println(binsearch.left_bound(nums, target));
        System.out.println(binsearch.right_bound(nums, target));
    }

    @org.junit.Test
    public void test() {
        int[] weights =new int[]{3, 2, 2, 4, 1, 4};
        int sum = 0;
        int x = 3;
        int days = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += weights[i];
            if (sum > x) {
                days++;
                sum = weights[i];
            } else if (sum == x) {
                days++;
                sum = 0;
            }
            System.out.println(days);
        }
    }
}
