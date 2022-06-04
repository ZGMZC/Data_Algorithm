package diff_Num;

import java.util.HashMap;
import java.util.Map;

public class Test {
    @org.junit.Test
    public void diffNum(){
        diffNum diffNum=new diffNum();
        int[] modifiedArray = diffNum.getModifiedArray(5, new int[][]{{1, 3, 2}, {2, 4, 3}, {0, 2, -2}});
        for(int i:modifiedArray)
            System.out.println(i);
        int[] flightBooking= diffNum.corpFlightBooking(new int[][]{{1,2,10}, {2,3,20}, {2,5,25}},5);
        for(int i:flightBooking)
            System.out.println(i);
    }
}
