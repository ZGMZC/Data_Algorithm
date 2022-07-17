package greedy_Algorithm;

import java.util.*;

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

    /**
     * LeetCode 1024
     * @param clips
     * @param time
     * @return
     */
    public static int videoStitching(int[][] clips, int time) {
        if(time==0) return 0;
        Arrays.sort(clips, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]==o2[0])
                    return o2[1]-o1[1];
                return o1[0]-o2[0];
            }
        });
        int res=0;
        int curEnd=0,nextEnd=0;
        int i=0,n=clips.length;
        while (i<n&&clips[i][0]<=curEnd){
            while (i<n&&clips[i][0]<=curEnd){
                nextEnd=Math.max(clips[i][1],nextEnd);
                i++;
            }
            res++;
            curEnd=nextEnd;
            if(curEnd>=time)
                return res;
        }
        return -1;
    }
    /**
     * LeetCode 659
     * @param nums
     * @return
     */
    public boolean isPossible(int[] nums) {
        HashMap<Integer,Integer> freq=new HashMap<>();
        HashMap<Integer,Integer> need=new HashMap<>();
        for(int v:nums){
            freq.put(v,freq.getOrDefault(v,0)+1);
        }
        for(int v:nums){
            if(freq.getOrDefault(v,0)<=0) continue;
            // 先判断 v 是否能接到其他子序列后面
            if(need.getOrDefault(v,0)>0){
                freq.put(v,freq.getOrDefault(v,0)-1);
                need.put(v,need.get(v)-1);
                need.put(v+1,need.getOrDefault(v+1,0)+1);
            }else if(freq.getOrDefault(v,0)>0&&freq.getOrDefault(v+1,0)>0&&freq.getOrDefault(v+2,0)>0){
                freq.put(v,freq.getOrDefault(v,0)-1);
                freq.put(v+1,freq.get(v+1)-1);
                freq.put(v+2,freq.get(v+2)-1);
                need.put(v+3,need.getOrDefault(v+3,0)+1);
            }else return false;
        }
        return true;
    }

    /**
     * LeetCode 391
     * @param rectangles
     * @return
     */
    public boolean isRectangleCover(int[][] rectangles) {
        int x = 10001, y = 10001, a = -10001, b = -10001;
        long s = 0;
        Map<String, Integer> cnts = new HashMap<>();
        for(int[] r: rectangles){
            x = Math.min(x, r[0]);
            y = Math.min(y, r[1]);
            a = Math.max(a, r[2]);
            b = Math.max(b, r[3]);
            s += (r[3] - r[1]) * (r[2] - r[0]);
            String p1 = point(r[0], r[1]), p2 = point(r[0],r[3]), p3 = point(r[2], r[1]), p4 = point(r[2],r[3]);
            cnts.put(p1, cnts.getOrDefault(p1, 0) + 1);
            cnts.put(p2, cnts.getOrDefault(p2, 0) + 1);
            cnts.put(p3, cnts.getOrDefault(p3, 0) + 1);
            cnts.put(p4, cnts.getOrDefault(p4, 0) + 1);
        }
        if(s != (a - x) * (b - y))
            return false;
        Set<String> points = new HashSet<String>(){};
        points.add(point(x, y));
        points.add(point(x, b));
        points.add(point(a, y));
        points.add(point(a, b));
        for(String p:points){
            if(!cnts.containsKey(p) || cnts.get(p) != 1)
                return false;
        }
        for(String p:cnts.keySet()){
            if(!points.contains(p)){
                int v = cnts.get(p);
                if(v > 4 || v % 2 != 0)
                    return false;
            }
        }
        return true;
    }
    private String point(int x, int y){
        return String.format("%d,%d",x,y);
    }

    /**
     * LeetCode 435
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        return intervals.length-intervalSchedule(intervals);
    }
    private int intervalSchedule(int[][] intervals){
        if(intervals.length==0) return 0;
        Arrays.sort(intervals,(a,b)->a[1]-b[1]);
        int count=1;
        int end=intervals[0][1];
        for(int[] interval:intervals){
            int start=interval[0];
            if(start>=end){
                count++;
                end=interval[1];
            }
        }
        return count;
    }

    /**
     * LeetCode 452
     * @param points
     * @return
     */
    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points,(a,b)->(a[1]<b[1]?-1:1));
        long end=points[0][1];
        int n=points.length;
        int count=1;
        for(int i=0;i<n;i++){
            long start=points[i][0];
            if(start>end){
                count++;
                end=points[i][1];
            }
        }
        return count;
    }
}
