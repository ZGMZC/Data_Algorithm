import java.util.*;

public class Test {
    @org.junit.Test
    public void one(){
        List<ArrayList<Integer>> list=new ArrayList<>();
        ArrayList<Integer> s1=new ArrayList<Integer>();
        s1.add(1);
        s1.add(9);
        ArrayList<Integer> s2=new ArrayList<Integer>();
        s2.add(1);
        s2.add(8);
        ArrayList<Integer> s3=new ArrayList<Integer>();
        s3.add(2);
        s3.add(8);
        list.add(s1);
        list.add(s3);
        list.add(s2);

        Collections.sort(list, new Comparator<ArrayList<Integer>>() {
            @Override
            public int compare(ArrayList<Integer> o1, ArrayList<Integer> o2) {
                if(o1.get(0)>o2.get(0)) return 1;
                else if(o1.get(0)==o2.get(0)) return o2.get(1)-o1.get(1);
                else return -1;
            }
        });
        System.out.println(list.toString());
    }
}
