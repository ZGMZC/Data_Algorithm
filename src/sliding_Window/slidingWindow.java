package sliding_Window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 滑动窗口 模板
 */
public class slidingWindow {
    public void Frame(String s, String t) {
        Map<Character, Integer> tmap = new HashMap<>();
        Map<Character, Integer> wmap = new HashMap<>();
        for (char c : t.toCharArray())
            tmap.put(c, tmap.getOrDefault(c, 0) + 1);
        int left = 0, right = 0;
        //valid 表示目前窗口中满足字符串t条件的字符个数
        int valid = 0;
        while (right < s.length()) {
            //c是将要移入窗口的字符
            char c = s.charAt(right);
            //增大窗口
            right++;

            /*进行窗口内数据的更新及判定 TODO*/

            // 判断左侧窗口是否要收缩
            while ( valid ==t.length()/*TODO*/) {
                //d 是将要移出的窗口
                char d = s.charAt(left);
                //缩小窗口
                left++;

                /*进行窗口内数据的更新及判定 TODO*/

            }
        }
    }

    /**
     * LeetCode 76
     * S(source) 中找到包含T(target) 中全部字母的一个子串，且这个子串一定是所有可能子串中最短的
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char c : t.toCharArray())
            need.put(c, need.getOrDefault(c, 0) + 1);
        int left = 0, right = 0;
        int valid = 0;//窗口中满足need条件的字符个数
        //记录最小覆盖子窜的起始索引和长度
        int start = 0, len = Integer.MAX_VALUE;
        while (right < s.length()) {
            //c是将要移入窗口的字符
            char c = s.charAt(right);
            //增大窗口
            right++;

            /* 窗口内数据的一系列更新 */
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (window.get(c).equals(need.get(c))) valid++;
            }
            /* 窗口内数据的一系列更新 */

            /*判断左侧窗口是否要收缩*/
            while (valid == need.size()) {
                //更新最小覆盖子串
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }

                //d是要移出窗口的字符
                char d = s.charAt(left);
                //缩小窗口
                left++;

                /*窗口内数据的一系列更新*/
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d)))
                        valid--;
                    window.put(d, window.get(d) - 1);
                }
                /* 窗口内数据的一系列更新 */
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start+len);
    }
    /**
     * Leetcode 567
     * 给你一个S和一个T，请问S中是否存在一个子串，包含T中所有字符且不包含其他字符
     * T可重复
     * @param s
     * @param t
     * @return
     */
    public boolean checkInclusion(String s,String t){
        Map<Character,Integer> tmap=new HashMap<>();
        Map<Character,Integer> window=new HashMap<>();
        for (char c:t.toCharArray())
            tmap.put(c,tmap.getOrDefault(c,0)+1);
        int left=0,right=0;
        int valid=0;
        while(right<s.length()){
            char c=s.charAt(right);
            right++;

            if(tmap.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
                if(tmap.get(c).equals(window.get(c)))
                    valid++;
            }

            while (right-left>=t.length()){
                if(valid==t.length())
                    return true;

                char d=s.charAt(left);
                left++;

                if(tmap.containsKey(d)){
                    if(tmap.get(d).equals(window.get(d)))
                        valid--;
                    window.put(d,window.get(d)-1);
                }
            }
        }
        return false;
    }

    /**
     * LeetCode 438
     * 输入一个串S，一个串T，找到S中所有T的排列，返回它们的起始索引。
     * @param s
     * @param t
     * @return
     */
    public int[] findAnagrams(String s,String t){
        Map<Character,Integer> tmap=new HashMap<>();
        Map<Character,Integer> window=new HashMap<>();
        for(char c:t.toCharArray())
            tmap.put(c,tmap.getOrDefault(c,0)+1);
        int left=0,right=0;
        int valid=0;

        ArrayList<Integer> res=new ArrayList<>();
        while(right<s.length()){
            char c=s.charAt(right);
            right++;

            if(tmap.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
                if(tmap.get(c).equals(window.get(c)))
                    valid++;
            }

            while (right-left>t.length()){
                if(valid==t.length()){
                    res.add(left);
                }

                char d=s.charAt(left);
                left++;

                if(tmap.containsKey(d)){
                    if(tmap.get(d).equals(window.get(d)))
                        valid--;
                    window.put(d,window.get(d)-1);
                }
            }
        }
        int[] ans=new int[res.size()];
        for(int i=0;i<res.size();i++)
            ans[i]=res.get(i);
        return ans;
    }

    /**
     * LeetCode 3
     * 无重复字符的最长子串
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s){
        Map<Character,Integer> window=new HashMap<>();
        int left=0,right=0;
        int res=0;
        while(right<s.length()){
            char c=s.charAt(right);
            right++;
            window.put(c,window.getOrDefault(c,0)+1);
            while(window.get(c)>1){
                char d=s.charAt(left);
                left++;
                window.put(d,window.get(d)-1);
            }
            res=Math.max(res,right-left);
        }
        return res;
    }
}
