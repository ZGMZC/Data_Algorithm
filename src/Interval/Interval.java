package Interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ZGMZC
 * @date 2022/7/11 23:14
 */
public class Interval {
    /**
     * LeetCode 1288
     * @param intervals
     * @return
     */
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]==o2[0])
                    return o2[1]-o1[1];
                return o1[0]-o2[0];
            }
        });
        int left=intervals[0][0];
        int right=intervals[0][1];
        int res=0;
        for(int i=1;i<intervals.length;i++){
            int[] intv=intervals[i];
            if(left<=intv[0]&&right>=intv[1])
                res++;
            if(right>=intv[0]&&right<=intv[1])
                right=intv[1];
            if(right<intv[0]){
                left=intv[0];
                right=intv[1];
            }
        }
        return intervals.length-res;
    }

    /**
     * LeetCode 56
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {
        LinkedList<int[]> res=new LinkedList<>();
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        res.add(intervals[0]);
        for(int i=1;i<intervals.length;i++){
            int[] cur=intervals[i];
            int[] last=res.getLast();
            if(cur[0]<=last[1])
                last[1]=Math.max(last[1],cur[1]);
            else
                res.add(cur);
        }
        return res.toArray(new int[0][0]);
    }

    /**
     * LeetCode 986
     * @param firstList
     * @param secondList
     * @return
     */
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        List<int[]> res=new LinkedList<>();
        int i=0,j=0;
        while (i<firstList.length&&j<secondList.length){
            int a1=firstList[i][0],a2=firstList[i][1];
            int b1=secondList[j][0],b2=secondList[j][1];
            if(b2>=a1&&a2>=b1){
                res.add(new int[]{Math.max(a1,b1),Math.min(a2,b2)});
            }
            if(b2<a2){
                j++;
            }else {
                i++;
            }
        }
        return res.toArray(new int[0][0]);
    }
}
