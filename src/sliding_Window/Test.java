package sliding_Window;

import java.util.Arrays;

public class Test {
    @org.junit.Test
    public void slidingWindow(){
        slidingWindow slidingWindow=new slidingWindow();
        System.out.println(slidingWindow.minWindow("EBBANCF", "ABC"));
        System.out.println(slidingWindow.checkInclusion("eidboaooo","ab"));
        int[] anagrams = slidingWindow.findAnagrams("cabaebabacb", "abc");
        for(int i:anagrams)
        System.out.println(i);
        System.out.println(slidingWindow.lengthOfLongestSubstring("aaaaa"));
    }
}
