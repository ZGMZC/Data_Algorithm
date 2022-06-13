package diff_Num;

public class diffNum {
    /*类似前缀和技巧构造的 prefix 数组，我们先对 nums 数组构造⼀个 diff 差
分数组，diff[i] 就是 nums[i] 和 nums[i-1] 之差*/
    private int[] diff;

    /* 输入⼀个初始数组，区间操作将在这个数组上进行 */
    public void init(int[] nums){
        diff = new int[nums.length];
        diff[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            diff[i] = nums[i] - nums[i - 1];
        }
    }
    /* 给闭区间 [i, j] 增加 val（可以是负数）*/
    public void increment(int start,int end,int value){
        //从i到n，差分数组增加val，代表原数组从i到n增加val
        diff[start]+=value;
        //end+1<length: 判断终点是否大于长度，若大于则说明从i到n，均增加了val；若小于说明从i到j增加了val，从j到n不变，需要减去val
        if(end+1<diff.length)
            diff[end+1]-=value;
    }
    /* 返回结果数组 */
    public int[] result() {
        int[] res = new int[diff.length];
        // 根据差分数组构造结果数组
        res[0] = diff[0];
        for (int i = 1; i < diff.length; i++) {
            res[i] = res[i - 1] + diff[i];
        }
        return res;
    }

    /**
     * LeetCode 370
     * 假设你有一个长度为n的数组，初始情况下所有的数字均为0，你将会被给出k个更新操作
     * 其中每个操作都会被表示成一个三元组：[start,end,val]
     * 将数组中[i...j]（包括i和j）增加val
     * @param length
     * @param updates
     * @return
     */
    public int[] getModifiedArray(int length, int[][] updates){
        int[] nums=new int[length];
        init(nums);
        for(int[] update:updates){
            int i=update[0];
            int j=update[1];
            int val=update[2];
            increment(i,j,val);
        }
        return result();
    }

    /**
     * LeetCode 1109
     * 这里有n个航班，它们分别从 1 到 n 进行编号。
     *
     * 有一份航班预订表bookings ，表中第i条预订记录bookings[i] = [firsti, lasti, seatsi]意味着在从 firsti到 lasti （包含 firsti 和 lasti ）的 每个航班 上预订了 seatsi个座位。
     *
     * 请你返回一个长度为 n 的数组answer，里面的元素是每个航班预定的座位总数。
     * @param bookings
     * @param n
     * @return
     */
    public int[] corpFlightBooking(int[][] bookings,int n){
        int[] nums=new int[n];
        init(nums);
        for(int[] book:bookings){
            //从1到n编号，所以索引需要减一
            int i=book[0]-1;
            int j=book[1]-1;
            int val=book[2];
            increment(i,j,val);
        }
        return result();
    }

    /**
     * LeetCode 1094
     * 车上最初有capacity个空座位。车只能向一个方向行驶（也就是说，不允许掉头或改变方向）
     *
     * 给定整数capacity和一个数组 trips , trip[i] = [numPassengersi, fromi, toi]表示第 i 次旅行有numPassengersi乘客，接他们和放他们的位置分别是fromi和toi。这些位置是从汽车的初始位置向东的公里数。
     *
     * 当且仅当你可以在所有给定的行程中接送所有乘客时，返回true，否则请返回 false。
     * 1 <= trips.length <= 1000
     * trips[i].length == 3
     * 1 <= numPassengersi<= 100
     * 0 <= fromi< toi<= 1000
     * 1 <= capacity <= 105
     * @param trips
     * @param capacity
     * @return
     */
    public boolean carPooling(int[][] trips, int capacity){
        int[] nums=new int[1001];
        init(nums);
        for(int[] trip:trips){
            int i=trip[1];
            //在trip[2]位置下车，乘客在车上的区间是 [trip[1], trip[2] - 1]
            int j=trip[2]-1;
            int val=trip[0];
            increment(i,j,val);
        }
        int[] result = result();
        for(int tmp:result){
            if(tmp>capacity) return false;
        }
        return true;
    }
}
