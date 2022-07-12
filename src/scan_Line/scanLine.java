package scan_Line;

import java.util.Arrays;

/**
 * @Author ZGMZC
 * @Date 2022/7/12 21:37
 */
public class scanLine {
    public int minMeetingRooms(int[][] meetings){
        int n=meetings.length;
        int[] begin=new int[n];
        int[] end=new int[n];
        for(int i=0;i<n;i++){
            begin[i] = meetings[i][0];
            end[i] = meetings[i][1];
        }
        Arrays.sort(begin);
        Arrays.sort(end);
        int count=0;
        int res=0,i=0,j=0;
        while (i<n&&j<n){
            if (begin[i] < end[j]) {
                count++;
                i++;
            } else {
                count--;
                j++;
            }
            res = Math.max(res, count);
        }
        return res;
    }
}
