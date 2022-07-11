package greedy_Algorithm;

/**
 * @author ZGMZC
 * @date 2022/7/11 20:21
 */
public class greedyAlgorithm {
    /**
     * LeetCode 134
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n=gas.length;
        int sum=0;
        for(int i=0;i<n;i++){
            sum+=gas[i]-cost[i];
        }
        if(sum<0) return -1;
        int tank=0;
        int start=0;
        for(int i=0;i<n;i++){
            tank+=gas[i]-cost[i];
            if(tank<0){
                tank=0;
                start=i+1;
            }
        }
        return start==n?0:start;
    }
}
